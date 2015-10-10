/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.service;

import br.com.devmedia.consultorioee.entities.Customer;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author George
 */
public class CustomerServiceTest {
    
    private EJBContainer container;
    private CustomerService instance;
    private Customer customerOne;
    private Customer customerTwo;
    private Customer customerThree;
    
    public CustomerServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws NamingException {
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        instance = (CustomerService)container.getContext().lookup("java:global/classes/CustomerService");
        customerOne = new Customer();
        customerOne.setCusAddress("Address One "+new Random().nextInt());
        customerOne.setCusAge(Math.abs(new Random().nextInt(99)));
        customerOne.setCusBorndate(new Date());
        customerOne.setCusCity("City "+new Random().nextInt());
        customerOne.setCusComplement("Complement "+new Random().nextInt());
        customerOne.setCusFather("Father "+new Random().nextInt());
        customerOne.setCusMother("Mother "+new Random().nextInt());
        customerOne.setCusName("Customer Name One "+new Random().nextInt());
        customerOne.setCusObs("Obs "+new Random().nextInt());
        customerOne.setCusOcupation("Ocupation "+new Random().nextInt());
        customerOne.setCusState("XX");
        customerOne.setCusTelephone("Tel "+new Random().nextInt());
        customerOne.setCuscelNumber("Cel "+new Random().nextInt());
        customerOne.setCusworkAddress("Work address one "+new Random().nextInt());
    }
    
    @After
    public void tearDown() {
        instance.removeCustomer(customerOne);
        instance.removeCustomer(customerTwo);
        instance.removeCustomer(customerThree);
        instance = null;
        container.close();
        container = null;
    }

    /**
     * Test of addCustomer method, of class CustomerService.
     */
    @Test
    public void testAddCustomer() throws Exception {
        System.out.println("addCustomer");
        Customer cus = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CustomerService instance = (CustomerService)container.getContext().lookup("java:global/classes/CustomerService");
        Customer expResult = null;
        Customer result = instance.addCustomer(cus);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCustomer method, of class CustomerService.
     */
    @Test
    public void testSetCustomer() throws Exception {
        System.out.println("setCustomer");
        Customer cus = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CustomerService instance = (CustomerService)container.getContext().lookup("java:global/classes/CustomerService");
        Customer expResult = null;
        Customer result = instance.setCustomer(cus);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeCustomer method, of class CustomerService.
     */
    @Test
    public void testRemoveCustomer() throws Exception {
        System.out.println("removeCustomer");
        Customer cus = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CustomerService instance = (CustomerService)container.getContext().lookup("java:global/classes/CustomerService");
        instance.removeCustomer(cus);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCustomer method, of class CustomerService.
     */
    @Test
    public void testGetCustomer() throws Exception {
        System.out.println("getCustomer");
        int idOfCustomer = 0;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CustomerService instance = (CustomerService)container.getContext().lookup("java:global/classes/CustomerService");
        Customer expResult = null;
        Customer result = instance.getCustomer(idOfCustomer);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCustomerByName method, of class CustomerService.
     */
    @Test
    public void testGetCustomerByName() throws Exception {
        System.out.println("getCustomerByName");
        String nameOfCustomer = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CustomerService instance = (CustomerService)container.getContext().lookup("java:global/classes/CustomerService");
        List<Customer> expResult = null;
        List<Customer> result = instance.getCustomerByName(nameOfCustomer);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCustomersToCall method, of class CustomerService.
     */
    @Test
    public void testGetCustomersToCall() throws Exception {
        System.out.println("getCustomersToCall");
        int month = 0;
        int year = 0;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CustomerService instance = (CustomerService)container.getContext().lookup("java:global/classes/CustomerService");
        List<Customer> expResult = null;
        List<Customer> result = instance.getCustomersToCall(month, year);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCustomersComPagamentoEmAberto method, of class CustomerService.
     */
    @Test
    public void testGetCustomersComPagamentoEmAberto() throws Exception {
        System.out.println("getCustomersComPagamentoEmAberto");
        int ifOfCustomer = 0;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        CustomerService instance = (CustomerService)container.getContext().lookup("java:global/classes/CustomerService");
        List<Customer> expResult = null;
        List<Customer> result = instance.getCustomersComPagamentoEmAberto(ifOfCustomer);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
