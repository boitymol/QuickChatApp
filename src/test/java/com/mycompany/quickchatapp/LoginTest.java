/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.quickchatapp;



import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class LoginTest {
    
    public LoginTest() {
    }

  
   

    /**
     * Test of checkuserName method, of class Login.
     */
    @Test
    public void testCheckuserName() {
        System.out.println("checkuserName");
        String userName = "boit_";
        Login instance = new Login();
        boolean expResult = true;
        boolean result = instance.checkuserName(userName);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of checkcellPhonenum method, of class Login.
     */
    @Test
    public void testCheckcellPhonenum() {
        System.out.println("checkcellPhonenum");
        String cellPhonenum = "+27676071493";
        Login instance = new Login();
        boolean expResult = true;
        boolean result = instance.checkcellPhonenum(cellPhonenum);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of registerUser method, of class Login.
     */
    @Test
    public void testRegisterUser() {
        System.out.println("registerUser");
        String firstName = "boitumelo";
        String lastName = "molefe";
        String userName = "boit_";
        String Password = "Boity@8";
        String cellPhonenum = "+27676071493";
        Login instance = new Login();
        String expResult = "User successfully registered";
        String result = instance.registerUser(firstName, lastName, userName, Password, cellPhonenum);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of loginUser method, of class Login.
     */
    @Test
    public void testLoginUser() {
        System.out.println("loginUser");
        String userName = "boit_";
        String Password = "Boity@8";
        String loginUsername = "boit_";
        String loginPassword = "Boity@8";
        Login instance = new Login();
        boolean expResult = false;
        boolean result = instance.loginUser(userName, Password, loginUsername, loginPassword);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of returnLoginStatus method, of class Login.
     */
    @Test
    public void testReturnLoginStatus() {
        System.out.println("returnLoginStatus");
        String firstName = "boitumelo";
        String lastName = "molefe";
        String userName = "boit_";
       
        String Password = "Boity@8";
        String loginuserName = "boit_";
        String loginPassword = "Boity@8";
        Login instance = new Login();
        String expResult = "Welcome" + firstName + "," + lastName + "'" + "it is great to see you again";
        String result = instance.returnLoginStatus(firstName, lastName, userName, Password, loginuserName, loginPassword);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of checkPasswordComplexity method, of class Login.
     */
    @Test
    public void testCheckPasswordComplexity() {
        System.out.println("checkPasswordComplexity");
        String Password = "Boitu@8";
        Login instance = new Login();
        boolean expResult = false;
        boolean result = instance.checkPasswordComplexity(Password);
        assertEquals(expResult, result);
       
    } 
}
