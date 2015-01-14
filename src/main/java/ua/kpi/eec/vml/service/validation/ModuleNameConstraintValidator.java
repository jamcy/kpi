package ua.kpi.eec.vml.service.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ua.kpi.eec.vml.service.validation.constraint.ModuleName;

public class ModuleNameConstraintValidator implements ConstraintValidator<ModuleName, String> {

	@Override
	public void initialize(ModuleName name) {}
	
	@Override
	public boolean isValid(String name, ConstraintValidatorContext ctx) {
		return name.equals("hello");
	}

}
