package br.com.devmedia.consultorioee.entities.validator;

import br.com.devmedia.consultorioee.entities.Service;
import br.com.devmedia.consultorioee.service.ServiceService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DescricaoServicoUnicoValidator implements ConstraintValidator<DescricaoServicoUnico, Service> {

    private final ServiceService serviceService = lookupServiceServiceBean();

    @Override
    public void initialize(DescricaoServicoUnico constraintAnnotation) {
        System.out.println("[DescricaoServicoUnicoValidator] Carregado com a mensagem - "+constraintAnnotation.message());
    }

    @Override
    public boolean isValid(Service value, ConstraintValidatorContext context) {
        if (value == null) { return true;}
        List<Service> serviceWithSameDescList = serviceService.getServicesByName(value.getSrvName());
        boolean valid = true;
        for (Service service : serviceWithSameDescList) {
            if (service.getSrvName().equalsIgnoreCase(value.getSrvName()) && !service.equals(value)) {
                valid = false;
                break;
            }
        }
        return valid;
    }

    private ServiceService lookupServiceServiceBean() {
        try {
            Context c = new InitialContext();
            return (ServiceService) c.lookup("java:global/ConsutorioOdontologicoEE/ConsutorioOdontologicoEE-ejb/ServiceService!br.com.devmedia.consultorioee.service.ServiceService");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    

}
