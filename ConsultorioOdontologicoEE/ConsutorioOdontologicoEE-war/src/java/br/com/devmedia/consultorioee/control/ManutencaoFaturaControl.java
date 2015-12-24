package br.com.devmedia.consultorioee.control;

import br.com.devmedia.consultorioee.entities.Customer;
import br.com.devmedia.consultorioee.service.CustomerService;
import br.com.devmedia.consultorioee.service.InfoMDB;
import br.com.devmedia.consultorioee.service.ManutencaoFaturaService;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

/**
 *
 * @author George Salu
 */
@Named
@SessionScoped
public class ManutencaoFaturaControl extends BasicControl implements java.io.Serializable {

    @EJB
    private CustomerService customerService;
    @EJB
    private ManutencaoFaturaService manutencaoFaturaService;
    private String localizar;
    private List<Customer> customers = new LinkedList<Customer>();
    private List<Customer> selectedCustomers = new LinkedList<Customer>();
    private Customer selectedCustomer;
    private int selectedTipoEnvio = 1;

    public String doLocalizar() {
        setCustomers(customerService.getCustomerByName(localizar));
        return "/restrito/faturas.faces";
    }
    
    public boolean isProcessando() {
        return (manutencaoFaturaService.getInfoMDB() != null && !manutencaoFaturaService.getInfoMDB().isConcluido());
    }
    
    public InfoMDB getInfoMDB() {
        return manutencaoFaturaService.getInfoMDB();
    }
    
    public List<SelectItem> getTiposEnvio() {
        List<SelectItem> toReturn = new LinkedList<SelectItem>();
        toReturn.add(new SelectItem(1,"Imprimir PDF"));
        toReturn.add(new SelectItem(2,"Enviar por Email"));
        toReturn.add(new SelectItem(3,"Imprimir PDF e Enviar por Email"));
        return toReturn;
    }
    

    public String doDown() {
        if (customers == null || customers.isEmpty()) {
            createFacesErrorMessage("Nenhum cliente localizado.");
        } else {
             List<Customer> paraRemover = new LinkedList<Customer>();
            for (Customer customer : customers) {
                if (customer.isSelecionado()) {
                    selectedCustomers.add(customer);
                    paraRemover.add(customer);
                }
            }
            for (Customer obj : paraRemover) {
                customers.remove(obj);
            }
            createFacesInfoMessage("Clientes adicionados com sucesso !");
        }
        return "/restrito/faturas.faces";
    }

    public String doRemove() {
        if (selectedCustomers == null || selectedCustomers.isEmpty()) {
            createFacesErrorMessage("Nenhum cliente adicionado.");
        } else {
            List<Customer> paraRemover = new LinkedList<Customer>();
            for (Customer customer : selectedCustomers) {
                if (customer.isSelecionado()) {
                    paraRemover.add(customer);
                }
            }
            for (Customer obj : paraRemover) {
                selectedCustomers.remove(obj);
            }
            createFacesInfoMessage("Clientes removidos da seleção com sucesso !");
        }
        return "/restrito/faturas.faces";
    }

    public String doEnviarFatuasManualmente() {
        if (selectedCustomers == null || selectedCustomers.isEmpty()) {
            createFacesErrorMessage("Nenhum cliente selecionado para processamento.");
            return "/restrito/faturas.faces";
        }
        createFacesInfoMessage("Requisição enviada para a fila.");
        manutencaoFaturaService.processarEnvioDeFaturas(selectedCustomers,getSelectedTipoEnvio());
        return "/restrito/faturas.faces?faces-redirect=true";
    }
    
    public String getLocalizar() {
        return localizar;
    }

    public void setLocalizar(String localizar) {
        this.localizar = localizar;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    public void setSelectedCustomer(Customer selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }

    public List<Customer> getSelectedCustomers() {
        return selectedCustomers;
    }

    public void setSelectedCustomers(List<Customer> selectedCustomers) {
        this.selectedCustomers = selectedCustomers;
    }

    public int getSelectedTipoEnvio() {
        return selectedTipoEnvio;
    }

    public void setSelectedTipoEnvio(int selectedTipoEnvio) {
        this.selectedTipoEnvio = selectedTipoEnvio;
    }

    
}
