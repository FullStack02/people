package com.batch.people.PeopleWriter;

import java.util.List;
import java.util.function.Consumer;

import javax.mail.internet.MimeMessage;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import com.batch.people.entity.People;

public class PeopleWriter implements ItemWriter<MimeMessage> {

	@Autowired
	private JavaMailSender mailSender;
	
	public void write(List<? extends MimeMessage> messages) throws Exception {
		
		messages.stream().forEach(new Consumer<MimeMessage>() {
			public void accept(MimeMessage message) {
				mailSender.send(message);
			}
		});
		
	}
	
	
	

	
	
	
	

}
