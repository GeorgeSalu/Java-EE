/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.service;

import br.com.devmedia.consultorioee.entities.Users;
import java.util.List;
import java.util.Random;
import javax.ejb.embeddable.EJBContainer;
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
    private EJBContainer container;
    private UserService instance;
    
    
    public UserServiceTest() {
    }
    
    @BeforeClass
    public void setUpClass() {
    }
    
    @AfterClass
    public void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        instance = (UserService)container.getContext().lookup("java:global/classes/UserService");
        // Mock User Object
        usrOne = new Users();
        usrOne.setUsuAdministrator(new Random().nextBoolean());
        usrOne.setUsuDentist(new Random().nextBoolean());
        usrOne.setUsuLogin("testLoginOne"+new Random().nextInt());
        usrOne.setUsuName("testNameOne "+new Random().nextInt());
        usrOne.setUsuPassword(usrOne.getUsuLogin());
        // Mock User Object
        usrTwo = new Users();
        usrTwo.setUsuAdministrator(new Random().nextBoolean());
        usrTwo.setUsuDentist(new Random().nextBoolean());
        usrTwo.setUsuLogin("testLoginTwo"+new Random().nextInt());
        usrTwo.setUsuName("testNameTwo "+new Random().nextInt());
        usrTwo.setUsuPassword(usrTwo.getUsuLogin());
        // Mock User Object
        usrThree = new Users();
        usrThree.setUsuAdministrator(new Random().nextBoolean());
        usrThree.setUsuDentist(new Random().nextBoolean());
        usrThree.setUsuLogin("testLoginThree"+new Random().nextInt());
        usrThree.setUsuName("testNameThree "+new Random().nextInt());
        usrThree.setUsuPassword(usrThree.getUsuLogin());
    
        usrOne   = instance.addUser(usrOne);
        usrTwo   = instance.addUser(usrTwo);
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
        container.close();
        container = null;
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
        user.setUsuName("ChangedUserName "+new Random().nextInt());
        Users result = instance.setUser(user);
        Users resultFromGet = instance.getUser(user.getUsuId());
        assertEquals(expResult.getUsuName(), result.getUsuName());
        assertEquals(resultFromGet, result);
        assertEquals(resultFromGet.getUsuName(), result.getUsuName());
    }

    /**
     * Test of removeUser method, of class UserService.
     */
//    @Test
    public void testRemoveUser() throws Exception {
        System.out.println("removeUser");
        Users user = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        UserService instance = (UserService)container.getContext().lookup("java:global/classes/UserService");
        instance.removeUser(user);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPassword method, of class UserService.
     */
//    @Test
    public void testSetPassword() throws Exception {
        System.out.println("setPassword");
        int ifOfUser = 0;
        String password = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        UserService instance = (UserService)container.getContext().lookup("java:global/classes/UserService");
        instance.setPassword(ifOfUser, password);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addUser method, of class UserService.
     */
//    @Test
    public void testAddUser() throws Exception {
        System.out.println("addUser");
        Users user = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        UserService instance = (UserService)container.getContext().lookup("java:global/classes/UserService");
        Users expResult = null;
        Users result = instance.addUser(user);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserByLoginPassword method, of class UserService.
     */
//    @Test
    public void testGetUserByLoginPassword() throws Exception {
        System.out.println("getUserByLoginPassword");
        String login = "";
        String password = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        UserService instance = (UserService)container.getContext().lookup("java:global/classes/UserService");
        Users expResult = null;
        Users result = instance.getUserByLoginPassword(login, password);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsers method, of class UserService.
     */
//    @Test
    public void testGetUsers() throws Exception {
        System.out.println("getUsers");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        UserService instance = (UserService)container.getContext().lookup("java:global/classes/UserService");
        List<Users> expResult = null;
        List<Users> result = instance.getUsers();
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
