package com.batch.people;

import static org.junit.Assert.assertEquals;
import javax.batch.runtime.BatchStatus;
import javax.sql.DataSource;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import com.batch.people.config.BatchConfig;


@SpringBatchTest
@SpringJUnitConfig({PeopleApplication.class,BatchConfig.class})
class PeopleApplicationTests {


    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private DataSource dataSource;

    
    private JdbcTemplate jdbcTemplate;
    

    @Autowired
    @Qualifier(value = "singleJobLauncherTestUtils")
    private JobLauncherTestUtils singleJobLauncherTestUtils;

    @Autowired
    @Qualifier(value = "multiJobLauncherTestUtils")
    private JobLauncherTestUtils multiJobLauncherTestUtils;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Test
    public void testStatusJob() throws Exception {
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();

        // Assert the job execution status
        assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());

        // Assert the number of records processed
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        assertEquals(2000000, jdbcTemplate.queryForObject("SELECT COUNT(*) FROM people", Integer.class).intValue());
    }
    
    
    
    @Test
    public void testJob(@Autowired Job job) throws Exception {
        this.jobLauncherTestUtils.setJob(job);
        this.jdbcTemplate.update("update from People") ;
        for (int i = 1; i <= 10; i++) {
            this.jdbcTemplate.update("insert into CUSTOMER values (?, 0, ?, 100000)",
                                      i, "customer" + i);
        }

        JobExecution jobExecution = jobLauncherTestUtils.launchJob();


        Assert.assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode());
    }


    public class NoWorkFoundStepExecutionListener extends StepExecutionListenerSupport {

        public ExitStatus afterStep(StepExecution stepExecution) {
            if (stepExecution.getReadCount() == 0) {
                return ExitStatus.FAILED;
            }
            return null;
        }
    }



    private NoWorkFoundStepExecutionListener tested = new NoWorkFoundStepExecutionListener();

    @Test
    public void testAfterStep() {
        StepExecution stepExecution = MetaDataInstanceFactory.createStepExecution();

        stepExecution.setExitStatus(ExitStatus.COMPLETED);
        stepExecution.setReadCount(0);

        ExitStatus exitStatus = tested.afterStep(stepExecution);
        assertEquals(ExitStatus.FAILED.getExitCode(), exitStatus.getExitCode());
    }
    
  

}
