package com.nisum.users.infrastructure.inbound.controllers.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
@Configuration
@PropertySource("classpath:application.properties")
public class DynamicPatternValidator implements ConstraintValidator<DynamicPattern, String> {
    @Autowired
    private Environment environment;
    private String regex;
    private String message;
    @Override
    public void initialize(DynamicPattern constraintAnnotation) {
        this.regex = environment.getProperty(constraintAnnotation.regexKey());
        this.message = constraintAnnotation.message();
    }

	public boolean isValid(String chars, ConstraintValidatorContext context) {
		java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(this.regex);
		context.buildConstraintViolationWithTemplate(this.message)
				.addConstraintViolation().disableDefaultConstraintViolation();
		return pattern.matcher(chars).matches();
	}
}