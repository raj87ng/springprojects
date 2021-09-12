package com.rajat.springrest.annotations;

import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;



@Target({FIELD ,PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@ReportAsSingleViolation
@Pattern(regexp = "^[0-9]*$")
public @interface OnlyDigitsAllowed {
	String message() default "Only digits allowed";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};

}
