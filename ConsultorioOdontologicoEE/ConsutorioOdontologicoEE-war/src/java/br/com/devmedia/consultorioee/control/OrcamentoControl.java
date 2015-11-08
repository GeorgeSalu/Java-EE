package br.com.devmedia.consultorioee.control;

import br.com.devmedia.consultorioee.entities.Anaminese;
import br.com.devmedia.consultorioee.entities.Customer;
import br.com.devmedia.consultorioee.entities.Orcamento;
import br.com.devmedia.consultorioee.entities.Orcamentoitem;
import br.com.devmedia.consultorioee.entities.PaymentType;
import br.com.devmedia.consultorioee.entities.Users;
import br.com.devmedia.consultorioee.service.OrcamentoService;
import br.com.devmedia.consultorioee.service.UserService;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
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

    public OrcamentoControl() {
    }
    private Customer selectedCustomer;
    private Orcamento selectedOrcamento;
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
            if (sb.length() >0) {
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
    
    public PaymentType[] getPaymentTypes() {
        return PaymentType.values();
    }
}
