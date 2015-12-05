package br.com.devmedia.consultorioee.control;

import br.com.devmedia.consultorioee.entities.Imagem;
import br.com.devmedia.consultorioee.entities.Orcamento;
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
    private Orcamento selectedOrcamento;
    
    
    public String doViewImages() {
        
        return "/restrito/viewImages.faces";
        
    }

    public List<Imagem> getImages() {
        return images;
    }

    public void setImages(List<Imagem> images) {
        this.images = images;
    }

    public Imagem getSelectedImagem() {
        return selectedImagem;
    }

    public void setSelectedImagem(Imagem selectedImagem) {
        this.selectedImagem = selectedImagem;
    }

    public Orcamento getSelectedOrcamento() {
        return selectedOrcamento;
    }

    public void setSelectedOrcamento(Orcamento selectedOrcamento) {
        this.selectedOrcamento = selectedOrcamento;
    }
    
}
