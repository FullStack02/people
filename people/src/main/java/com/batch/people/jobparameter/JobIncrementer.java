package com.batch.people.jobparameter;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

public class JobIncrementer  implements JobParametersIncrementer {
	
	 public JobParameters getNext(JobParameters parameters) {
	        if (parameters==null || parameters.isEmpty()) {
	            return new JobParametersBuilder().addLong("run.id", 1L).toJobParameters();
	        }
	     
			long id = parameters.getLong("run.id",1L) +1;
	        return new JobParametersBuilder().addLong("run.id", id).toJobParameters();
	    }

}
