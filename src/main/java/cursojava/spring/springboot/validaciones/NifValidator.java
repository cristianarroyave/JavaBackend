package cursojava.spring.springboot.validaciones;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NifValidator implements ConstraintValidator<Nif, String>{

	@Override
	public void initialize(Nif constraintAnnotation) {}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return !(value != null && value.trim().equals("") && value.matches("^\\d{8}[A-Z]$"));
	}
	
}
