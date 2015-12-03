

package br.com.devmedia.consultorioee.service;

import br.com.devmedia.consultorioee.entities.Categoriaimagem;
import br.com.devmedia.consultorioee.service.repository.CategoriaImagemRepository;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.PostActivate;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CategoriaImagemService extends BasicService {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;
    
    private CategoriaImagemRepository categoriaRepository;

    @PostActivate
    @PostConstruct
    private void postConstruct() {
        categoriaRepository = new CategoriaImagemRepository(em);
    }
    
    
    public Categoriaimagem addCategoriaimagem(Categoriaimagem categoriaimagem) {
        return categoriaRepository.addCategoriaimagem(categoriaimagem);
    }

    public Categoriaimagem setCategoriaimagem(Categoriaimagem categoriaimagem) {
        return categoriaRepository.setCategoriaimagem(categoriaimagem);
    }
    

    public void removeCategoriaimagem(Categoriaimagem categoriaimagem) {
        categoriaRepository.removeCategoriaimagem(categoriaimagem);
    }

    public Categoriaimagem getCategoriaimagem(int idOfCategoriaimagem) {
        return categoriaRepository.getCategoriaimagem(idOfCategoriaimagem);
    }
    
    public List<Categoriaimagem> getCategoriasDeImagem() {
        return categoriaRepository.getCategoriasDeImagem();
    }

    public List<Categoriaimagem> getCategoriaimagemByName(String localizar) {
        return categoriaRepository.getCategoriaimagemByName(localizar);
    }
    

}
