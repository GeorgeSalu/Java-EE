package br.com.devmedia.consultorioee.control;

import br.com.devmedia.consultorioee.entities.Anaminese;
import br.com.devmedia.consultorioee.entities.Customer;
import br.com.devmedia.consultorioee.service.AnamineseService;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class AnamneseControl extends BasicControl implements java.io.Serializable {
    
        @EJB 
    private AnamineseService anamneseService;
    private Anaminese selectedAnaminese;
    private Customer selectedCustomer;
    private List<Anaminese> anamneses;
    @Inject
    private OrcamentoControl orcamentoControl;

    public Anaminese getSelectedAnaminese() {
        return selectedAnaminese;
    }

    public void setSelectedAnaminese(Anaminese selectedAnaminese) {
        this.selectedAnaminese = selectedAnaminese;
    }

    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    public void setSelectedCustomer(Customer selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }

    public List<Anaminese> getAnamneses() {
        return anamneses;
    }

    public void setAnamneses(List<Anaminese> anamneses) {
        this.anamneses = anamneses;
    }
    
    public void cleanCache() {
        setSelectedAnaminese(new Anaminese());
        getSelectedAnaminese().setAnsCustomer(selectedCustomer);
        anamneses = anamneseService.getAnaminesesByCustomer(selectedCustomer);
    }
    
    public String doStartAddAnamnese() {
        cleanCache();
        return "/restrito/addAnamnese.faces";
    }
    
    public String doFinishAddAnamnese() {
        selectedAnaminese.setAnsCustomer(selectedCustomer);
        anamneseService.addAnaminese(selectedAnaminese);
        cleanCache();
        return "/restrito/orcamentos.faces";
    }
    
    public String doFinishExcluir() {
        anamneseService.removeAnaminese(selectedAnaminese);
        cleanCache();
        return "/restrito/orcamentos.faces";
    }

}
