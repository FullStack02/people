package com.batch.people.config;

import java.util.Date;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


public class PeopleJobListener implements JobExecutionListener {
	
	
//	@Autowired
//	private JavaMailSender mailSender;
//	
//	private String notificationEmail;
//
//	public PeopleJobListener(String notificationEmail) {
//		this.notificationEmail = notificationEmail;
//	}
	
	
	public void beforeJob(JobExecution je) {
		System.out.println("Job Started at :" + new Date());
		System.out.println("Job Starting status :" + je.getStatus());
		
//		
//		SimpleMailMessage message = new SimpleMailMessage();
//				
//				message.setSubject("Job started");
//				message.setFrom(notificationEmail);
//				message.setTo(notificationEmail);
//				message.setText("Job started");
//		
//				mailSender.send(message);
	
	}
	
	public void afterJob(JobExecution je) {
		System.out.println("Job Ended at :" + new Date());
		System.out.println("Job Ending status :" + je.getStatus());
		
//		SimpleMailMessage message = new SimpleMailMessage();
//				
//				message.setSubject("Job completion");
//				message.setFrom(notificationEmail);
//				message.setTo(notificationEmail);
//				message.setText("Job completed with " + je.getExitStatus());
//		
//				mailSender.send(message);
		
	}

}
