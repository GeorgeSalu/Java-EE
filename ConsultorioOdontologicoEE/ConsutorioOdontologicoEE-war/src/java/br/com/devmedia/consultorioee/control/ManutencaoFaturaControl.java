package br.com.devmedia.consultorioee.control;

import br.com.devmedia.consultorioee.entities.Customer;
import br.com.devmedia.consultorioee.service.CustomerService;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
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
    private String localizar;
    private List<Customer> customers = new LinkedList<Customer>();
    private List<Customer> selectedCustomers = new LinkedList<Customer>();
    private Customer selectedCustomer;

    
    public String doLocalizar() {
        setCustomers(customerService.getCustomerByName(localizar));
        return "/restrito/faturas.faces";
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
    
}
