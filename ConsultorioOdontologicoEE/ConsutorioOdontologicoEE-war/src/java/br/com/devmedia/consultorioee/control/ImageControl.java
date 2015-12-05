package br.com.devmedia.consultorioee.control;

import br.com.devmedia.consultorioee.entities.Imagem;
import br.com.devmedia.consultorioee.service.ImageService;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class ImageControl extends BasicControl implements java.io.Serializable {

    @EJB
    private ImageService imageService;
    
    private List<Imagem> images;
    private Imagem selectedImagem;
    

}
