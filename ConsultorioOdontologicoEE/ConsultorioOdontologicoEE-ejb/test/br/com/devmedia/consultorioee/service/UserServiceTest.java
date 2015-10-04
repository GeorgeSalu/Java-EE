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
    
    public UserServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        // Mock User Object
        usrOne = new Users();
        usrOne.setUsuAdministrator(new Random().nextBoolean());
        usrOne.setUsuDentist(new Random().nextBoolean());
        usrOne.setUsuLogin("testLoginOne"+new Random().nextInt());
        usrOne.setUsuName("testNameOne"+new Random().nextInt());
        usrOne.setUsuPassword(usrOne.getUsuLogin());
        
        // Mock User Object
        usrTwo = new Users();
        usrTwo.setUsuAdministrator(new Random().nextBoolean());
        usrTwo.setUsuDentist(new Random().nextBoolean());
        usrTwo.setUsuLogin("testLoginTwo"+new Random().nextInt());
        usrTwo.setUsuName("testNameTwo"+new Random().nextInt());
        usrTwo.setUsuPassword(usrOne.getUsuLogin());
        
        // Mock User Object
        usrThree = new Users();
        usrThree.setUsuAdministrator(new Random().nextBoolean());
        usrThree.setUsuDentist(new Random().nextBoolean());
        usrThree.setUsuLogin("testLogin"+new Random().nextInt());
        usrThree.setUsuName("testName"+new Random().nextInt());
        usrThree.setUsuPassword(usrOne.getUsuLogin());
        
    }
    
    @After
    public void tearDown() {
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
        int id = 0;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        UserService instance = (UserService)container.getContext().lookup("java:global/classes/UserService");
        
        usrOne =  instance.addUser(usrOne);
        usrTwo =  instance.addUser(usrTwo);
        usrThree =  instance.addUser(usrThree);
        
        Users expResult = usrTwo;
        Users result = instance.getUser(usrTwo.getUsuId());
        assertEquals(expResult, result);
        
        instance.removeUser(usrOne);
        instance.removeUser(usrTwo);
        instance.removeUser(usrThree);
        
        container.close();
        
    }

    /**
     * Test of setUser method, of class UserService.
     */
    //@Test
    public void testSetUser() throws Exception {
        System.out.println("setUser");
        Users user = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        UserService instance = (UserService)container.getContext().lookup("java:global/classes/UserService");
        Users expResult = null;
        Users result = instance.setUser(user);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeUser method, of class UserService.
     */
    //@Test
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
    //@Test
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
    //@Test
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
    //@Test
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
    //@Test
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
