
package br.com.devmedia.consultorioee.service.repository;

import br.com.devmedia.consultorioee.entities.Categoriaimagem;
import br.com.devmedia.consultorioee.service.repository.BasicRepository;
import java.util.List;
import javax.persistence.EntityManager;


public class CategoriaImagemRepository extends BasicRepository {

    private static final long serialVersionUID = 1L;

    public CategoriaImagemRepository(EntityManager entityManager) {
        super(entityManager);
    }
    
    
    public Categoriaimagem addCategoriaimagem(Categoriaimagem categoriaimagem) {
        return addEntity(Categoriaimagem.class, categoriaimagem);
    }

    public Categoriaimagem setCategoriaimagem(Categoriaimagem categoriaimagem) {
        return setEntity(Categoriaimagem.class, categoriaimagem);
    }
    

    public void removeCategoriaimagem(Categoriaimagem categoriaimagem) {
        removeEntity(categoriaimagem);
    }

    public Categoriaimagem getCategoriaimagem(int idOfCategoriaimagem) {
        return getEntity(Categoriaimagem.class, idOfCategoriaimagem);
    }
    
    public List<Categoriaimagem> getCategoriasDeImagem() {
        return getPureList(Categoriaimagem.class, "select ci from Categoriaimagem ci");
    }

    public List<Categoriaimagem> getCategoriaimagemByName(String localizar) {
        return getPureList(Categoriaimagem.class, "select ci from Categoriaimagem ci where ci.cigNome like ?1",localizar+"%");
    }
    

}
