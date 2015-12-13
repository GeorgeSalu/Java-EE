package br.com.devmedia.consultorioee.entities.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author George Salu

 */
@Constraint(validatedBy = SomenteImagemValidaValidator.class)
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SomenteImagemValida {

   String message() default "Imagem Inv�lida";
   Class<?>[] groups() default { };
   Class<? extends Payload>[] payload() default { };
    
}
