package com.rajat.springparallelwebclients.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;



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
