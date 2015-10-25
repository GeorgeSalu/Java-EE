package br.com.devmedia.consultorioee.entities.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = LoginPadraoValidator.class)
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginPadrao {

   String message() default "Login invalido";
   Class<?>[] groups() default { };
   Class<? extends Payload>[] payload() default { };
    
}
