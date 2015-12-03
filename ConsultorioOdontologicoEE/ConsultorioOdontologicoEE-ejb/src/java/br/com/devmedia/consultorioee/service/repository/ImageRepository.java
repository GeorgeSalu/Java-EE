
package br.com.devmedia.consultorioee.service.repository;

import br.com.devmedia.consultorioee.entities.Imagem;
import java.util.List;
import javax.persistence.EntityManager;

public class ImageRepository extends BasicRepository {

    private static final long serialVersionUID = 1L;
    
    public ImageRepository(EntityManager entityManager) {
        super(entityManager);
    }
    
    public Imagem addImagem(Imagem imagem) {
        return addEntity(Imagem.class, imagem);
    }
    
    public Imagem setImagem(Imagem imagem) {
        return setEntity(Imagem.class, imagem);
    }
    
    public void removeImagem(Imagem imagem) {
        removeEntity(imagem);
    }
    
    public Imagem getImagem(int idOfImagem) {
        return getEntity(Imagem.class, idOfImagem);
    }
    
    public List<Imagem> getImagensOfOrcamento(int idOrcamento) {
        return null;
    }

}
