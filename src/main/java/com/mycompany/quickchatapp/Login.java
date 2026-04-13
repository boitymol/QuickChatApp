/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchatapp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Student
 */
class Login {
    
    //method to validate the username 
    boolean checkuserName (String userName){
        return userName.contains("_")&& userName.length()<=5;
    }
    
    //method to validate the password
  
 
    }
    
    //method that validates the cellphone number
        boolean checkcellPhonenum(String cellPhonenum){
        
        //declare a variable to store the pattern
        String regex  = "^(\\+27|0)[6-8][0-9]{8}$";
       
            
            //pattern object to compile the regex
            Pattern pattern = Pattern.compile (regex);
            
            //matcher object to match the student number to the regex
            Matcher matcher = pattern.matcher(cellPhonenum);
            
            //true is pattern matches student number
            return matcher.matches();
            
        }
        //method to return registration status
        String registerUser(String firstName, String lastName, String userName, String Password, String cellPhonenum){
       if(checkuserName(userName) && checkcellPhonenum (cellPhonenum)){
           return "User successfully registered";
       }else{
           System.out.print("Username, password or cellphone number incorrectly formatted, registration unsuccessfully");
           System.exit(0);
           return null; 
       }
       
          }
        
        }
        
        //method to return login status
        String returnLoginStatus(String firstName, String lastName, String userName, String Password, String loginuserName, String loginPassword){
            
            if (loginUser(userName,Password, loginuserName,loginPassword )){
                return "Welcome" + firstName + "," + lastName + "'" + "it is great to see you again";
            
        }else{
                return "Username or password incorrect, please try again.";
            }
                
        }
        
        }
        
       
    
