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
        
         Scanner input = new Scanner(System.in);
        Login login = new Login();

        // Declare variables for registration
        String firstName, lastName, username, password, cellPhonenum;

        System.out.println("================= SIGN UP =================");

        // Get first and last name
        System.out.println("Enter your first name:");
        firstName = input.nextLine();

        System.out.println("Enter your last name:");
        lastName = input.nextLine();
        
        // Get and validate username
        System.out.println("Enter a valid username (must contain _ and be max 5 characters):");
        username = input.nextLine();

        while (!login.checkuserName(username)) {
            System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters long.");
            System.out.println("Enter username:");
            username = input.nextLine();
        }
        System.out.println("Username successfully captured.");

        // Get and validate password
        System.out.println("Enter a valid password (min 8 characters, 1 capital, 1 number, 1 special character):");
        password = input.nextLine();

        while (!login.checkPasswordComplexity(password)) {
            System.out.println("Password is not correctly formatted; please ensure that the password contains at least 8 characters, a capital letter, a number, and a special character.");
            System.out.println("Enter password:");
            password = input.nextLine();
        }
        System.out.println("Password successfully captured.");
        
          // Get and validate cell number
        System.out.println("Enter your cellphone number:");
        cellPhonenum = input.nextLine();

        while (!login.checkcellPhonenum(cellPhonenum)) {
            System.out.println("Cellphone number incorrectly formatted or does not contain international code.");
            System.out.println("Try again:");
            cellPhonenum = input.nextLine();
        }
        System.out.println("Cellphone number successfully added.");

        // Show registration status
        System.out.println(login.registerUser(firstName, lastName, username, password, cellPhonenum));

        // Login section
        System.out.println("\n================= LOGIN =================");
        System.out.println("Enter your username:");
        String loginUsername = input.nextLine();

        System.out.println("Enter your password:");
        String loginPassword = input.nextLine();

        // Check login credentials
        if (login.loginUser(username, password, loginUsername, loginPassword)) {

            System.out.println(login.returnLoginStatus(firstName, lastName, username, password, loginUsername, loginPassword));
            System.out.println("Welcome to QuickChat.");

            int option = 0;
            
            // Keep showing the menu until user selects quit
            while (option != 3) {

                System.out.println("\n================= MENU =================");
                System.out.println("1) Send Messages");
                System.out.println("2) View recently sent messages");
                System.out.println("3) Quit");
                System.out.println("4) Stored Messages");
                System.out.print("Please select an option:");
                option = Integer.parseInt(input.nextLine());

                if (option == 1) {

                    System.out.println("============ SEND MESSAGES ============");
                    
                    // Ask how many messages after selecting Send
                    System.out.print("How many messages do you want to send?");
                    int numMess = Integer.parseInt(input.nextLine());

                    // for loop runs exactly numMess times
                    for (int i = 0; i < numMess; i++) {

                        System.out.println("\nMessage " + (i + 1) + " of " + numMess);

                        // Get and validate recipient
                        System.out.println("Enter recipient cellphone number (with international code):");
                        String recipient = input.nextLine();

                        if (!login.checkcellPhonenum(recipient)) {
                            System.out.println(new Messages(0, recipient, "x").checkRecipientCell(recipient));
                            i--; // don't count this attempt
                            continue;
                        }

                        // Get and validate message text
                        System.out.println("Enter your message (max 250 characters):");
                        String messageText = input.nextLine();

                        if (messageText.length() > 250) {
                            int over = messageText.length() - 250;
                            System.out.println("Message exceeds 250 characters by " + over + "; please reduce the size.");
                            i--; // don't count this attempt
                            continue;
                        }

                        // Create the message object
                        Messages msg = new Messages(i + 1, recipient, messageText);

                        // Show full message details
                        System.out.println(msg.printMessages());

                        // Ask what to do with it
                        String result = msg.sentMessage();
                        System.out.println(result);

                    } // for loop exits automatically after numMess

                    // Show total after all messages done
                    Messages temp = new Messages(0, "+270000000000", "placeholder");
                    System.out.println("\nTotal messages sent: " + temp.returnTotalMessages());

                } else if (option == 2) {
                    System.out.println("Coming Soon!");

                } else if (option == 3) {
                    System.out.println("Goodbye!!");

                } else if (option == 4) {

                    // Stored Messages sub-menu
                    int subChoice = 0;
        
         while (subChoice != 7) {

                        System.out.println("\n--- Stored Messages Menu ---");
                        System.out.println("1) Display all stored messages (recipient + message)");
                        System.out.println("2) Display the longest message");
                        System.out.println("3) Search for a message by ID");
                        System.out.println("4) Search messages for a particular recipient");
                        System.out.println("5) Delete a message using its hash");
                        System.out.println("6) Display full message report");
                        System.out.println("7) Back to main menu");

                        subChoice = Integer.parseInt(input.nextLine());

                        if (subChoice == 1){
                            //2a - display stored messages
                            Messages.displayStoredSendersAndRecipients();
                            
                        }else if (subChoice == 2){
                            //2b - longest message
                            Messages.displayLongestMessage();
                            
                        }else if (subChoice == 3){
                            //2c - search by name ID
                            System.out.println("Enter message ID to search:");
                            String searchID = input.nextLine();
                            Messages.searchByMessageID(searchID);
                            
                            
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        /*
         //Scanner object to allow user input
           Scanner input = new Scanner (System.in);
           Messages messages = new Messages ();
           
          
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
              System.out.println("Username successfully captured");
          }else {
              System.out.println("Username is not correctly formatted; please ensure that your usrename contains an underscore and is no more than five characters long");
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
         */
                 
         
         //declare variable to store option
         int option = 0;
         int numMess = 0;
         String recipient = ""; 
         String messageHash;
         String sentMessage;
         String printMessages; 
         String messageID;
         String messageText;
         
         
         
         
         
         
         //while loop to loop menu until user selects quit
         do{
         System.out.println("================= MENU =================");
         System.out.println("1.SEND MESSAGES");
         System.out.println("2.VIEW RECENTLY SENT MESSAGES");
         System.out.println("3.QUIT");
         System.out.print("Please select one option:");
         option = Integer.parseInt(input.nextLine());
         
         
         //create menu
        switch(option){
            case 1: 
                //send messages
                System.out.println("============SEND MESSAGES============");
                
                //prompt user to enter set of numbers
                System.out.print("How many messages do you want to send?");
                numMess = Integer.parseInt(input.nextLine());
                
                //Loop numMessages times
                for(int i = 0; i < numMess; i++){
                    
                    System.out.println("\nMessage " + (i + 1) + " of " + numMess);
                    
                    //2. Get recipient with validation
                    while(!message.checkcellPhonenum(recipient)){
                        System.out.print("Enter recipient cellphone number with international code:");
                        recipient = input.nextLine();
                        System.out.println(message.checkRecipientCell(recipient));
                    }
                    
                    recipient = "";
                    
                }
                System.out.println("Enter your message:");
                messageText = input.nextLine();
                
                while (!message.checkMessageLength(messageText)){
                    System.out.println("Message must be 250 character or less!!");
                    System.out.println("Enter message:");
                    messageText = input.nextLine();
                    
                }
                
                messageID = message.generateMessageID();
                messageHash = message.createMessageHash(messageID ,messageText);
                
                 
                break; 
                
            case 2:
                //show recently sent messages
                System.out.println("Coming Soon!");
                break;
                
                
            case 3:
                //quit
                System.out.println("Goodbye!!");
                break;
                
            default:
                //if user has selected an invalid option
                System.out.println("Invalid option, please enter 1,2 or 3");
                break;
                
        }
        
        
         }while(option !=3);
         
         
    }
}
