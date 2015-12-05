package br.com.devmedia.consultorioee.control;

import br.com.devmedia.consultorioee.entities.Categoriaimagem;
import br.com.devmedia.consultorioee.entities.Imagem;
import br.com.devmedia.consultorioee.entities.Orcamento;
import br.com.devmedia.consultorioee.service.CategoriaImagemService;
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
    @EJB
    private CategoriaImagemService categoriaService;
    
    private List<Imagem> images;
    private Imagem selectedImagem;
    private Categoriaimagem selectedCategoria;
    
    private Orcamento selectedOrcamento;
    private Part arquivoImagem;

    public Part getArquivoImagem() {
        return arquivoImagem;
    }

    public void setArquivoImagem(Part arquivoImagem) {
        this.arquivoImagem = arquivoImagem;
    }

    public String doCadastrarImagem() {
        selectedImagem = new Imagem();
        selectedImagem.setImgOrcamento(selectedOrcamento);
        return "/restrito/addImages.faces";
    }
    
    public String doFinishCadastrarImagem() throws IOException {
        byte[] arqBytes = new byte[(int)arquivoImagem.getSize()];
        arquivoImagem.getInputStream().read(arqBytes);
        selectedImagem.setImgImagem(arqBytes);
        
        return "/restrito/viewImages.faces";
    }
    
    public Categoriaimagem getSelectedCategoria() {
        return selectedCategoria;
    }

    public void setSelectedCategoria(Categoriaimagem selectedCategoria) {
        this.selectedCategoria = selectedCategoria;
    }


    
    public List<Categoriaimagem> getCategorias() {
        return categoriaService.getCategoriasDeImagem();
    }
    
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
