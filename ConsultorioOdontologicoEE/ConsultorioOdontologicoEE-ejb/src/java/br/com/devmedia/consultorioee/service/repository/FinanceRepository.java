/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.service.repository;

import br.com.devmedia.consultorioee.entities.Parcela;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author George
 */
public class FinanceRepository extends BasicRepository{

    private static final long serialVersionUID = 1L;
    
    public FinanceRepository(EntityManager entityManager) {
        super(entityManager);
    }
    
    public Parcela getParcela(int idParcela) {
        return getEntity(Parcela.class, idParcela);
    }
    
    public Parcela setParcela(Parcela par) {
        return setEntity(Parcela.class, par);
    }
    
    public Parcela addParcela(Parcela par) {
        return addEntity(Parcela.class, par);
    }
    
    public void removeParcela(Parcela par) {
        removeEntity(par);
    }
    
    public List<Parcela> getParcelas(int idOfOrcamento) {
        return getPureList(Parcela.class,"select par from Parcela par where par.parOrcamento.orcId = ?1",idOfOrcamento);
    }
    
    public List<Parcela> getParcelasEmAberto(int idOfOrcamento) {
        return getPureList(Parcela.class,"select par from Parcela par where par.parOrcamento.orcId = ?1 and par.parPago = ?2",idOfOrcamento,Boolean.FALSE);
    }
    
    public List<Parcela> getParcelasPagas(int idOfOrcamento) {
        return getPureList(Parcela.class,"select par from Parcela par where par.parOrcamento.orcId = ?1 and par.parPago = ?2",idOfOrcamento,Boolean.TRUE);
    }
    
    
}
