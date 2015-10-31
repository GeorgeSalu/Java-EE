/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.devmedia.consultorioee.control;

import br.com.devmedia.consultorioee.entities.Customer;
import br.com.devmedia.consultorioee.service.CustomerService;
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
        customers = customerService.getCustomerByName(localizar);
        return "/restrito/customers.faces";
    }
    
    
    

}
