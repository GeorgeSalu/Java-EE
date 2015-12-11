
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
        executeCommand("DELETE FROM Imagem img where img.imgId = ?1", imagem.getImgId());
    }
    
    public Imagem getImagem(int idOfImagem) {
        return getEntity(Imagem.class, idOfImagem);
    }
    
    public List<Imagem> getImagensOfOrcamento(int idOrcamento) {
        return getPureList(Imagem.class,"select new br.com.devmedia.consultorioee.entities.Imagem(img.imgId,img.imgDescricao,img.imgdataInclusao,img.imghoraInclusao,img.imgCategoria,img.imgOrcamento) from Imagem img where img.imgOrcamento.orcId = ?1",idOrcamento);
    }

    public List<Imagem> getImagensOfOrcamento(int idOrcamento, int idCategoria) {
        return getPureList(Imagem.class,"select new br.com.devmedia.consultorioee.entities.Imagem(img.imgId,img.imgDescricao,img.imgdataInclusao,img.imghoraInclusao,img.imgCategoria,img.imgOrcamento) from Imagem img where img.imgOrcamento.orcId = ?1 and img.imgCategoria.cigId = ?2",idOrcamento,idCategoria);
    }

}
