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
class Messages {
    
    //method to message ID
    boolean checkMessageID(String messageID){
        return messageID.length () < 10;
                   
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
    
    //method to ensure recipient call number
    String checkRecipientCell(String recipientCell){
        
        if (checkcellPhonenum(recipientCell)){
            return "Cellphone number successfully saved";
        }else{
            return "Cellphone number incorrectly formatted or missing international code";
            
            
        } 
    }
    //method to create message Hash
    String createMessageHash(String messageId, String messageText){
       String idStr = String.valueOf(messageId);
       String[] words = messageText.split("");
       String firstWords = words.length >= 2? words [0] + words [1]:messageText;
       
       String hash = idStr.substring(0,2).toUpperCase() + ":" + firstWords.toUpperCase() + ":" + idStr.substring(idStr.length() - 3);
       return hash;
       
    }
    
    //method to allow the user to chose between send, store or disregard
    String sentMessage(String messageText, String recipientCell){
        if (messageText.length() <=250 && checkRecipientCell(recipientCell).contains ("successfully")){
            sentMessage.add(this);
            return "Message sent successfully";
        }else if (messageText.length ()> 250){
            return "Message not set. Please enter message of less than 250 characters";
        }else {
            return "Message not sent. Invalid recipient number";
        }
    }
    //method to returns all the message sent
    String printMessages(String messageText, String recipientCell, String messageID, String messageHash){
        if (sentMessages.isEmpty()){
            return "No message sent yet";
        }
        StringBuilder sb = new StringBuilder();
        for (Message m: sentMessages){
            sb.append("Message Hash:").append(m.messageHash).append("\n");
            sb.append("Recipient:").append(m.recipient).append("\n");
            sb.append("Message:").append(m.recipient).append("\n\n");     
        }
        return sb.toString();
        
    }
    //method to return the otal number of messages sent
    int returnTotalMessages(String messageText, String recipientCell, String messageID, String messageHash){
        return TotalMessageSent;
        
    }
    
    //Your own defined storeMessage method 
    String storeMessage(String messageText, String recipientCell, String messageID, String messageHash){
        JSONObject json = new JSONObject();
        json.put("MessageHash", messageHash);
        json.put("Recipient", recipientCell);
        json.put("Message", messageText);
        json.put("MessageID", messageID);
        
        return json.toString();
        
    }
    
    //Getter for testing
    long getMessageID(String messageText, String recipientCell, String messageID, String messageHash){
        return messageID;
    }
    String getMessageHash(){
        return messageHash;
    }
}

    
        
    
}
