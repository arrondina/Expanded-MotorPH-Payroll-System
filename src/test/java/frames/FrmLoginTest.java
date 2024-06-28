/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package frames;

import classes.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Rosh
 */
public class FrmLoginTest {
    
    private FrmLogin frmLogin;
    
    public FrmLoginTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        frmLogin = new FrmLogin();
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of main method, of class FrmLogin.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        FrmLogin.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testLoginSuccess() {
        
    }
    
}
