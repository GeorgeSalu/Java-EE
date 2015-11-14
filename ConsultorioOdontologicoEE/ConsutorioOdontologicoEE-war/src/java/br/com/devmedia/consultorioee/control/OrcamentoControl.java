package br.com.devmedia.consultorioee.control;

import br.com.devmedia.consultorioee.entities.Anaminese;
import br.com.devmedia.consultorioee.entities.Customer;
import br.com.devmedia.consultorioee.entities.Orcamento;
import br.com.devmedia.consultorioee.entities.Orcamentoitem;
import br.com.devmedia.consultorioee.entities.PaymentType;
import br.com.devmedia.consultorioee.entities.Service;
import br.com.devmedia.consultorioee.entities.Users;
import br.com.devmedia.consultorioee.service.OrcamentoService;
import br.com.devmedia.consultorioee.service.ServiceService;
import br.com.devmedia.consultorioee.service.UserService;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;


@Named
@SessionScoped
public class OrcamentoControl extends BasicControl implements java.io.Serializable {
    
    @EJB
    private OrcamentoService orcamentoService;
    @Inject
    private AnamneseControl anamneseControl;
    @EJB
    private UserService userService;
    @EJB
    private ServiceService serviceService;

    public OrcamentoControl() {
    }
    private Customer selectedCustomer;
    private Orcamento selectedOrcamento;
    private Orcamentoitem selectedOrcamentoItem;

    private List<Orcamento> orcamentos;

    public OrcamentoService getOrcamentoService() {
        return orcamentoService;
    }

    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    public void setSelectedCustomer(Customer selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }

    public void setOrcamentoService(OrcamentoService orcamentoService) {
        this.orcamentoService = orcamentoService;
    }

    public Orcamento getSelectedOrcamento() {
        return selectedOrcamento;
    }

    public void setSelectedOrcamento(Orcamento selectedOrcamento) {
        this.selectedOrcamento = selectedOrcamento;
    }

    public List<Orcamento> getOrcamentos() {
        return orcamentos;
    }

    public void setOrcamentos(List<Orcamento> orcamentos) {
        this.orcamentos = orcamentos;
    }

    public String doStartAtenderOCliente(Customer customer) {
        setSelectedCustomer(customer);
        anamneseControl.setSelectedCustomer(selectedCustomer);
        anamneseControl.cleanCache();
        cleanCache();
        return "/restrito/orcamentos.faces";
    }

    public String getItensOfOrcamento(Orcamento orc) {
        StringBuilder sb = new StringBuilder();
        for (Orcamentoitem item : orc.getOrcamentoitemList()) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(item.getOriService().getSrvName());
        }
        return sb.toString();
    }

    private void cleanCache() {
        setSelectedOrcamento(new Orcamento());
        getSelectedOrcamento().setOrcCustomer(getSelectedCustomer());
        setOrcamentos(orcamentoService.getOrcamentos(getSelectedCustomer().getCusId()));
    }

    public String doStartOrcamentoComUmaAnamnese(Anaminese ans) {
        cleanCache();
        getSelectedOrcamento().setOrcAnaminese(ans);
        ans.setAnsOrcamento(selectedOrcamento);
        return "/restrito/addOrcamento.faces";
    }

    public List<Users> getDentistas() {
        return userService.getDentistUsers();
    }

    public List<PaymentType> getPaymentTypes() {
        return Arrays.asList(PaymentType.values());
    }

    public Orcamentoitem getSelectedOrcamentoItem() {
        return selectedOrcamentoItem;
    }

    public void setSelectedOrcamentoItem(Orcamentoitem selectedOrcamentoItem) {
        this.selectedOrcamentoItem = selectedOrcamentoItem;
    }

    public List<Service> getServices() {
        return serviceService.getServices();
    }

    public String doStartAddItemAoOrcamento() {
        selectedOrcamentoItem = new Orcamentoitem();
        selectedOrcamentoItem.setOriOrcamento(selectedOrcamento);
        return "/restrito/addOrcamentoItem.faces";
    }

    public String doFinishExcluirItem() {
        selectedOrcamento.getOrcamentoitemList().remove(selectedOrcamentoItem);
        recalcularSaldoOrcamento();
        return "/restrito/addOrcamento.faces";
    }

    public String doFinishAddOrcamentoItem() {
        selectedOrcamentoItem.setOriCost(selectedOrcamentoItem.getTotalItemParcial());
        selectedOrcamento.getOrcamentoitemList().add(selectedOrcamentoItem);
        recalcularSaldoOrcamento();
        return "/restrito/addOrcamento.faces";
    }
    
    public String doFinishAddOrcamento() {
        if (!selectedOrcamento.getOrcpaymentType().equals(PaymentType.CREDITO)) {
            selectedOrcamento.setOrcTimes(1);
        }
        if (selectedOrcamento.getOrcpaymentType().equals(PaymentType.CREDITO)) {
           if (selectedOrcamento.getOrcTimes() == null || selectedOrcamento.getOrcTimes() <= 0) {
               FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Voce precisa selecionar quantas vezes quer fazer no credito", "Voce precisa selecionar quantas vezes quer fazer no credito");
               FacesContext.getCurrentInstance().addMessage("prestacoes", fm);
               return "/restrito/addOrcamento.faces";
           }
        }
        selectedOrcamento.setOrcCustomer(selectedCustomer);
        orcamentoService.addOrcamento(selectedOrcamento);
        cleanCache();
        return "/restrito/orcamentos.faces";
    }
    

    private void recalcularSaldoOrcamento() {
        BigDecimal total = BigDecimal.ZERO;
        for (Orcamentoitem item : selectedOrcamento.getOrcamentoitemList()) {
            total = total.add(item.getOriCost());
        }
        selectedOrcamento.setOrcTotal(total);
    }

    public String doStartAlterarItemOrcamento() {
        return "/restrito/editOrcamentoItem.faces";
    }

    public String doFinishEditOrcamentoItem() {
        if (selectedOrcamentoItem.getOriId() != null) {
            orcamentoService.setItem(selectedOrcamentoItem);
        }
        selectedOrcamentoItem.setOriCost(selectedOrcamentoItem.getTotalItemParcial());
        recalcularSaldoOrcamento();
        return "/restrito/addOrcamento.faces";
    }

}