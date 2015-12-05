
package br.com.devmedia.consultorioee.control.converter;

import br.com.devmedia.consultorioee.entities.Categoriaimagem;
import br.com.devmedia.consultorioee.service.CategoriaImagemService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@FacesConverter(value = "categoriaImagemConverter")
public class CategoriaImagemConverter implements Converter {
    
    CategoriaImagemService categoriaImagemService = lookupCategoriaImagemServiceBean();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) return null;
        return categoriaImagemService.getCategoriaimagemByName(value).get(0);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) return null;
        return ((Categoriaimagem)value).getCigNome();
    }

    private CategoriaImagemService lookupCategoriaImagemServiceBean() {
        try {
            Context c = new InitialContext();
            return (CategoriaImagemService) c.lookup("java:global/ConsutorioOdontologicoEE/ConsutorioOdontologicoEE-ejb/CategoriaImagemService!br.com.devmedia.consultorioee.service.CategoriaImagemService");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
