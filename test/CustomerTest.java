/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Models.Customer;
import Models.Visa;
import java.sql.SQLException;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ia7ma
 */
public class CustomerTest {
    
    public CustomerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of login method, of class Customer.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        String ssn = "232323";
        String pass = "12345";
        Customer instance = new Customer();
        boolean expResult = true;
        boolean result = instance.login(ssn, pass);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setVisa method, of class Customer.
     */

    @Test
    public void testIsExist() throws Exception {
        System.out.println("isExist");
        String ssn = "232323";
        boolean expResult = true;
        boolean result = Customer.isExist(ssn);
        assertEquals(expResult, result);
    }

    /**
     * Test of Register method, of class Customer.
     */
    
    @Test
    public void testPayment() throws SQLException {
        System.out.println("payment");
        int cvc = 333;
        Visa visa = new Visa(new Date(), "464846", 333);
        Customer instance = new Customer(visa, "12345", "232323", "mostafa", "ramadan", "misto@gmail.com", "055555555");
        boolean expResult = true;
        boolean result = instance.payment(cvc);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }


    /**
     * Test of cancelBook method, of class Customer.
     */
    @Test
    public void testRegister() throws SQLException {
        System.out.println("register");
        Customer instance = new Customer();
        boolean expResult = false;
        Visa visa = new Visa(new Date(), "464846", 333);
        //Customer(Visa visa,String password, String ssn, String fname, String lname, String email, String phone)
        boolean result = instance.Register(visa, "1111", "12344", "Ahmed", "Adel", "ahmed@gmail.com", "054889774");
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
}
