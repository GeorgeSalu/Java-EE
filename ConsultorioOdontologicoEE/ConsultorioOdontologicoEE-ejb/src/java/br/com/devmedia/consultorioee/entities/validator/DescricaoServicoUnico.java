package br.com.devmedia.consultorioee.entities.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = DescricaoServicoUnicoValidator.class)
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DescricaoServicoUnico {
   String message() default "Ja existe um serviço com este nome cadastrado";
   Class<?>[] groups() default { };
   Class<? extends Payload>[] payload() default { };
}
