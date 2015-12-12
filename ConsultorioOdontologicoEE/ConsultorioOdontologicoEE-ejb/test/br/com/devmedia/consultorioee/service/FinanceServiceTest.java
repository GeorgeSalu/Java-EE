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
import br.com.devmedia.consultorioee.entities.Parcela;
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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dyego.carmo
 */
public class FinanceServiceTest {
    
    private static EJBContainer container;
    private FinanceService instance;
    private Parcela parcelaOne;
    private Parcela parcelaTwo;
    private Parcela parcelaThree;

    private Customer customerOne;
    private Users userOne;
    private Service serviceOne;
    private Orcamento orcamentoOne;
    private Orcamentoitem orcamentoItemOfOne;
    
    public FinanceServiceTest() {
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
        instance = (FinanceService)container.getContext().lookup("java:global/classes/FinanceService");
        
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
        // Mock of User
        userOne = new Users();
        userOne.setUsuAdministrator(new Random().nextBoolean());
        userOne.setUsuDentist(new Random().nextBoolean());
        userOne.setUsuLogin("testLoginOne"+new Random().nextInt());
        userOne.setUsuName("testNameOne "+new Random().nextInt());
        userOne.setUsuPassword(userOne.getUsuLogin());
        // Mock Orcamento Object
        orcamentoOne = new Orcamento();
        orcamentoOne.setOrcCustomer(customerOne);
        orcamentoOne.setOrcDate(new Date());
        orcamentoOne.setOrcDentist(userOne);
        orcamentoOne.setOrcHour(new Date());
        orcamentoOne.setOrcObs("Obs "+new Random().nextInt());
        orcamentoOne.setOrcTimes(3);
        orcamentoOne.setOrcTotal(BigDecimal.valueOf(new Random().nextInt(9999)));
        orcamentoOne.setOrcpaymentType(PaymentType.CREDITO);
        // Mock Service Object- One
        serviceOne = new Service();
        serviceOne.setSrvName("Test Finance Service One "+new Random().nextInt());
        serviceOne.setSrvCost(orcamentoOne.getOrcTotal());
        // Mock Of Item
        orcamentoItemOfOne = new Orcamentoitem();
        orcamentoItemOfOne.setOriCost(orcamentoOne.getOrcTotal());
        orcamentoItemOfOne.setOriObs("Obs Finance Item "+new Random().nextInt());
        orcamentoItemOfOne.setOriService(serviceOne);
        orcamentoOne.addItem(orcamentoItemOfOne);
        // Mock of Parcela
        
        BigDecimal vlrParcela = orcamentoOne.getOrcTotal().divide(BigDecimal.valueOf(3));
        parcelaOne = new Parcela();
        parcelaOne.setParNumero(1);
        parcelaOne.setParOrcamento(orcamentoOne);
        parcelaOne.setParPago(true);
        parcelaOne.setParValue(vlrParcela);
        parcelaTwo = new Parcela();
        parcelaTwo.setParNumero(2);
        parcelaTwo.setParOrcamento(orcamentoOne);
        parcelaTwo.setParPago(false);
        parcelaTwo.setParValue(vlrParcela);
        parcelaThree = new Parcela();
        parcelaThree.setParNumero(3);
        parcelaThree.setParOrcamento(orcamentoOne);
        parcelaThree.setParPago(false);
        parcelaThree.setParValue(vlrParcela);
        
        // Persist
        parcelaOne = instance.addParcela(parcelaOne);
        parcelaTwo = instance.addParcela(parcelaTwo);
        parcelaThree = instance.addParcela(parcelaThree);
    }
    
    @After
    public void tearDown() {
        instance.removeParcela(parcelaOne);
        instance.removeParcela(parcelaTwo);
        instance.removeParcela(parcelaThree);
        instance = null;
    }

    /**
     * Test of addParcela method, of class FinanceService.
     */
    @Test
    public void testAddParcela() throws Exception {
        System.out.println("addParcela");
        Parcela par = null;
        Parcela expResult = null;
        Parcela result = instance.addParcela(par);
        assertEquals(expResult, result);
    }

    /**
     * Test of getParcela method, of class FinanceService.
     */
    @Test
    public void testGetParcela() throws Exception {
        System.out.println("getParcela");
        Integer idOfParcela = parcelaOne.getParId();
        Parcela expResult = parcelaOne;
        Parcela result = instance.getParcela(idOfParcela);
        assertEquals(expResult, result);
    }

    /**
     * Test of setParcela method, of class FinanceService.
     */
    @Test
    public void testSetParcela() throws Exception {
        System.out.println("setParcela");
        Parcela par = null;
        Parcela expResult = null;
        Parcela result = instance.setParcela(par);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeParcela method, of class FinanceService.
     */
    @Test
    public void testRemoveParcela() throws Exception {
        System.out.println("removeParcela");
        Parcela par = null;
        instance.removeParcela(par);
        container.close();
    }

    /**
     * Test of getParcelasByOrcamento method, of class FinanceService.
     */
    @Test
    public void testGetParcelasByOrcamento() throws Exception {
        System.out.println("getParcelasByOrcamento");
        int orcamentoId = orcamentoOne.getOrcId();
        int expResult = orcamentoOne.getOrcTimes();
        List<Parcela> result = instance.getParcelasByOrcamento(orcamentoId);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of getParcelasOfOrcamentoPagas method, of class FinanceService.
     */
    @Test
    public void testGetParcelasOfOrcamentoPagas() throws Exception {
        System.out.println("getParcelasOfOrcamentoPagas");
        int orcamentoId = orcamentoOne.getOrcId();
        int expResult = 1;
        List<Parcela> result = instance.getParcelasOfOrcamentoPagas(orcamentoId);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of getParcelasOfOrcamentoEmAberto method, of class FinanceService.
     */
    @Test
    public void testGetParcelasOfOrcamentoEmAberto() throws Exception {
        System.out.println("getParcelasOfOrcamentoEmAberto");
        int orcamentoId = orcamentoOne.getOrcId();
        int expResult = 2;
        List<Parcela> result = instance.getParcelasOfOrcamentoEmAberto(orcamentoId);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of getParcelasOfCustomer method, of class FinanceService.
     */
    @Test
    public void testGetParcelasOfCustomer() throws Exception {
        System.out.println("getParcelasOfCustomer");
        int customerId = customerOne.getCusId();
        int expResult = 3;
        List<Parcela> result = instance.getParcelasOfCustomer(customerId);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of getParcelasOfCustomerPagas method, of class FinanceService.
     */
    @Test
    public void testGetParcelasOfCustomerPagas() throws Exception {
        System.out.println("getParcelasOfCustomerPagas");
        int orcamentoId = customerOne.getCusId();
        int expResult = 1;
        List<Parcela> result = instance.getParcelasOfCustomerPagas(orcamentoId);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of getParcelasOfCustomerEmAberto method, of class FinanceService.
     */
    @Test
    public void testGetParcelasOfCustomerEmAberto() throws Exception {
        System.out.println("getParcelasOfCustomerEmAberto");
        int orcamentoId = customerOne.getCusId();
        int expResult = 2;
        List<Parcela> result = instance.getParcelasOfCustomerEmAberto(orcamentoId);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of setPagamentoParcela method, of class FinanceService.
     */
    @Test
    public void testSetPagamentoParcela() throws Exception {
        System.out.println("setPagamentoParcela");
        int idOfParcela = parcelaTwo.getParId();
        boolean expResult = true;
        Parcela result = instance.setPagamentoParcela(idOfParcela);
        assertEquals(expResult, result.getParPago());
    }
    
}
