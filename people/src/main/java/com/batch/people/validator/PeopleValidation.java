package com.batch.people.validator;

import java.util.Set;
import java.util.regex.Pattern;

import javax.validation.ConstraintViolation;

import org.springframework.batch.item.validator.ValidationException;

import com.batch.people.entity.People;

public class PeopleValidation {
	
	 private boolean isValidEmail(String email) {
	        // Add validation logic for email (e.g., valid format using regular expression)
	       String emailRegex = null;// "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
	        return email != null && email.matches(emailRegex);
	    }

	 private boolean isValidId(Long id) {
	        return id > 0;
	    }

	    private boolean isValidName(String name) {	   
	        return name != null && !name.trim().isEmpty();
	    }

	    private boolean isValidLoginId(String loginId) {
	
	        return loginId != null && !loginId.trim().isEmpty();
	    }

	
	   private javax.validation.Validator validator;

		public void validate(People people) throws ValidationException {
			
			 if (people.getpeopleId() == null) {

		            throw new ValidationException("people id must not be null");
		        }
		        if (people.getLoginId() == null) {
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
		        
		        if(isValidEmail(people.getEmail())==true) {
		        	throw new ValidationException("Email not valid");
		        }
		        
		        Set<ConstraintViolation<People>> constraintViolations =  validator.validate(people);
		        if(constraintViolations.size() > 0) {
		            generateValidationException(constraintViolations);
		        }
		
			  
	  	}

		
	  
		
		
		private void generateValidationException(Set<ConstraintViolation<People>> constraintViolations) {
			StringBuilder message = new StringBuilder();

	        for (ConstraintViolation<People> constraintViolation : constraintViolations) {
	            message.append(constraintViolation.getMessage() + "\n");
	        }

	        throw new ValidationException(message.toString());
			
		}
		
		
		  public boolean isValid(People people) {
			  
		        return isValidId(people.getpeopleId()) &&
		                isValidName(people.getFirstName()) &&
		                isValidLoginId(people.getLoginId()) &&
		                isValidEmail(people.getEmail());
		    }
		
		
		

}
