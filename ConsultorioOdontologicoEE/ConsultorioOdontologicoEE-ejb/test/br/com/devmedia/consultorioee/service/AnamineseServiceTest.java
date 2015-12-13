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

import br.com.devmedia.consultorioee.entities.Anaminese;
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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dyego.carmo
 */
public class AnamineseServiceTest {
     
    private static EJBContainer container;
    private AnamineseService instance;
    private Customer customerOne;
    private Users userOne;
    private Service serviceOne;
    private Service serviceTwo;
    private Orcamento orcamentoOne;
    private Orcamento orcamentoTwo;
    private Orcamentoitem orcamentoItemOfOne;
    private Orcamentoitem orcamentoItemOfTwo;
    private Anaminese anamineseOne;
    private Anaminese anamineseTwo;
    
    public AnamineseServiceTest() {
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
        instance = (AnamineseService)container.getContext().lookup("java:global/classes/AnamineseService");
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
        serviceOne.setSrvName("Test Anaminese Service One "+new Random().nextInt());
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
        orcamentoTwo.setOrcTimes(new Random().nextInt(10));
        orcamentoTwo.setOrcTotal(new BigDecimal(Math.abs(new Random().nextDouble())));
        orcamentoTwo.setOrcpaymentType(PaymentType.CREDITO);
        // Mock Service Object- One
        serviceTwo = new Service();
        serviceTwo.setSrvName("Test Anaminese Service Two "+new Random().nextInt());
        serviceTwo.setSrvCost(orcamentoTwo.getOrcTotal());
        // Mock Of Item
        orcamentoItemOfTwo = new Orcamentoitem();
        orcamentoItemOfTwo.setOriCost(orcamentoTwo.getOrcTotal());
        orcamentoItemOfTwo.setOriObs("Obs Item Two "+new Random().nextInt());
        orcamentoItemOfTwo.setOriService(serviceTwo);
        orcamentoTwo.addItem(orcamentoItemOfTwo);
        
        
        
        
        // Anaminese One
        anamineseOne = new Anaminese();
        anamineseOne.setAnsAlergia(new Random().nextBoolean());
        anamineseOne.setAnsCustomer(customerOne);
        anamineseOne.setAnsDst(new Random().nextBoolean());
        anamineseOne.setAnsFuma(new Random().nextBoolean());
        anamineseOne.setAnsObs("One Obs "+new Random().nextInt());
        anamineseOne.setAnsOrcamento(orcamentoOne);
        anamineseOne.setAnsdescricaoAlergia("One Desc "+new Random().nextInt());
        anamineseOne.setAnsdescricaoDoenca("One Desc Doenca "+new Random().nextInt());
        anamineseOne.setAnsdescricaoDst("One Desc Dst "+new Random().nextInt());
        anamineseOne.setAnsdescricaoMedicacaoContinua("One Medicacao Continua "+new Random().nextInt());
        anamineseOne.setAnsdescricaoOperacaoRecente("One Operacao Re "+new Random().nextInt());
        anamineseOne.setAnsdoencaHereditaria(new Random().nextBoolean());
        anamineseOne.setAnsmedicacaoContinua(new Random().nextBoolean());
        anamineseOne.setAnsoperacaoRecente(new Random().nextBoolean());
        anamineseOne.setAnspraticaExercicio(new Random().nextBoolean());
        
        // Anaminese Two
        anamineseTwo = new Anaminese();
        anamineseTwo.setAnsAlergia(new Random().nextBoolean());
        anamineseTwo.setAnsCustomer(customerOne);
        anamineseTwo.setAnsDst(new Random().nextBoolean());
        anamineseTwo.setAnsFuma(new Random().nextBoolean());
        anamineseTwo.setAnsObs("Two Obs "+new Random().nextInt());
        anamineseTwo.setAnsOrcamento(orcamentoTwo);
        anamineseTwo.setAnsdescricaoAlergia("Two Desc "+new Random().nextInt());
        anamineseTwo.setAnsdescricaoDoenca("Two Desc Doenca "+new Random().nextInt());
        anamineseTwo.setAnsdescricaoDst("Two Desc Dst "+new Random().nextInt());
        anamineseTwo.setAnsdescricaoMedicacaoContinua("Two Medicacao Continua "+new Random().nextInt());
        anamineseTwo.setAnsdescricaoOperacaoRecente("Two Operacao Re "+new Random().nextInt());
        anamineseTwo.setAnsdoencaHereditaria(new Random().nextBoolean());
        anamineseTwo.setAnsmedicacaoContinua(new Random().nextBoolean());
        anamineseTwo.setAnsoperacaoRecente(new Random().nextBoolean());
        anamineseTwo.setAnspraticaExercicio(new Random().nextBoolean());
        
        
        
        // Persist
        anamineseOne = instance.addAnaminese(anamineseOne);
        anamineseTwo = instance.addAnaminese(anamineseTwo);
    }
    
