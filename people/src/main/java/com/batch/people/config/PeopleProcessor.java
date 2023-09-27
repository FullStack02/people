package com.batch.people.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.MimeMessage;
import javax.validation.ConstraintViolation;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.spring.VelocityEngineUtils;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.batch.people.entity.People;

public class PeopleProcessor implements ItemProcessor<People, People>{
	
	private javax.validation.Validator validator;
	
	public People process(People people) throws Exception {		
		
		 if (people.getpeopleId() == null) {

			 System.out.println("Missing People id : " + people.getpeopleId());
	            throw new ValidationException("people id must not be null");
	        }
	        if (people.getLoginId() == null) {
	        	 System.out.println("Missing Login id : " + people.getLoginId());
	            throw new ValidationException("Login id must not be null");
	        }
	        if (people.getEmail() == null) {
	        	
	        	throw new ValidationException("Email value cannot be null");
	        }
	        
	        if (people.getFirstName() == null) {
	        	throw new ValidationException("First Name value cannot be null");
	        }
	        if (people.getLastName() == null) {
	        	throw new ValidationException("Last Name value cannot be null");
	        }
	        
	        if(isValidEmail(people.getEmail())==false) {
	        	
	        	 System.out.println("Email not valid: " + people.getEmail());
	        	throw new ValidationException("Email not valid");
	        }
	        
	        if(isValidFirstName(people.getFirstName())==false) {
	        	
	        	System.out.println("First Name not valid: " + people.getFirstName());
	        	throw new ValidationException("First Name not valid");
	        }
	        if(isValidgender(people.getSex())==false) {
	        	System.out.println("gender not valid :" + people.getSex());
	        	throw new ValidationException("Gender not Valid");
	        }
	        
	        if(isValidLastName(people.getLastName())==false) {
	        	System.out.println("Last Name not valid: " + people.getLastName());
	        	throw new ValidationException("Last Name not valid");
	        }
	        
	        if(minChar(people.getJobTitle())==false) {
	        	System.out.println("max 100 characters only" + people.getLastName());
	        	throw new ValidationException("max 100 characters only" );
	        }
//	        
//	        if(isValidDob(people.getDob())==false) {
//	        	System.out.println("Date of birth not valid: " + people.getDob());
//	        	throw new ValidationException("Date of Birth not valid");
//	        }
	  
//	        Set<ConstraintViolation<People>> constraintViolations =  validator.validate(people);
//	        if(constraintViolations.size() > 0) {
//	            generateValidationException(constraintViolations);
//	        }
		
		return people;
	}

    
  	 private Boolean isValidEmail(String emaildata) {
	        String emailRegex ="^(.+)@(.+)$";  		 
  			Pattern pattern = Pattern.compile(emailRegex);  	
  			Matcher matcher = pattern.matcher(emaildata);
			return emaildata != null &&  emaildata.matches(emailRegex);
  		
	    }
  	 
  	 private Boolean isValidFirstName(String firstname) {
	        String regex ="^[A-Za-z]+";  		 
			Pattern pattern = Pattern.compile(regex);  
			Matcher matcher = pattern.matcher(firstname);
			return firstname != null &&  firstname.matches(regex);
		
	    }
  	 
	 private Boolean isValidLastName(String lastname) {
	        String regex ="^[A-Za-z]+";  		 
			Pattern pattern = Pattern.compile(regex);  
			Matcher matcher = pattern.matcher(lastname);
			return lastname != null &&  lastname.matches(regex);
		
	    }
	 

	 private Boolean isValidgender(String gender) {
	        String regex ="^(?:m|M|male|Male|f|F|female|Female)$";  		 
			Pattern pattern = Pattern.compile(regex);  
			Matcher matcher = pattern.matcher(gender);
			return gender != null &&  gender.matches(regex);
		
	    }
	 
//	 private Boolean isValidDob(String dob) {
//	        String regex ="^(\\d{1,2}\\/){2}\\d{4}\\s+((\\d+)(\\:)){2}\\d+\\s+(AM|PM)";	 
//			Pattern pattern = Pattern.compile(regex);  
//			Matcher matcher = pattern.matcher(dob);
//			return dob != null &&  dob.matches(regex);
//		
//	    }
//  	 

	 

	 private Boolean minChar(String jobtitle) {
	        String regex ="^[\\s.]*([^\\s.][\\s.]*){0,100}$";  		 
			Pattern pattern = Pattern.compile(regex);  
			Matcher matcher = pattern.matcher(jobtitle);
			return jobtitle != null &&  jobtitle.matches(regex);
		
	    }
}
