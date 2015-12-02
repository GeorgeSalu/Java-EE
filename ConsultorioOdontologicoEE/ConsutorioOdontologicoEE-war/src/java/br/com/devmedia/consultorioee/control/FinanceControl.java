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
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import net.sf.jasperreports.engine.JRException;

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
    private Parcela selectedParcela;

    private List<Parcela> parcelas;

    public Parcela getSelectedParcela() {
        return selectedParcela;
    }

    public void setSelectedParcela(Parcela selectedParcela) {
        this.selectedParcela = selectedParcela;
    }

    public String doPagar() {
        financeService.setPagamentoParcela(getSelectedParcela().getParId());
        cleanCache();
        return "/restrito/orcamentos.faces";
    }

    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }
    
    public String doSendEmailWithInvoice(Customer customer,Parcela par) throws JRException, IOException {
        financeService.sendEmailTo(customer, par);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Email enviado com sucesso !","Email enviado com sucesso !"));
        return null;
    }

    public void setSelectedCustomer(Customer selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }

    public List<Parcela> getParcelas() {
        return parcelas;
    }

    public List<Parcela> getParcelas(Customer cus) {
        this.setSelectedCustomer(cus);
        cleanCache();
        return parcelas;
    }

    public void setParcelas(List<Parcela> parcelas) {
        this.parcelas = parcelas;
    }

    public void cleanCache() {
        setParcelas(financeService.getParcelasOfCustomerEmAberto(getSelectedCustomer().getCusId()));
    }

    public String doImprimirBoleto() throws JRException, IOException {
        byte[] boleto = financeService.getPDF(getSelectedParcela());
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        ec.responseReset();
        ec.setResponseContentType("application/pdf");
        ec.setResponseContentLength(boleto.length);
        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"parcela_" + getSelectedParcela().getParNumero() + "_"+getSelectedParcela().getParOrcamento().getOrcCustomer().getCusId()+".pdf\"");
        OutputStream output = ec.getResponseOutputStream();
        output.write(boleto);
        fc.responseComplete();
        return null;
    }

    
}