    @After
    public void tearDown() {
        instance.removeAnaminese(anamineseOne);
        instance.removeAnaminese(anamineseTwo);
        instance = null;
    }

    /**
     * Test of getAnaminese method, of class AnamineseService.
     */
    @Test
    public void testGetAnaminese() throws Exception {
        System.out.println("getAnaminese");
        Integer idOfAnamnese = anamineseOne.getAnsId();
        Anaminese expResult = anamineseOne;
        Anaminese result = instance.getAnaminese(idOfAnamnese);
        assertEquals(expResult, result);
        assertNotSame(anamineseTwo, result);
    }

    /**
     * Test of addAnaminese method, of class AnamineseService.
     */
    @Test
    public void testAddAnaminese() throws Exception {
        System.out.println("addAnaminese");
        // Mock Orcamento Object
        Orcamento orcPrivate = new Orcamento();
        orcPrivate.setOrcCustomer(customerOne);
        orcPrivate.setOrcDate(new Date());
        orcPrivate.setOrcDentist(userOne);
        orcPrivate.setOrcHour(new Date());
        orcPrivate.setOrcObs("Obs "+new Random().nextInt());
        orcPrivate.setOrcTimes(new Random().nextInt(10));
        orcPrivate.setOrcTotal(new BigDecimal(Math.abs(new Random().nextDouble())));
        orcPrivate.setOrcpaymentType(PaymentType.CREDITO);
        // Mock Service Object- One
        Service servicePrivate = new Service();
        servicePrivate.setSrvName("Test Anaminese Service Private "+new Random().nextInt());
        servicePrivate.setSrvCost(orcPrivate.getOrcTotal());
        // Mock Of Item
        Orcamentoitem itemPrivate = new Orcamentoitem();
        itemPrivate.setOriCost(orcPrivate.getOrcTotal());
        itemPrivate.setOriObs("Obs Item Two "+new Random().nextInt());
        itemPrivate.setOriService(servicePrivate);
        orcPrivate.addItem(itemPrivate);

        Anaminese an = new Anaminese();
        an.setAnsAlergia(new Random().nextBoolean());
        an.setAnsCustomer(customerOne);
        an.setAnsDst(new Random().nextBoolean());
        an.setAnsFuma(new Random().nextBoolean());
        an.setAnsObs("Two Add Obs "+new Random().nextInt());
        an.setAnsOrcamento(orcPrivate);
        an.setAnsdescricaoAlergia("Two  Add Desc "+new Random().nextInt());
        an.setAnsdescricaoDoenca("Two Add Desc Doenca "+new Random().nextInt());
        an.setAnsdescricaoDst("Two Add Desc Dst "+new Random().nextInt());
        an.setAnsdescricaoMedicacaoContinua("Two Add Medicacao Continua "+new Random().nextInt());
        an.setAnsdescricaoOperacaoRecente("Two Add Operacao Re "+new Random().nextInt());
        an.setAnsdoencaHereditaria(new Random().nextBoolean());
        an.setAnsmedicacaoContinua(new Random().nextBoolean());
        an.setAnsoperacaoRecente(new Random().nextBoolean());
        an.setAnspraticaExercicio(new Random().nextBoolean());        
        Anaminese result = instance.addAnaminese(an);
        assertNotNull(result.getAnsId());
        instance.removeAnaminese(result);
    }

