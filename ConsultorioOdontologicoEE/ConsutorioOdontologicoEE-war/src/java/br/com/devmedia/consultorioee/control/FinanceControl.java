/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.control;

import br.com.devmedia.consultorioee.entities.Customer;
import br.com.devmedia.consultorioee.entities.Parcela;
import br.com.devmedia.consultorioee.service.FinanceService;
import br.com.devmedia.consultorioee.service.OrcamentoService;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author dyego.carmo
 */
@Named
@SessionScoped
public class FinanceControl extends BasicControl implements java.io.Serializable {
 
    @EJB
    private OrcamentoService orcamentoService;
    @Inject
    private OrcamentoControl orcamentoControl;
    @EJB
    private FinanceService financeService;
    private Customer selectedCustomer;
    private List<Parcela> parcelas;

    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    public void setSelectedCustomer(Customer selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }

    public List<Parcela> getParcelas() {
        return parcelas;
    }

    public void setParcelas(List<Parcela> parcelas) {
        this.parcelas = parcelas;
    }


    public void cleanCache() {
        setParcelas(financeService.getParcelasOfCustomer(getSelectedCustomer().getCusId()));
    }
    
    
    
    
    
    
 
    
}
