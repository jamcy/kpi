package ua.kpi.eec.vml.service.validation.constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import ua.kpi.eec.vml.service.validation.ModuleNameConstraintValidator;

@Documented
@Constraint(validatedBy = ModuleNameConstraintValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ModuleName {
	
    String message() default "{Year}";
     
    Class<?>[] groups() default {};
     
    Class<? extends Payload>[] payload() default {};
}
