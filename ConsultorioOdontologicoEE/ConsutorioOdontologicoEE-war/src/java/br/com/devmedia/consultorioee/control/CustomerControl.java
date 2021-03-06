/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.devmedia.consultorioee.control;

import br.com.devmedia.consultorioee.entities.Customer;
import br.com.devmedia.consultorioee.service.CustomerService;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named
@SessionScoped
public class CustomerControl extends BasicControl implements java.io.Serializable {

    @EJB
    private CustomerService customerService;
    
    private List<Customer> customers;
    private Customer selectedCustomer;
    private String localizar;

    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    public void setSelectedCustomer(Customer selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
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

    public String doLocalizar() {
        cleanCache();
        customers = customerService.getCustomerByName(localizar);
        return "/restrito/customers.faces";
    }
    
    private void cleanCache() {
        customers = new LinkedList<>();
        setSelectedCustomer(new Customer());
    }

    public int getCustomersCount() {
        return customerService.getCustomersCount();
    }
    
    public String getUltimoAtendimento(Integer idOfCustomer) {
        Date toReturn = customerService.getUltimoAtendimento(idOfCustomer);
        if (toReturn == null) {
            return "Nunca.";
        }
        return getSdf().format(toReturn);
    }
    
    public String doStartAddCustomer() {
        cleanCache();
        return "/restrito/addCustomer.faces";
    }
    
    public String doStartAlterar() {
        return "/restrito/editCustomer.faces";
    }
    
    public String doFinishAlterar() {
        if (existsViolationsForJSF(getSelectedCustomer())) {
            return "/restrito/editCustomer.faces";
        }
        customerService.setCustomer(getSelectedCustomer());
        customerService.refreshCustomer(getSelectedCustomer());
        setSelectedCustomer(null);
        cleanCache();doLocalizar();
        return "/restrito/customers.faces";
    }
    
    public String doFinishAddCustomer() {
        if (existsViolationsForJSF(getSelectedCustomer())) {
            return "/restrito/addCustomer.faces";
        }
        Customer cus = customerService.addCustomer(selectedCustomer);
        cleanCache();
        getCustomers().add(cus);
        setLocalizar(cus.getCusName());
        return "/restrito/customers.faces";
    }
    
    public String doFinishExcluir() {
        customerService.removeCustomer(selectedCustomer);
        getCustomers().remove(getSelectedCustomer());
        return "/restrito/customers.faces";
    }
}
