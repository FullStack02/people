package com.batch.people.validator;

import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import com.batch.people.entity.People;


public class PeopleValidator implements Validator<People> {
	
	 private PeopleValidation peopleValidator;

	    public PeopleValidator() {
	        this.peopleValidator = new PeopleValidation();
	    }

		public void validate(People people) throws ValidationException {
			 if (!peopleValidator.isValid(people)) {
		            throw new ValidationException("Invalid data for People: " + people);
		        }
			 
		}
	
}
