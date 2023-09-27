package com.batch.people.jobparameter;

import org.springframework.batch.core.JobParameters;

public interface JobParametersIncrementer {
	
	   JobParameters getNext(JobParameters parameters);

}
