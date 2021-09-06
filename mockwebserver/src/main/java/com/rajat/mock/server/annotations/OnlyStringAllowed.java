package com.rajat.mock.server.annotations;

import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.CONSTRUCTOR;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;



@Target({METHOD,FIELD ,PARAMETER,CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@ReportAsSingleViolation
@Pattern(regexp = "^[a-zA-Z]*$")
public @interface OnlyStringAllowed {
	String message() default "Only Strings without space";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};

}
