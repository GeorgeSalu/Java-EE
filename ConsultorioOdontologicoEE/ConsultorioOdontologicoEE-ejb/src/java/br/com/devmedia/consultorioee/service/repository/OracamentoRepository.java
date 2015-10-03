/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.service.repository;

import br.com.devmedia.consultorioee.entities.Orcamento;
import br.com.devmedia.consultorioee.entities.Orcamentoitem;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author George
 */
public class OracamentoRepository extends BasicRepository{

    private static final long serialVersionUID = 1L;

    public OracamentoRepository(EntityManager entityManager) {
        super(entityManager);
    }
    
    public Orcamento getOrcamento(int idOfOrcamento) {
        return getEntity(Orcamento.class, idOfOrcamento);
    }
    
    public Orcamento addOrcamento(Orcamento orc) {
        return addEntity(Orcamento.class, orc);
    }
    
    public Orcamento setOrcamento(Orcamento orc) {
        return setEntity(Orcamento.class, orc);
    }
    
    public void removeOrcamento(Orcamento orc) {
        removeEntity(orc);
    }

    public void removeItem(Orcamentoitem ori) {
        removeEntity(ori);
    }
    
    public Orcamentoitem addItem(Orcamentoitem ori) {
        return addEntity(Orcamentoitem.class, ori);
    }
    
    public Orcamentoitem setItem(Orcamentoitem ori) {
        return setEntity(Orcamentoitem.class, ori);
    }
    
    public Orcamentoitem getItem(int idofOri) {
        return getEntity(Orcamentoitem.class, idofOri);
    }
    
    public List<Orcamento> getOrcamentos(int idOfCustomer) {
        return getPureList(Orcamento.class,"select orc from Orcamento orc where orc.orcCustomer.cusId = ?1",idOfCustomer);
    }
    
    public List<Orcamentoitem> getItens(int idOrcamento) {
        return getPureList(Orcamentoitem.class,"select ori from Orcamentoitem ori where ori.oriOrcamento.orcId = ?1",idOrcamento);
    }
    
}
