
package br.com.devmedia.consultorioee.control;

import br.com.devmedia.consultorioee.entities.Categoriaimagem;
import br.com.devmedia.consultorioee.service.CategoriaImagemService;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named
@SessionScoped
public class CategoriaImagemControl extends BasicControl implements java.io.Serializable {

    @EJB
    private CategoriaImagemService categoriaImagemService;

    private List<Categoriaimagem> catimagens;
    private Categoriaimagem selectedCategoriaimagem;
    private String localizar;

    public Categoriaimagem getSelectedCategoriaimagem() {
        return selectedCategoriaimagem;
    }

    public void setSelectedCategoriaimagem(Categoriaimagem selectedCategoriaimagem) {
        this.selectedCategoriaimagem = selectedCategoriaimagem;
    }

    public String getLocalizar() {
        return localizar;
    }

    public void setLocalizar(String localizar) {
        this.localizar = localizar;
    }

    public List<Categoriaimagem> getCategoriaimagems() {
        if (catimagens == null) {
            localizar = "%";
            doLocalizar();
            localizar = "";
        }
        return catimagens;
    }

    public String doLocalizar() {
        cleanCache();
        catimagens = categoriaImagemService.getCategoriaimagemByName(localizar);
        return "/restrito/catimagens.faces";
    }

    private void cleanCache() {
        catimagens = new LinkedList<>();
        setSelectedCategoriaimagem(new Categoriaimagem());
    }

    public String doStartAddCategoria() {
        cleanCache();
        return "/restrito/addCatimagem.faces";
    }

    public String doFinishAddCategoria() {
        selectedCategoriaimagem = categoriaImagemService.addCategoriaimagem(selectedCategoriaimagem);
        localizar = "%";
        doLocalizar();
        localizar = "";
        return "/restrito/catimagens.faces";
    }

    public String doStartAlterar() {
        return "/restrito/editCatimagem.faces";
    }
    
    public String doFinishAlterar() {
        selectedCategoriaimagem = categoriaImagemService.setCategoriaimagem(selectedCategoriaimagem);
        localizar = "%";
        doLocalizar();
        localizar = "";
        return "/restrito/catimagens.faces";
    }

    
}
