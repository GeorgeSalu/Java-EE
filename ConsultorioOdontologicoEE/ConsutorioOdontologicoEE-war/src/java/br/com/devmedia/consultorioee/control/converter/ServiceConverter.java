/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.control.converter;

import br.com.devmedia.consultorioee.entities.Service;
import br.com.devmedia.consultorioee.service.ServiceService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author George
 */
@FacesConverter("serviceConverter")
public class ServiceConverter implements Converter {
    ServiceService serviceService = lookupServiceServiceBean();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) return null;
        return lookupServiceServiceBean().getServicesByExactName(value.toString());
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) return null;
        return ((Service)value).getSrvName();
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
