/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quickchatapp;

import java.util.Scanner;

/**
 *
 * @author Student ST10106455 Boitumelo Molefe
 */
public class QuickChatApp {

    public static void main(String[] args) {
        
         //Scanner object to allow user input
           Scanner input = new Scanner (System.in);
           
           //login object to allow input validation
           Login login = new Login();
           
          //Declare variables
      String firstName, lastName, Username, Password, cellPhonenum;
      
      //Prompt user to enter their details 
        System.out.print("Enter your first name:");
      firstName = input.nextLine();
      
      System.out.print("Enter your last name:");
      lastName = input.nextLine();
      
      System.out.print("Enter a valid username:");
      Username = input.nextLine();
          
          //Validate
          
          if(login.checkuserName(Username)){
              System.out.print("Username successfully captured");
          }else {
              System.out.print("Username is not correctly formatted; please ensure that your usrename contains an underscore and is no more than five characters long");
          }
      
      //Prompt user to enter their details 
        System.out.print("Enter your password:");
      Password = input.nextLine();
      
          //Validate
          
       if(!login.checkPasswordComplexity(Password)){
          System.out.println("Pssword successfully captured"); 
       }else{
           System.out.print("Password is not correctly formatted, please ensure that the password contains atleast eigth characters, a capital letter, a number, and a special character");
       }
      
      System.out.println("Enter your cellphone number:");
      cellPhonenum = input.nextLine();
      
         //Validate
         if(login.checkcellPhonenum(cellPhonenum)){
                System.out.print("Cellphone number successfully added");
         } else{
                System.out.print("Cellphone not correctly formatted or does not contain international code");
         }
         
     //display the registration status
     System.out.println(login.registerUser(firstName, lastName, Username, Password, cellPhonenum));
     
     //declare temporary variables to allow user to login
     String loginuserName, loginPassword;
     
      System.out.println("************LOGIN*************");
      
       System.out.println("Please enter your login login username:");
       loginuserName = input.nextLine();
        System.out.println("PLease enter your login password:");
        loginPassword = input.nextLine();
        
         System.out.println(login.returnLoginStatus(firstName, lastName, Username, Password, loginuserName, loginPassword));
         
        
    }
}
