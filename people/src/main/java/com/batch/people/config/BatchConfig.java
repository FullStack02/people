package com.batch.people.config;

import java.util.Map;
import javax.sql.DataSource;
import javax.validation.Validation;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.batch.people.entity.People;
import com.batch.people.validator.PeopleValidation;
import com.batch.people.validator.PeopleValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Configuration
@EnableBatchProcessing
@EnableScheduling
public class BatchConfig {
	

	private static final Logger LOG = LoggerFactory.getLogger(BatchConfig.class);
	
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	

	@Autowired
	private DataSource datasource;
	


	@Bean
	public BlankLineRecordSeparatorPolicy blrsp() {
		
		return new BlankLineRecordSeparatorPolicy();
	}
				
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Bean
	public FlatFileItemReader<People> reader(){	
		
		FlatFileItemReader<People> reader = new FlatFileItemReader<People>();
		reader.setResource(new FileSystemResource("C://Users//ANSAJID//OneDrive - Capgemini//Desktop//2M data -people//data//people.csv"));
		//reader.setResource(new ClassPathResource("people.csv"));
		reader.setLinesToSkip(1);
		reader.setLineMapper(new DefaultLineMapper() {{
			setLineTokenizer(new DelimitedLineTokenizer() {{
				setDelimiter(",");
				setNames("peopleId","LoginId","firstName","lastName","sex","email","phone","dob","jobTitle");
				setIncludedFields(new int[]{0,1,2,3,4,5,6,7,8});
				
			}});
			setFieldSetMapper(new BeanWrapperFieldSetMapper() {{
				setTargetType(People.class);
			}});
			
		}});
		
		  reader.setRecordSeparatorPolicy(new BlankLineRecordSeparatorPolicy());
		
		
		  return reader;
		
	}		
	
	@Bean
	public ItemProcessor<People, People> processor(){
		
		return new PeopleProcessor();
	}
	
//	@Bean
//	public BeanValidatingItemProcessor<People> itemValidator() throws Exception {
//		BeanValidatingItemProcessor<People> validator = new BeanValidatingItemProcessor<People>();
//		validator.setFilter(true);
//		validator.afterPropertiesSet();
//		return validator;
//	}
	
	
//	   @Bean
//	    public ValidatingItemProcessor<People> itemProcessor() {
//	        ValidatingItemProcessor<People> validatingItemProcessor = new ValidatingItemProcessor<People>();
//	        validatingItemProcessor.setValidator((Validator<? super People>) new PeopleValidation());
//	        validatingItemProcessor.setFilter(true);
//	        return validatingItemProcessor;
//	    }
	   
	   
	   @Bean
	   public PeopleValidation validation() {
		   
		   return new PeopleValidation();
	   }
	
	
	
	@Bean
	public JdbcBatchItemWriter<People> writer(DataSource dataSource){	
		JdbcBatchItemWriter<People> writer = new JdbcBatchItemWriter<People>();	
		writer.setSql("INSERT INTO PEOPLELIST(peopleId,loginId,firstName,lastName,sex,email,phone,dob,jobTitle) VALUES (:peopleId,:loginId,:firstName,:lastName,:sex,:email,:phone,:dob,:jobTitle);");
		writer.setDataSource(datasource);
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<People>());
	
		return writer;
	}
	
	@Bean
	public JobExecutionListener listener() {
		
	
		return new PeopleJobListener();
	}
	
	@Bean
	public Job job1() {
				
		return jobBuilderFactory.get("job1")
				.incrementer(new RunIdIncrementer())
				.listener(listener())
				.start(step1())
				.build(); 		
	}
	
	
	@Bean
	public Step step1() {
		
		return stepBuilderFactory.get("step1")
				.<People,People>chunk(10)
				.reader(reader())
				.processor(processor())
				.writer(writer(datasource))
				.faultTolerant()
				.skip(ValidationException.class)
                .skipLimit(100)
                .skip(FlatFileParseException.class)
                .skipLimit(150)
				.build();
	}

	@Bean
	public JobParametersValidator validate() {
	    JobParametersValidator j = new JobParametersValidator() {
	        
	        public void validate(JobParameters parameters) throws JobParametersInvalidException {
	            Map<String, JobParameter> params = parameters.getParameters();
	            if(null == params.get("peopleId").getValue() || null == params.get("loginId").getValue()
	                    || null == params.get("firstName").getValue()){
	                throw new JobParametersInvalidException("Cant be null");
	            }
	        }
	    };
	    return j;       
	}
	

	
	

}
