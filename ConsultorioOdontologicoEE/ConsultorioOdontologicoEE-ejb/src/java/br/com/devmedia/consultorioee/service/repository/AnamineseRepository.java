/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.service.repository;

import br.com.devmedia.consultorioee.entities.Anaminese;
import br.com.devmedia.consultorioee.entities.Customer;
import br.com.devmedia.consultorioee.entities.Orcamento;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author George
 */
public class AnamineseRepository extends BasicRepository{
    
    public AnamineseRepository(EntityManager entityManager) {
        super(entityManager);
    }
    
    public Anaminese getAnaminese(int idOfAnamnese) {
        return getEntity(Anaminese.class, idOfAnamnese);
    }

    public Anaminese setAnaminese(Anaminese anaminese) {
        return setEntity(Anaminese.class, anaminese);
    }
    
    public Anaminese addAnaminese(Anaminese anaminese) {
        return addEntity(Anaminese.class, anaminese);
    }
    
    public void removeAnamnese(Anaminese anaminese) {
        removeEntity(anaminese);
    }
    
    public List<Anaminese> getAnaminesesByCustomer(Customer customer) {
        return getPureList(Anaminese.class,"select anam from Anaminese anam wher anam.ansCustomer.cusId = ?1",customer.getCusId());
    }

    public List<Anaminese> getAnaminesesByOrcamento(Orcamento orc) {
        return getPureList(Anaminese.class,"select anam from Anaminese anam where anam.ansOrcamento.orcId = ?1",orc.getOrcId());
    }
    
}
