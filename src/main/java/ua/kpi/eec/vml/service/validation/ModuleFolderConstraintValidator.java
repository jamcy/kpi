package ua.kpi.eec.vml.service.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ua.kpi.eec.vml.service.validation.constraint.ModuleFolderUnique;

public class ModuleFolderConstraintValidator implements ConstraintValidator<ModuleFolderUnique, String> {

	@Override
	public void initialize(ModuleFolderUnique name) {}
	
	@Override
	public boolean isValid(String name, ConstraintValidatorContext ctx) {
		return name.equals("hello");
	}

}
