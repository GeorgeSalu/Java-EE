/*
 * Copyright (C) 2014 dyego.carmo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package br.com.devmedia.consultorioee.service;

import br.com.devmedia.consultorioee.entities.Customer;
import br.com.devmedia.consultorioee.entities.Orcamento;
import br.com.devmedia.consultorioee.entities.Orcamentoitem;
import br.com.devmedia.consultorioee.entities.PaymentType;
import br.com.devmedia.consultorioee.entities.Service;
import br.com.devmedia.consultorioee.entities.Users;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author dyego.carmo
 */
public class OrcamentoServiceTest {
    
     private static EJBContainer container;
    private OrcamentoService instance;
    private CustomerService instaceCustomer;
    private UserService instanceUser;
    private ServiceService instanceService;
    private Customer customerOne;
    private Users userOne;
    private Service serviceOne;
    private Service serviceTwo;
    private Orcamento orcamentoOne;
    private Orcamento orcamentoTwo;
    private Orcamentoitem orcamentoItemOfOne;
    private Orcamentoitem orcamentoItemOfTwo;

    public OrcamentoServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
    }
    
    @AfterClass
    public static void tearDownClass() {
        container.close();
        container = null;
    }
    
    @Before
    public void setUp() throws NamingException {
        instance = (OrcamentoService)container.getContext().lookup("java:global/classes/OrcamentoService");
        // Mock Object One
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
        customerOne.setCusworkName("WorkName "+new Random().nextInt());
        customerOne.setCusworkNumber(new Random().nextInt()+"");
        customerOne.setCusworkObs("WorkObs "+new Random().nextInt());
        // Mock User Object
        userOne = new Users();
        userOne.setUsuAdministrator(new Random().nextBoolean());
        userOne.setUsuDentist(new Random().nextBoolean());
        userOne.setUsuLogin("test.LoginOne"+new Random().nextInt());
        userOne.setUsuName("test.NameOne "+new Random().nextInt());
        userOne.setUsuPassword(userOne.getUsuLogin());
    
        // Mock Orcamento Object
        orcamentoOne = new Orcamento();
        orcamentoOne.setOrcCustomer(customerOne);
        orcamentoOne.setOrcDate(new Date());
        orcamentoOne.setOrcDentist(userOne);
        orcamentoOne.setOrcHour(new Date());
        orcamentoOne.setOrcObs("Obs "+new Random().nextInt());
        orcamentoOne.setOrcTimes(new Random().nextInt(10));
        orcamentoOne.setOrcTotal(new BigDecimal(Math.abs(new Random().nextDouble())));
        orcamentoOne.setOrcpaymentType(PaymentType.CREDITO);
        // Mock Service Object- One
        serviceOne = new Service();
        serviceOne.setSrvName("Test Orcamento Service One "+new Random().nextInt());
        serviceOne.setSrvCost(orcamentoOne.getOrcTotal());
        // Mock Of Item
        orcamentoItemOfOne = new Orcamentoitem();
        orcamentoItemOfOne.setOriCost(orcamentoOne.getOrcTotal());
        orcamentoItemOfOne.setOriObs("Obs Item "+new Random().nextInt());
        orcamentoItemOfOne.setOriService(serviceOne);
        orcamentoOne.addItem(orcamentoItemOfOne);
        
        
        // Mock Orcamento Object
        orcamentoTwo = new Orcamento();
        orcamentoTwo.setOrcCustomer(customerOne);
        orcamentoTwo.setOrcDate(new Date());
        orcamentoTwo.setOrcDentist(userOne);
        orcamentoTwo.setOrcHour(new Date());
        orcamentoTwo.setOrcObs("Obs "+new Random().nextInt());
        orcamentoTwo.setOrcTimes(new Random().nextInt(5));
        orcamentoTwo.setOrcTotal(new BigDecimal(Math.abs(new Random().nextDouble())));
        orcamentoTwo.setOrcpaymentType(PaymentType.CREDITO);
        // Mock Service Object- One
        serviceTwo = new Service();
        serviceTwo.setSrvName("Test Orcamento Service Two "+new Random().nextInt());
        serviceTwo.setSrvCost(orcamentoTwo.getOrcTotal());
        // Mock Of Item
        orcamentoItemOfTwo = new Orcamentoitem();
        orcamentoItemOfTwo.setOriCost(orcamentoTwo.getOrcTotal());
        orcamentoItemOfTwo.setOriObs("Obs Item Two "+new Random().nextInt());
        orcamentoItemOfTwo.setOriService(serviceTwo);
        orcamentoOne.addItem(orcamentoItemOfTwo);
        
        // Persist
        orcamentoOne = instance.addOrcamento(orcamentoOne);
        orcamentoTwo = instance.addOrcamento(orcamentoTwo);
    }
    
    @After
    public void tearDown() {
        instance.removeOrcamento(orcamentoOne);
        instance.removeOrcamento(orcamentoTwo);
        instance = null;
    }

    /**
     * Test of addOrcamento method, of class OrcamentoService.
     */
    @Test
    public void testAddOrcamento() throws Exception {
        System.out.println("addOrcamento");
        // Mock Orcamento Object
        Orcamento orc = null;
        orc = new Orcamento();
        orc.setOrcCustomer(customerOne);
        orc.setOrcDate(new Date());
        orc.setOrcDentist(userOne);
        orc.setOrcHour(new Date());
        orc.setOrcObs("Obs "+new Random().nextInt());
        orc.setOrcTimes(new Random().nextInt(10));
        orc.setOrcTotal(new BigDecimal(Math.abs(new Random().nextDouble())));
        orc.setOrcpaymentType(PaymentType.CREDITO);
        orc = instance.addOrcamento(orc);
        Orcamento result = instance.addOrcamento(orc);
        Orcamento expResult = instance.getOrcamento(result.getOrcId());
        assertEquals(expResult, result);
        instance.removeOrcamento(orc);
    }

    /**
     * Test of setOrcamento method, of class OrcamentoService.
     */
    @Test
    public void testSetOrcamento() throws Exception {
        System.out.println("setOrcamento");
        Orcamento orc = orcamentoTwo;
        String newObs = "the new obs "+new Random().nextFloat();
        orc.setOrcObs(newObs);
        Orcamento result = instance.setOrcamento(orc);
        assertEquals(newObs, result.getOrcObs());
    }

    /**
     * Test of getOrcamento method, of class OrcamentoService.
     */
    @Test
    public void testGetOrcamento() throws Exception {
        System.out.println("getOrcamento");
        Integer idOfOrcamento = orcamentoOne.getOrcId();
        Orcamento expResult = orcamentoOne;
        Orcamento result = instance.getOrcamento(idOfOrcamento);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeOrcamento method, of class OrcamentoService.
     */
    @Test
    public void testRemoveOrcamento() throws Exception {
        System.out.println("removeOrcamento");
        Orcamento orc = null;
        // Mock Orcamento Object
        orc = new Orcamento();
        orc.setOrcCustomer(customerOne);
        orc.setOrcDate(new Date());
        orc.setOrcDentist(userOne);
        orc.setOrcHour(new Date());
        orc.setOrcObs("Obs "+new Random().nextInt());
        orc.setOrcTimes(new Random().nextInt(10));
        orc.setOrcTotal(new BigDecimal(Math.abs(new Random().nextDouble())));
        orc.setOrcpaymentType(PaymentType.CREDITO);
        orc = instance.addOrcamento(orc);
        instance.removeOrcamento(orc);
    }

    /**
     * Test of addItem method, of class OrcamentoService.
     */
    @Test
    public void testAddItem() throws Exception {
        System.out.println("addItem");
        Orcamentoitem item = null;
        // Mock Service Object- One
        serviceOne = new Service();
        serviceOne.setSrvName("Test Orcamento Service testAddItem "+new Random().nextInt());
        serviceOne.setSrvCost(orcamentoOne.getOrcTotal());
        // Mock Of Item
        item = new Orcamentoitem();
        item.setOriCost(orcamentoOne.getOrcTotal());
        item.setOriObs("Obs Item  testAddItem "+new Random().nextInt());
        item.setOriService(serviceOne);
        Orcamentoitem result = instance.addItem(item);
        Orcamentoitem expResult = instance.getItem(item.getOriId());
        assertEquals(expResult, result);
    }

    /**
     * Test of setItem method, of class OrcamentoService.
     */
    @Test
    public void testSetItem() throws Exception {
        System.out.println("setItem");
        Orcamentoitem item = orcamentoItemOfTwo;
        String newObs = "the new obs "+new Random().nextFloat();
        item.setOriObs(newObs);
        Orcamentoitem result = instance.setItem(item);
        Orcamentoitem expResult = instance.getItem(result.getOriId());
        assertEquals(expResult.getOriObs(), newObs);
    }

    /**
     * Test of getItem method, of class OrcamentoService.
     */
    @Test
    public void testGetItem() throws Exception {
        System.out.println("getItem");
        Integer idOfItem = orcamentoItemOfOne.getOriId();
        Orcamentoitem expResult = orcamentoItemOfOne;
        Orcamentoitem result = instance.getItem(idOfItem);
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrcamentos method, of class OrcamentoService.
     */
    @Test
    public void testGetOrcamentos() throws Exception {
        System.out.println("getOrcamentos");
        Integer idofCustomer = customerOne.getCusId();
        List<Orcamento> result = instance.getOrcamentos(idofCustomer);
        assertTrue(result.size() >=2);
    }

    /**
     * Test of getItens method, of class OrcamentoService.
     */
    @Test
    public void testGetItens() throws Exception {
        System.out.println("getItens");
        Integer idOfOrcamento = orcamentoOne.getOrcId();
        List<Orcamentoitem> result = instance.getItens(idOfOrcamento);
        assertTrue(result.size() >=2);
    }

    @Test
    public void testGetUltimoOrcamentoByCliente() throws Exception {
        System.out.println("getUltimoOrcamentoByCliente");
        Integer idOfCustomer = customerOne.getCusId();
        Orcamento expResult = orcamentoTwo;
        Orcamento result = instance.getUltimoOrcamentoByCliente(idOfCustomer);
        assertEquals(expResult, result);
    }
    
}
