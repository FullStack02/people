package com.batch.people;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.batch.people.config.BatchConfig;

import lombok.extern.slf4j.Slf4j; 

@Component
@Slf4j
public class PeopleJobRunner implements CommandLineRunner {
	
	private static final Logger LOG = LoggerFactory.getLogger(BatchConfig.class);

	@Autowired
	private JobLauncher launcher;
	
	@Autowired
	private Job job1;
	
	public void run(String... args) throws Exception {
		
		JobParameters jobParameters = new JobParametersBuilder()
				.addLong("time", System.currentTimeMillis())
				.toJobParameters();
		
		launcher.run(job1,jobParameters);
		LOG.info("JOB EXECUTION DONE");
	}

}
