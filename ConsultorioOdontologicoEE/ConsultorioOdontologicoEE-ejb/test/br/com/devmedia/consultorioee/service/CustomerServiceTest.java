/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.service;

import br.com.devmedia.consultorioee.entities.Customer;
import br.com.devmedia.consultorioee.entities.Orcamento;
import br.com.devmedia.consultorioee.entities.PaymentType;
import br.com.devmedia.consultorioee.entities.Users;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
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
    
        private static EJBContainer container;
    private CustomerService instance;
    private UserService instanceUser;
    private Customer customerOne;
    private Customer customerTwo;
    private Customer customerThree;
    private Users usrOne;
    private String customerNamePrefix;

    public CustomerServiceTest() {
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
        instance = (CustomerService) container.getContext().lookup("java:global/classes/CustomerService");
        customerNamePrefix = "Customer Name "+new Random().nextInt();
        // Mock Object One
        customerOne = new Customer();
        customerOne.setCusAddress("Address One " + new Random().nextInt());
        customerOne.setCusAge(Math.abs(new Random().nextInt(99)));
        customerOne.setCusBorndate(new Date());
        customerOne.setCusCity("City " + new Random().nextInt());
        customerOne.setCusComplement("Complement " + new Random().nextInt());
        customerOne.setCusFather("Father " + new Random().nextInt());
        customerOne.setCusMother("Mother " + new Random().nextInt());
        customerOne.setCusName(customerNamePrefix+" One " + new Random().nextInt());
        customerOne.setCusObs("Obs " + new Random().nextInt());
        customerOne.setCusOcupation("Ocupation " + new Random().nextInt());
        customerOne.setCusState("XX");
        customerOne.setCusTelephone("Tel " + new Random().nextInt());
        customerOne.setCuscelNumber("Cel " + new Random().nextInt());
        customerOne.setCusworkAddress("Work address one " + new Random().nextInt());
        customerOne.setCusworkName("WorkName " + new Random().nextInt());
        customerOne.setCusworkNumber(new Random().nextInt() + "");
        customerOne.setCusworkObs("WorkObs " + new Random().nextInt());

        // Mock Object Two
        customerTwo = new Customer();
        customerTwo.setCusAddress("Address Two " + new Random().nextInt());
        customerTwo.setCusAge(Math.abs(new Random().nextInt(99)));
        customerTwo.setCusBorndate(new Date());
        customerTwo.setCusCity("City " + new Random().nextInt());
        customerTwo.setCusComplement("Complement " + new Random().nextInt());
        customerTwo.setCusFather("Father " + new Random().nextInt());
        customerTwo.setCusMother("Mother " + new Random().nextInt());
        customerTwo.setCusName(customerNamePrefix+" Two " + new Random().nextInt());
        customerTwo.setCusObs("Obs " + new Random().nextInt());
        customerTwo.setCusOcupation("Ocupation " + new Random().nextInt());
        customerTwo.setCusState("XX");
        customerTwo.setCusTelephone("Tel " + new Random().nextInt());
        customerTwo.setCuscelNumber("Cel " + new Random().nextInt());
        customerTwo.setCusworkAddress("Work address Two " + new Random().nextInt());
        customerTwo.setCusworkName("WorkName " + new Random().nextInt());
        customerTwo.setCusworkNumber(new Random().nextInt() + "");
        customerTwo.setCusworkObs("WorkObs " + new Random().nextInt());

        // Mock Object Three
        customerThree = new Customer();
        customerThree.setCusAddress("Address Three " + new Random().nextInt());
        customerThree.setCusAge(Math.abs(new Random().nextInt(99)));
        customerThree.setCusBorndate(new Date());
        customerThree.setCusCity("City " + new Random().nextInt());
        customerThree.setCusComplement("Complement " + new Random().nextInt());
        customerThree.setCusFather("Father " + new Random().nextInt());
        customerThree.setCusMother("Mother " + new Random().nextInt());
        customerThree.setCusName(customerNamePrefix+" Three " + new Random().nextInt());
        customerThree.setCusObs("Obs " + new Random().nextInt());
        customerThree.setCusOcupation("Ocupation " + new Random().nextInt());
        customerThree.setCusState("XX");
        customerThree.setCusTelephone("Tel " + new Random().nextInt());
        customerThree.setCuscelNumber("Cel " + new Random().nextInt());
        customerThree.setCusworkAddress("Work address Three " + new Random().nextInt());
        customerThree.setCusworkName("WorkName " + new Random().nextInt());
        customerThree.setCusworkNumber(new Random().nextInt() + "");
        customerThree.setCusworkObs("WorkObs " + new Random().nextInt());
        // Mock User Object
        usrOne = new Users();
        usrOne.setUsuAdministrator(new Random().nextBoolean());
        usrOne.setUsuDentist(new Random().nextBoolean());
        usrOne.setUsuLogin("testLoginOne" + new Random().nextInt());
        usrOne.setUsuName("testNameOne " + new Random().nextInt());
        usrOne.setUsuPassword(usrOne.getUsuLogin());

        // Persist
        customerOne = instance.addCustomer(customerOne);
        customerTwo = instance.addCustomer(customerTwo);
        customerThree = instance.addCustomer(customerThree);
    }

    @After
    public void tearDown() {
        instance.removeCustomer(customerOne);
        instance.removeCustomer(customerTwo);
        instance.removeCustomer(customerThree);
        instance = null;
    }

    /**
     * Test of addCustomer method, of class CustomerService.
     */
    @Test
    public void testAddCustomer() throws Exception {
        System.out.println("addCustomer");
        Customer cus = new Customer();
        cus.setCusAddress("Address AddCustomer " + new Random().nextInt());
        cus.setCusAge(Math.abs(new Random().nextInt(99)));
        cus.setCusBorndate(new Date());
        cus.setCusCity("City " + new Random().nextInt());
        cus.setCusComplement("Complement " + new Random().nextInt());
        cus.setCusFather("Father " + new Random().nextInt());
        cus.setCusMother("Mother " + new Random().nextInt());
        cus.setCusName("Customer Name AddCustomer " + new Random().nextInt());
        cus.setCusObs("Obs " + new Random().nextInt());
        cus.setCusOcupation("Ocupation " + new Random().nextInt());
        cus.setCusState("XX");
        cus.setCusTelephone("Tel " + new Random().nextInt());
        cus.setCuscelNumber("Cel " + new Random().nextInt());
        cus.setCusworkAddress("Work address AddCustomer " + new Random().nextInt());
        cus.setCusworkName("WorkName " + new Random().nextInt());
        cus.setCusworkNumber(new Random().nextInt() + "");
        cus.setCusworkObs("WorkObs " + new Random().nextInt());
        Customer result = instance.addCustomer(cus);
        Customer expResult = instance.getCustomer(result.getCusId());
        assertEquals(result, expResult);
        assertEquals(result.getCusName(), expResult.getCusName());
    }

    /**
     * Test of setCustomer method, of class CustomerService.
     */
    @Test
    public void testSetCustomer() throws Exception {
        System.out.println("setCustomer");
        String nameOfCustomer = "Customer Changed Name " + new Random().nextInt();
        Customer cus = customerTwo;
        cus.setCusName(nameOfCustomer);
        Customer result = instance.setCustomer(cus);
        assertEquals(nameOfCustomer, result.getCusName());
    }

    /**
     * Test of removeCustomer method, of class CustomerService.
     */
    @Test
    public void testRemoveCustomer() throws Exception {
        System.out.println("removeCustomer");
        Customer cus = new Customer();
        cus.setCusAddress("Address AddCustomer " + new Random().nextInt());
        cus.setCusAge(Math.abs(new Random().nextInt(99)));
        cus.setCusBorndate(new Date());
        cus.setCusCity("City " + new Random().nextInt());
        cus.setCusComplement("Complement " + new Random().nextInt());
        cus.setCusFather("Father " + new Random().nextInt());
        cus.setCusMother("Mother " + new Random().nextInt());
        cus.setCusName("Customer Name AddCustomer " + new Random().nextInt());
        cus.setCusObs("Obs " + new Random().nextInt());
        cus.setCusOcupation("Ocupation " + new Random().nextInt());
        cus.setCusState("XX");
        cus.setCusTelephone("Tel " + new Random().nextInt());
        cus.setCuscelNumber("Cel " + new Random().nextInt());
        cus.setCusworkAddress("Work address AddCustomer " + new Random().nextInt());
        cus.setCusworkName("WorkName " + new Random().nextInt());
        cus.setCusworkNumber(new Random().nextInt() + "");
        cus.setCusworkObs("WorkObs " + new Random().nextInt());
        Customer toRemove = instance.addCustomer(cus);
        Customer gettedToRemove = instance.getCustomer(toRemove.getCusId());
        assertNotNull(toRemove);
        assertNotNull(gettedToRemove);
        instance.removeCustomer(gettedToRemove);
        Customer removed = instance.getCustomer(gettedToRemove.getCusId());
        assertNull(removed);
    }

    /**
     * Test of getCustomer method, of class CustomerService.
     */
    @Test
    public void testGetCustomer() throws Exception {
        System.out.println("getCustomer");
        int idOfCustomer = customerThree.getCusId();
        Customer expResult = customerThree;
        Customer result = instance.getCustomer(idOfCustomer);
        assertEquals(expResult, result);
    }

    @Test
    public void testRefreshCustomer() throws Exception {
        String nomeCorreto = customerOne.getCusName();
        customerOne.setCusName("blablabla " + new Random().nextInt());
        Customer response = instance.refreshCustomer(customerOne);
        assertEquals(nomeCorreto, nomeCorreto);
    }

    @Test
    public void testGetIdade() throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 1982);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 12);
        int idade = instance.getIdade(cal.getTime());
        assertEquals(idade,33);
    }

    @Test
    public void testGetUltimoAtendimento() throws Exception {
        Date toReturn = instance.getUltimoAtendimento(customerTwo.getCusId());
        assertNull(toReturn);
    }

    @Test
    public void testGetCustomersCount() throws Exception {
        int quantidade = instance.getCustomersCount();
        assertTrue(quantidade >=3);
    }
    
    /**
     * Test of getCustomerByName method, of class CustomerService.
     */
    @Test
    public void testGetCustomerByName() throws Exception {
        System.out.println("getCustomerByName");
        assertEquals(3, instance.getCustomerByName(customerNamePrefix).size());
        assertEquals(1, instance.getCustomerByName(customerNamePrefix+" Three").size());
        assertEquals(1, instance.getCustomerByName(customerNamePrefix+" Two").size());
        assertEquals(1, instance.getCustomerByName(customerNamePrefix+" One").size());
        assertEquals(0, instance.getCustomerByName("alksjhdgajksdh" + new Random().nextInt()).size());
    }

    /**
     * Test of getCustomersToCall method, of class CustomerService.
     */
    @Test
    public void testGetCustomersToCall() throws Exception {
        System.out.println("getCustomersToCall");
        int month = new Random().nextInt(13);
        if (month == 0) {
            month++;
        }
        int year = 2013;

        Orcamento orc = new Orcamento();
        orc.setOrcCustomer(customerOne);
        orc.setOrcDentist(usrOne);
        orc.setOrcTimes(10);
        orc.setOrcTotal(BigDecimal.TEN);
        orc.setOrcpaymentType(PaymentType.CREDITO);

        List<Customer> expResult = new LinkedList<Customer>();
        List<Customer> result = instance.getCustomersToCall(month, year);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCustomersComPagamentoEmAberto method, of class
     * CustomerService.
     */
    @Test
    public void testGetCustomersComPagamentoEmAberto() throws Exception {
        System.out.println("getCustomersComPagamentoEmAberto");
        int ifOfCustomer = 0;
        List<Customer> expResult = new LinkedList<Customer>();
        List<Customer> result = instance.getCustomersComPagamentoEmAberto(ifOfCustomer);
        assertEquals(expResult , result);
    }
    
}
