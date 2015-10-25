package br.com.devmedia.consultorioee.entities.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LoginPadraoValidator implements ConstraintValidator<LoginPadrao, String> {

    @Override
    public void initialize(LoginPadrao constraintAnnotation) {
        System.out.println("[LoginPadraoValidator] Carregado com a mensagem - "+constraintAnnotation.message());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return (value != null && value.contains(".") && value.charAt(value.length()-1) != '.');
    }

}
