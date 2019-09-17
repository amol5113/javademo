package com.javatpoint.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.javatpoint.model.Emp;

@Component
public class EmployeeValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Emp.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "userName", "error.userName.empty");
		
		ValidationUtils.rejectIfEmpty(errors, "Password", "error.Password.empty");
		ValidationUtils.rejectIfEmpty(errors, "email", "error.email.empty");
		ValidationUtils.rejectIfEmpty(errors, "userType", "error.userType.empty");
		
			Emp e =(Emp) target;
		
	      Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
	            Pattern.CASE_INSENSITIVE);
	      if (!(pattern.matcher(e.getEmail()).matches())) {
	         errors.rejectValue("email", "user.email.invalid");
	      }

		
		/*
		 * ValidationUtils.rejectIfEmpty(errors, "accountno", "user.accountno.empty");
		 * ValidationUtils.rejectIfEmpty(errors, "email", "user.email.empty");
		 * ValidationUtils.rejectIfEmpty(errors, "ifsccode", "user.ifsccode.empty");
		 * ValidationUtils.rejectIfEmpty(errors, "dob", "user.dob.empty");
		 * ValidationUtils.rejectIfEmpty(errors, "interest", "user.interest.empty");
		 * ValidationUtils.rejectIfEmpty(errors, "salary", "user.salary.empty");
		 * ValidationUtils.rejectIfEmpty(errors, "designation",
		 * "user.designation.empty");
		 */
		



		/*
		 * Pattern empat = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
		 * Pattern.CASE_INSENSITIVE); if (!(empat.matcher(e.getEmail()).matches())) {
		 * errors.rejectValue("email", "user.email.invalid"); }
		 * 
		 * Pattern nmpat = Pattern.compile("[a-zA-Z\\s]+",Pattern.CASE_INSENSITIVE);
		 * 
		 * if(!(nmpat.matcher(e.getUserName()).matches())) { errors.rejectValue("name",
		 * "user.name.invalid"); } Pattern ifscpat =
		 * Pattern.compile("^[A-Za-z]{4}\\d{7}$",Pattern.CASE_INSENSITIVE);
		 *
		 *
		 * if(!(ifscpat.matcher(e.getIfsccode()).matches())) {
		 * errors.rejectValue("ifsccode", "user.ifsccode.invalid"); }
		 */
		
		


	}

}
