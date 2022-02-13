package com.rajat.springparallelwebclients.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;



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
