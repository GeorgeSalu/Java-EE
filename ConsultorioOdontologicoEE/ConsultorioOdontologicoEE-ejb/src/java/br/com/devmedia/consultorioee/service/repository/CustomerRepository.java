/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.service.repository;

import br.com.devmedia.consultorioee.entities.Customer;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author George
 */
public class CustomerRepository extends BasicRepository{

        private static final long serialVersionUID = 1L;

    public CustomerRepository(EntityManager entityManager) {
        super(entityManager);
    }

    public Customer addCustomer(Customer customer) {
        return addEntity(Customer.class, customer);
    }

    public Customer setCustomer(Customer customer) {
        return setEntity(Customer.class, customer);
    }
    

    public void removeCustomer(Customer customer) {
        removeEntity(customer);
    }

    public Customer getCustomer(int idOfCustomer) {
        return getEntity(Customer.class, idOfCustomer);
    }

    public List<Customer> getCustomerByName(String name) {
        return getPureList(Customer.class, "select cus from Customer cus where cus.cusName like ?1", name + "%");
    }

    public List<Customer> getCustomersToCall(int month, int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, (month - 1));
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date dataInicial = cal.getTime();

        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DAY_OF_YEAR, -1);
        Date dataFinal = cal.getTime();

        return getPureList(Customer.class, "select orc.orcCustomer from Orcamento orc where orc.orcDate >= ?1 and orc.orcDate <= ?2", dataInicial, dataFinal);
    }

    public List<Customer> getCustomersComPagamentoEmAberto(int idOfCustomer) {
        return getPureList(Customer.class, "select par.parOrcamento.orcCustomer from Parcela par where par.parPago = ?1 and par.parOrcamento.orcCustomer.cusId = ?2", Boolean.FALSE,idOfCustomer);
    }

    public Date getUltimoAtendimento(int idOfCustomer) {
        try {
            Date toReturn = getPurePojo(Date.class, "select max(orc.orcDate) from Orcamento orc where orc.orcCustomer.cusId = ?1", idOfCustomer);
            return toReturn;
        } catch (Exception e ) {
            e.printStackTrace();
            return null;
        }
    }
    
    public int getCustomersCount() {
        Long toReturn = getPurePojo(Long.class,"select count(cus) from Customer cus");
        if (toReturn != null) return toReturn.intValue();
        return 0;
    }

}
