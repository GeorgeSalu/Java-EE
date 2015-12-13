package br.com.devmedia.consultorioee.entities.validator;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author George Salu
 */
public class SomenteImagemValidaValidator implements ConstraintValidator<SomenteImagemValida, byte[]> {

    @Override
    public void initialize(SomenteImagemValida constraintAnnotation) {
    }

    @Override
    public boolean isValid(byte[] value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        try {
            BufferedImage imagem = ImageIO.read(new ByteArrayInputStream(value));
            System.out.println("[SomenteImagemValidaValidator] Imagem lida "+imagem);
            return imagem != null;
        } catch (Exception e) {
            return false;
        }
    }

}
