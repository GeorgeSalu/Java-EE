/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.service;

import br.com.devmedia.consultorioee.entities.Service;
import java.math.BigDecimal;
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
public class ServiceServiceTest {

    private EJBContainer container;
    private ServiceService instance;
    private Service srvOne;
    private Service srvTwo;
    private Service srvThree;

    
    public ServiceServiceTest() {
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
        instance = (ServiceService)container.getContext().lookup("java:global/classes/ServiceService");
        
        // Mock Service Object- One
        srvOne = new Service();
        srvOne.setSrvName("Service Name Of One "+new Random().nextInt());
        srvOne.setSrvCost(new BigDecimal(new Random().nextFloat()));
        // Mock Service Object - Two
        srvTwo = new Service();
        srvTwo.setSrvName("Service Name Of Two "+new Random().nextInt());
        srvTwo.setSrvCost(new BigDecimal(new Random().nextFloat()));
        // Mock Service Object - Three
        srvThree = new Service();
        srvThree.setSrvName("Service Name of Three "+new Random().nextInt());
        srvThree.setSrvCost(new BigDecimal(new Random().nextFloat()));
        // Persist
        srvOne   = instance.addService(srvOne);
        srvTwo   = instance.addService(srvTwo);
        srvThree = instance.addService(srvThree);

    
    }
    
    @After
    public void tearDown() {
        instance.removeService(srvOne);
        instance.removeService(srvTwo);
        instance.removeService(srvThree);
        instance = null;
        container.close();
        container = null;
    }

        /**
     * Test of addService method, of class ServiceService.
     */
    @Test
    public void testAddService() throws Exception {
        System.out.println("addService");
        Service service = null;
        Service expResult = null;
        Service result = instance.addService(service);
        assertEquals(expResult, result);
    }

    /**
     * Test of setService method, of class ServiceService.
     */
    @Test
    public void testSetService() throws Exception {
        System.out.println("setService");
        Service service = null;
        Service expResult = null;
        Service result = instance.setService(service);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeService method, of class ServiceService.
     */
    @Test
    public void testRemoveService() throws Exception {
        System.out.println("removeService");
        Service service = null;
        instance.removeService(service);
    }

    /**
     * Test of getService method, of class ServiceService.
     */
    @Test
    public void testGetService() throws Exception {
        System.out.println("getService");
        int idOfService = 0;
        Service expResult = null;
        Service result = instance.getService(idOfService);
        assertEquals(expResult, result);
    }

    /**
     * Test of getServices method, of class ServiceService.
     */
    @Test
    public void testGetServices() throws Exception {
        System.out.println("getServices");
        List<Service> expResult = null;
        List<Service> result = instance.getServices();
        assertEquals(expResult, result);
    }

    /**
     * Test of getServicesByName method, of class ServiceService.
     */
    @Test
    public void testGetServicesByName() throws Exception {
        System.out.println("getServicesByName");
        String name = "";
        List<Service> expResult = null;
        List<Service> result = instance.getServicesByName(name);
        assertEquals(expResult, result);
    }

}
