package com.batch.people.scheduler;

import org.springframework.scheduling.annotation.Scheduled;

public class TaskScheduler {
	
	
//	   @Scheduled(fixedRate = 10000)
//	    public void scheduledTime() {
//	        Date date = new Date();
//	        String stringDateFormat = "hh:mm:ss a";
//	        DateFormat dateFormat = new SimpleDateFormat(stringDateFormat);
//	        //get current time
//	        String formattedDateAsString = dateFormat.format(date);
//	        System.out.println("This method will execute after every 10 sec " + formattedDateAsString);
//	    }

	   
	   
	   @Scheduled(cron = "0 15 10 15 * ?")
	   public void scheduleTask() {
	    
	       long now = System.currentTimeMillis() / 1000;
	       System.out.println(
	         "Job Executing on 15th of every month at 10:15 - " + now);
	   }
}