    /**
     * Test of setAnaminese method, of class AnamineseService.
     */
    @Test
    public void testSetAnaminese() throws Exception {
        System.out.println("setAnaminese");
        Anaminese an = anamineseOne;
        String obs = "Obs Set "+new Random().nextLong();
        an.setAnsObs(obs);
        Anaminese result = instance.setAnaminese(an);
        assertEquals(result.getAnsObs(), obs);
    }

    /**
     * Test of removeAnaminese method, of class AnamineseService.
     */
    @Test
    public void testRemoveAnaminese() throws Exception {
        System.out.println("removeAnaminese");
        // Mock Orcamento Object
        Orcamento orcPrivate = new Orcamento();
        orcPrivate.setOrcCustomer(customerOne);
        orcPrivate.setOrcDate(new Date());
        orcPrivate.setOrcDentist(userOne);
        orcPrivate.setOrcHour(new Date());
        orcPrivate.setOrcObs("Obs "+new Random().nextInt());
        orcPrivate.setOrcTimes(new Random().nextInt(10));
        orcPrivate.setOrcTotal(new BigDecimal(Math.abs(new Random().nextDouble())));
        orcPrivate.setOrcpaymentType(PaymentType.CREDITO);
        // Mock Service Object- One
        Service servicePrivate = new Service();
        servicePrivate.setSrvName("Test Anaminese Service Private "+new Random().nextInt());
        servicePrivate.setSrvCost(orcPrivate.getOrcTotal());
        // Mock Of Item
        Orcamentoitem itemPrivate = new Orcamentoitem();
        itemPrivate.setOriCost(orcPrivate.getOrcTotal());
        itemPrivate.setOriObs("Obs Item Two "+new Random().nextInt());
        itemPrivate.setOriService(servicePrivate);
        orcPrivate.addItem(itemPrivate);

        Anaminese an = new Anaminese();
        an.setAnsAlergia(new Random().nextBoolean());
        an.setAnsCustomer(customerOne);
        an.setAnsDst(new Random().nextBoolean());
        an.setAnsFuma(new Random().nextBoolean());
        an.setAnsObs("Two Add Obs "+new Random().nextInt());
        an.setAnsOrcamento(orcPrivate);
        an.setAnsdescricaoAlergia("Two  Add Desc "+new Random().nextInt());
        an.setAnsdescricaoDoenca("Two Add Desc Doenca "+new Random().nextInt());
        an.setAnsdescricaoDst("Two Add Desc Dst "+new Random().nextInt());
        an.setAnsdescricaoMedicacaoContinua("Two Add Medicacao Continua "+new Random().nextInt());
        an.setAnsdescricaoOperacaoRecente("Two Add Operacao Re "+new Random().nextInt());
        an.setAnsdoencaHereditaria(new Random().nextBoolean());
        an.setAnsmedicacaoContinua(new Random().nextBoolean());
        an.setAnsoperacaoRecente(new Random().nextBoolean());
        an.setAnspraticaExercicio(new Random().nextBoolean());        
        Anaminese result = instance.addAnaminese(an);
        assertNotNull(result.getAnsId());
        instance.removeAnaminese(result);
        assertNull(instance.getAnaminese(result.getAnsId()));
    }

    /**
     * Test of getAnaminesesByCustomer method, of class AnamineseService.
     */
    @Test
    public void testGetAnaminesesByCustomer() throws Exception {
        System.out.println("getAnaminesesByCustomer");
        Customer customer = customerOne;
        List<Anaminese> result = instance.getAnaminesesByCustomer(customer);
        assertTrue(result.size() >=1);
    }

    /**
     * Test of getAnaminesesByOrcamento method, of class AnamineseService.
     */
    @Test()
    public void testGetAnaminesesByOrcamento() throws Exception {
        System.out.println("getAnaminesesByOrcamento");
        Orcamento orc = orcamentoOne;
        List<Anaminese> result = instance.getAnaminesesByOrcamento(orc);
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    
}
