/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.service;

import br.com.devmedia.consultorioee.entities.Users;
import java.security.MessageDigest;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.ejb.embeddable.EJBContainer;
import javax.validation.ConstraintViolation;
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
public class UserServiceTest {
   
    private Users usrOne;
    private Users usrTwo;
    private Users usrThree;
    private static EJBContainer container;
    private UserService instance;

    public UserServiceTest() {
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
    public void setUp() throws Exception {
        instance = (UserService) container.getContext().lookup("java:global/classes/UserService");
        // Mock User Object
        usrOne = new Users();
        usrOne.setUsuAdministrator(true);
        usrOne.setUsuDentist(new Random().nextBoolean());
        usrOne.setUsuLogin("test.LoginOne" + new Random().nextInt());
        usrOne.setUsuName("testNameOne " + new Random().nextInt());
        usrOne.setUsuPassword(usrOne.getUsuLogin());
        // Mock User Object
        usrTwo = new Users();
        usrTwo.setUsuAdministrator(true);
        usrTwo.setUsuDentist(new Random().nextBoolean());
        usrTwo.setUsuLogin("test.LoginTwo" + new Random().nextInt());
        usrTwo.setUsuName("testNameTwo " + new Random().nextInt());
        usrTwo.setUsuPassword(usrTwo.getUsuLogin());
        // Mock User Object
        usrThree = new Users();
        usrThree.setUsuAdministrator(true);
        usrThree.setUsuDentist(new Random().nextBoolean());
        usrThree.setUsuLogin("test.LoginThree" + new Random().nextInt());
        usrThree.setUsuName("testNameThree " + new Random().nextInt());
        usrThree.setUsuPassword(usrThree.getUsuLogin());

        try {
            usrOne = instance.addUser(usrOne);
        } catch (javax.ejb.EJBException ejbe) {
            javax.validation.ConstraintViolationException cv = (javax.validation.ConstraintViolationException) ejbe.getCausedByException();
            for (ConstraintViolation<?> violacao : cv.getConstraintViolations()) {
                System.out.println("Violation " + violacao.getMessage());
            }
        }
        usrTwo = instance.addUser(usrTwo);
        usrThree = instance.addUser(usrThree);
    }

    @After
    public void tearDown() {
        instance.removeUser(usrOne);
        instance.removeUser(usrTwo);
        instance.removeUser(usrThree);
        instance = null;
        usrOne = null;
        usrTwo = null;
        usrThree = null;
    }

    /**
     * Test of getUser method, of class UserService.
     */
    @Test
    public void testGetUser() throws Exception {
        System.out.println("getUser");
        Users expResult = usrTwo;
        Users result = instance.getUser(usrTwo.getUsuId());
        assertEquals(expResult, result);
    }

    /**
     * Test of setUser method, of class UserService.
     */
    @Test
    public void testSetUser() throws Exception {
        System.out.println("setUser");
        Users user = usrThree;
        Users expResult = usrThree;
        user.setUsuName("ChangedUserName " + new Random().nextInt());
        Users result = instance.setUser(user);
        Users resultFromGet = instance.getUser(user.getUsuId());
        assertEquals(expResult.getUsuName(), result.getUsuName());
        assertEquals(resultFromGet, result);
        assertEquals(resultFromGet.getUsuName(), result.getUsuName());
    }

    /**
     * Test of removeUser method, of class UserService.
     */
    @Test
    public void testRemoveUser() throws Exception {
        System.out.println("removeUser");
        // Mock User Object
        Users user = new Users();
        user.setUsuAdministrator(new Random().nextBoolean());
        user.setUsuDentist(new Random().nextBoolean());
        user.setUsuLogin("My Test Login User " + new Random().nextInt());
        user.setUsuName("My Test Name User " + new Random().nextInt());
        user.setUsuPassword(user.getUsuLogin());
        user = instance.addUser(user);
        instance.removeUser(user);
        Users userRemoved = instance.getUser(user.getUsuId());
        assertNull(userRemoved);
    }

    /**
     * Test of setPassword method, of class UserService.
     */
    @Test
    public void testSetPassword() throws Exception {
        System.out.println("setPassword");
        String tmpPassword = new Random().nextInt() + "MyChangePassword";
        String md5TmpPassword = getMd5(tmpPassword);
        instance.setPassword(usrTwo.getUsuId(), tmpPassword);
        Users user = instance.getUser(usrTwo.getUsuId());
        assertEquals(user.getUsuPassword(), md5TmpPassword);
    }

    private String getMd5(String message) {
        String digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(message.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                sb.append(String.format("%02x", b & 0xff));
            }
            digest = sb.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return digest;
    }

    /**
     * Test of addUser method, of class UserService.
     */
    @Test
    public void testAddUser() throws Exception {
        System.out.println("addUser");
        // Mock User Object
        Users user = new Users();
        user.setUsuAdministrator(new Random().nextBoolean());
        user.setUsuDentist(new Random().nextBoolean());
        user.setUsuLogin("My Test Login User(Add) " + new Random().nextInt());
        user.setUsuName("My Test Name User(Add) " + new Random().nextInt());
        user.setUsuPassword(user.getUsuLogin());
        Users result = instance.addUser(user);
        Users resultFromGet = instance.getUser(user.getUsuId());
        assertEquals(result, resultFromGet);
        instance.removeUser(resultFromGet);
    }

    /**
     * Test of getUserByLoginPassword method, of class UserService.
     */
    @Test
    public void testGetUserByLoginPassword() throws Exception {
        System.out.println("getUserByLoginPassword");
        String login = usrOne.getUsuLogin();
        String password = usrOne.getUsuLogin();
        Users expResult = usrOne;
        Users result = instance.getUserByLoginPassword(login, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUsers method, of class UserService.
     */
    @Test
    public void testGetUsers() throws Exception {
        System.out.println("getUsers");
        List<Users> expResult = new LinkedList<>();
        expResult.add(usrOne);
        expResult.add(usrTwo);
        expResult.add(usrThree);
        List<Users> result = instance.getUsers();
        assertEquals(expResult.size(), result.size());
    }

    @Test
    public void testGetUsersByName() throws Exception {
        System.out.println("getUsersByName");
        List<Users> usrs = instance.getUsersByName("testName");
        assertTrue(usrs.size() >= 3);
    }

    @Test
    public void testGetDentistUsers() throws Exception {
        System.out.println("getDentistUsers");
        List<Users> dentistas = instance.getDentistUsers();
        int quantidade = 0;
        if (usrOne.getUsuDentist()) {
            quantidade++;
        }
        if (usrTwo.getUsuDentist()) {
            quantidade++;
        }
        if (usrThree.getUsuDentist()) {
            quantidade++;
        }
        assertTrue(dentistas.size() >= quantidade);

    }

    @Test
    public void testGetUsersByExactName() throws Exception {
        System.out.println("getUsersByExactName");
        assertEquals(usrOne.getUsuName(), instance.getUsersByExactName(usrOne.getUsuName()).getUsuName());
        assertEquals(usrTwo.getUsuName(), instance.getUsersByExactName(usrTwo.getUsuName()).getUsuName());
        assertEquals(usrThree.getUsuName(), instance.getUsersByExactName(usrThree.getUsuName()).getUsuName());
    }
    

}
