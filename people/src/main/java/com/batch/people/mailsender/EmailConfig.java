package com.batch.people.mailsender;

import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig
{
	@Bean
	public JavaMailSender getJavaMailSender()
	{
	    JavaMailSender mailSender = new JavaMailSenderImpl();
	    ((JavaMailSenderImpl) mailSender).setHost("smtp.gmail.com");
	    ((JavaMailSenderImpl) mailSender).setPort(25);

	    ((JavaMailSenderImpl) mailSender).setUsername("anamrahman2702@gmail.com");
	    ((JavaMailSenderImpl) mailSender).setPassword("Paris@1998");

	    Properties props = ((JavaMailSenderImpl) mailSender).getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");

	    return mailSender;
	}

	@Bean
	public SimpleMailMessage emailTemplate()
	{
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("anamsajid2702@gmail.com");
		message.setFrom("anamrahman2702@gmail.com");
	    message.setText("People Data has been successfully imported. Job completed !!");
	    return message;
	}
}
