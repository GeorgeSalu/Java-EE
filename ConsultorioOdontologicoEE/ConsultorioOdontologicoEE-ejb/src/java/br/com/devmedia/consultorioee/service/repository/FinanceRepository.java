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
    
    public List<Parcela> getParcelasOfOrcamento(int idOfOrcamento) {
        return getPureList(Parcela.class,"select par from Parcela par where par.parOrcamento.orcId = ?1",idOfOrcamento);
    }
    
    public List<Parcela> getParcelasOfOrcamentoEmAberto(int idOfOrcamento) {
        return getPureList(Parcela.class,"select par from Parcela par where par.parOrcamento.orcId = ?1 and par.parPago = ?2",idOfOrcamento,Boolean.FALSE);
    }
    
    public List<Parcela> getParcelasOfOrcamentoPagas(int idOfOrcamento) {
        return getPureList(Parcela.class,"select par from Parcela par where par.parOrcamento.orcId = ?1 and par.parPago = ?2",idOfOrcamento,Boolean.TRUE);
    }
    
    public List<Parcela> getParcelasOfCustomer(int idOfCustomer) {
        return getPureList(Parcela.class, "select par from Parcela par where par.parOrcamento.orcCustomer.cusId = ?1", idOfCustomer);
    }
    
    public List<Parcela> getParcelasOfCustomerEmAberto(int idOfCustomer) {
        return getPureList(Parcela.class,"select par from Parcela par where par.parOrcamento.orcCustomer.cusId = ?1 and par.parPago = ?2", idOfCustomer,Boolean.FALSE);
    }
    
    public List<Parcela> getParcelasOfCustomerPagas(int idOfCustomer) {
        return getPureList(Parcela.class,"select par from Parcela par where par.parOrcamento.orcCustomer.cusId = ?1 and par.parPago = ?2", idOfCustomer,Boolean.TRUE);
    }
    
    public Parcela setPagamentoParcela(int idOfParcela) {
        Parcela par = getParcela(idOfParcela);
        par.setParPago(true);
        par = setParcela(par);
        return par;
    }
    
    
}
