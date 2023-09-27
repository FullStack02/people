package com.batch.people;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.batch.people.mailsender.EmailService;

@SpringBootApplication
@EnableJpaRepositories("com.batch.people.*")
public class PeopleApplication extends SpringBootServletInitializer {
	
	

	public static void main(String[] args) throws Exception {
		SpringApplication.run(PeopleApplication.class, args);
		
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		SessionFactory factory= cfg.buildSessionFactory();
		System.out.println("Factory Session " + factory);  
		
		
		//Create the application context
//	    ApplicationContext context = new FileSystemXmlApplicationContext
//	    			("classpath:com/batch/people/email/applicationContext.xml");

	    //Get the mailer instance
//	    EmailService mailer = (EmailService) context.getBean("emailService");

	    //Send a composed mail
//	    mailer.sendNewMail("anamsajid2702@gmail.com", "JOB STATUS", "Job has been exceuted successfully");

	    //Send a pre-configured mail
//	    mailer.sendPreConfiguredMail("Exception occurred everywhere.. where are you ????");
	}
		
		
	@Override  
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application)   
	{  
	return application.sources(PeopleApplication.class);  
	}  

	}
	
