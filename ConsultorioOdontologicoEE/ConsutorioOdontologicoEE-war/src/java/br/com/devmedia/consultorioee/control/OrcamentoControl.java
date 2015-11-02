package br.com.devmedia.consultorioee.control;

import br.com.devmedia.consultorioee.entities.Orcamento;
import br.com.devmedia.consultorioee.service.OrcamentoService;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named
@SessionScoped
public class OrcamentoControl extends BasicControl implements java.io.Serializable {
    

    @EJB
    private OrcamentoService orcamentoService;
    private Orcamento selectedOrcamento;
    private List<Orcamento> orcamentos;

    public OrcamentoService getOrcamentoService() {
        return orcamentoService;
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
    
}
