/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchatapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Student
 */
class Messages {
    
     // Private variables for each message
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;

    // --- ARRAYS (static = shared across all messages) ---
    private static ArrayList<String> sentMessages        = new ArrayList<>(); // full details of sent messages
    private static ArrayList<String> disregardedMessages = new ArrayList<>(); // full details of disregarded messages
    private static ArrayList<String> storedMessages      = new ArrayList<>(); // loaded from JSON file
    private static ArrayList<String> messageHashes       = new ArrayList<>(); // all hashes
    private static ArrayList<String> messageIDs          = new ArrayList<>(); // all IDs
    private static ArrayList<String> sentRecipients      = new ArrayList<>(); // recipients of sent messages
    private static ArrayList<String> sentTexts           = new ArrayList<>(); // texts of sent messages

    private static int totalMessagesSent = 0;

    // Constructor
    public Messages(int messageNumber, String recipient, String messageText) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = generateMessageID();
    }

    // Builds a 10-digit ID by picking random digits one at a time
    public String generateMessageID() {
        String id = "";
        for (int i = 0; i < 10; i++) {
            int randomDigit = (int) (Math.random() * 10);
            id = id + randomDigit;
        }
        return id;
    }

    // Returns true if message ID is exactly 10 characters
    public boolean checkMessageID() {
        if (messageID.length() == 10) {
            return true;
        } else {
            return false;
        }
    }

    // Returns true if message is 250 characters or less
    public boolean checkMessageLength(String message) {
        if (message.length() <= 250) {
            return true;
        } else {
            return false;
        }
    }

    // Validates cell number using regex - must start with +27 or 0, then 6/7/8, then 8 digits
    public boolean checkcellPhonenum(String cellPhonenum) {
        String regex = "^(\\+27|0)[6-8][0-9]{8}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cellPhonenum);
        return matcher.matches();
    }

    // Checks recipient number is valid
    public String checkRecipientCell(String recipientCell) {
        if (checkcellPhonenum(recipientCell)) {
            return "Cellphone number successfully saved.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    // Builds hash: first 2 digits of ID + ":" + messageNumber + ":" + firstWord + lastWord
    // Example: "00:1:DIDCAKE?"
    public String createMessageHash() {
        // Split message into words using spaces
        String[] words = messageText.trim().split(" ");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        String firstTwoDigits = messageID.substring(0, 2);
        String hash = firstTwoDigits + ":" + messageNumber + ":" + firstWord + lastWord;
        return hash.toUpperCase();
    }

    // Saves this message to messages.json in the project folder (append mode)
    public void storeMessage() {
        try {
            String messageData = "{\n" +
                "  \"messageID\": \"" + messageID + "\",\n" +
                "  \"messageHash\": \"" + createMessageHash() + "\",\n" +
                "  \"recipient\": \"" + recipient + "\",\n" +
                "  \"message\": \"" + messageText + "\"\n" +
                "}";

            // Save to project folder
            String projectPath = System.getProperty("user.dir") + "/messages.json";
            System.out.println("Saving to: " + projectPath);

            // 'true' means append - adds to file instead of overwriting
            FileWriter writer = new FileWriter(projectPath, true);
            writer.write(messageData + ",\n");
            writer.flush();
            writer.close();

            System.out.println("File saved successfully.");

        } catch (Exception e) {
            System.out.println("Error saving message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Reads messages.json and loads each stored message block into storedMessages array
    public static void loadStoredMessages() {
        storedMessages.clear(); // clear first to avoid duplicates

        try {
            String projectPath = System.getProperty("user.dir") + "/messages.json";
            FileReader fr = new FileReader(projectPath);
            BufferedReader reader = new BufferedReader(fr);

            String line = "";
            String currentBlock = "";

            // Read file line by line and group each {...} block as one message
            while ((line = reader.readLine()) != null) {
                currentBlock = currentBlock + line + "\n";

                // Each message block ends with "}," so we know its complete
                if (line.trim().equals("},")) {
                    storedMessages.add(currentBlock.trim());
                    currentBlock = ""; // reset for next message
                }
            }
            reader.close();

        } catch (Exception e) {
            System.out.println("No stored messages file found.");
        }
    }

    // Helper: pulls a field value out of a JSON block
    // e.g. extractField(block, "message") returns the message text
    private static String extractField(String block, String fieldName) {
        String[] lines = block.split("\n");
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].trim();
            if (line.startsWith("\"" + fieldName + "\"")) {
                // Line looks like: "message": "Hi there"
                String value = line.replace("\"" + fieldName + "\":", "").trim();
                value = value.replace("\"", "").trim();
                // Remove trailing comma if present
                if (value.endsWith(",")) {
                    value = value.substring(0, value.length() - 1);
                }
                return value;
            }
        }
        return "";
    }

    // Asks the user what to do with the message: send, disregard, or store
    public String sentMessage() {
        Scanner input = new Scanner(System.in);

        System.out.println("What would you like to do?");
        System.out.println("1) Send Message");
        System.out.println("2) Disregard Message");
        System.out.println("3) Store Message to send later");

        int choice = Integer.parseInt(input.nextLine());

        if (choice == 1) {
            totalMessagesSent++;
            sentMessages.add(printMessages());       // save full details
            sentRecipients.add(recipient);           // save recipient
            sentTexts.add(messageText);              // save message text
            messageHashes.add(createMessageHash());  // save hash
            messageIDs.add(messageID);               // save ID
            return "Message successfully sent.";

        } else if (choice == 2) {
            disregardedMessages.add(printMessages()); // save to disregarded array
            return "Press 0 to delete the message.";

        } else if (choice == 3) {
            storeMessage();                           // write to JSON file
            messageHashes.add(createMessageHash());
            messageIDs.add(messageID);
            return "Message successfully stored.";

        } else {
            return "Invalid option.";
        }
    }

    // Returns all message details as one block of text
    public String printMessages() {
        String details = "Message ID: " + messageID + "\n";
        details = details + "Message Hash: " + createMessageHash() + "\n";
        details = details + "Recipient: " + recipient + "\n";
        details = details + "Message: " + messageText;
        return details;
    }

    // Returns total messages sent
    public int returnTotalMessages() {
        return totalMessagesSent;
    }

    // --- PART 3 FEATURES ---

    // 2a: Display recipient and message of all stored messages (reads from JSON)
    public static void displayStoredSendersAndRecipients() {
        loadStoredMessages();

        if (storedMessages.size() == 0) {
            System.out.println("No stored messages found.");
            return;
        }

        System.out.println("\n--- Stored Messages ---");
        for (int i = 0; i < storedMessages.size(); i++) {
            String block = storedMessages.get(i);
            String recipient = extractField(block, "recipient");
            String message = extractField(block, "message");
            System.out.println((i + 1) + ". Recipient: " + recipient + " | Message: " + message);
        }
    }

    // 2b: Display the longest message across sent and stored messages
    public static void displayLongestMessage() {
        // Collect all message texts into one list
        ArrayList<String> allTexts = new ArrayList<>();

        // Add sent message texts
        for (int i = 0; i < sentTexts.size(); i++) {
            allTexts.add(sentTexts.get(i));
        }

        // Add stored message texts from JSON
        loadStoredMessages();
        for (int i = 0; i < storedMessages.size(); i++) {
            String text = extractField(storedMessages.get(i), "message");
            if (!text.equals("")) {
                allTexts.add(text);
            }
        }

        if (allTexts.size() == 0) {
            System.out.println("No messages found.");
            return;
        }

        // Loop through all texts to find the longest one
        String longest = allTexts.get(0);
        for (int i = 1; i < allTexts.size(); i++) {
            if (allTexts.get(i).length() > longest.length()) {
                longest = allTexts.get(i);
            }
        }

        System.out.println("\nLongest message: " + longest);
    }

    // 2c: Search for a message by ID, display recipient and message
    public static void searchByMessageID(String searchID) {
        boolean found = false;

        // Search sent messages
        for (int i = 0; i < messageIDs.size(); i++) {
            if (messageIDs.get(i).equals(searchID)) {
                if (i < sentRecipients.size()) {
                    System.out.println("Recipient: " + sentRecipients.get(i));
                    System.out.println("Message: " + sentTexts.get(i));
                }
                found = true;
            }
        }

        // Also search stored JSON messages
        loadStoredMessages();
        for (int i = 0; i < storedMessages.size(); i++) {
            String id = extractField(storedMessages.get(i), "messageID");
            if (id.equals(searchID)) {
                System.out.println("Recipient: " + extractField(storedMessages.get(i), "recipient"));
                System.out.println("Message: " + extractField(storedMessages.get(i), "message"));
                found = true;
            }
        }

        if (!found) {
            System.out.println("No message found with ID: " + searchID);
        }
    }

    // 2d: Search all messages for a particular recipient
    public static void searchByRecipient(String searchRecipient) {
        boolean found = false;

        System.out.println("\nMessages for " + searchRecipient + ":");

        // Search sent messages
        for (int i = 0; i < sentRecipients.size(); i++) {
            if (sentRecipients.get(i).equals(searchRecipient)) {
                System.out.println("- " + sentTexts.get(i));
                found = true;
            }
        }

        // Search stored messages from JSON
        loadStoredMessages();
        for (int i = 0; i < storedMessages.size(); i++) {
            String recipient = extractField(storedMessages.get(i), "recipient");
            if (recipient.equals(searchRecipient)) {
                String message = extractField(storedMessages.get(i), "message");
                System.out.println("- " + message);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No messages found for: " + searchRecipient);
        }
    }

    // 2e: Delete a message using its hash
    public static void deleteByHash(String hashToDelete) {
        boolean found = false;
        String upperHash = hashToDelete.toUpperCase();

        // Search the messageHashes array
        for (int i = 0; i < messageHashes.size(); i++) {
            if (messageHashes.get(i).equals(upperHash)) {
                String deletedText = "";
                if (i < sentTexts.size()) {
                    deletedText = sentTexts.get(i);
                    sentTexts.remove(i);
                    sentRecipients.remove(i);
                    messageIDs.remove(i);
                }
                messageHashes.remove(i);
                System.out.println("Message: \"" + deletedText + "\" successfully deleted.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("No message found with hash: " + hashToDelete);
        }
    }

    // 2f: Display full report of all sent messages
    public static void displayReport() {
        System.out.println("\n========== MESSAGE REPORT ==========");

        if (sentMessages.size() == 0) {
            System.out.println("No messages sent yet.");
        } else {
            for (int i = 0; i < sentMessages.size(); i++) {
                System.out.println("\n--- Message " + (i + 1) + " ---");
                System.out.println(sentMessages.get(i));
            }
        }

        System.out.println("\n====================================");
        System.out.println("Total messages sent: " + totalMessagesSent);
    }

    // Getters
    public String getMessageID()   { return messageID; }
    public String getMessageText() { return messageText; }
    public String getRecipient()   { return recipient; }

    // Array getters (used in tests)
    public static ArrayList<String> getSentMessages()        { return sentMessages; }
    public static ArrayList<String> getDisregardedMessages() { return disregardedMessages; }
    public static ArrayList<String> getSentRecipients()      { return sentRecipients; }
    public static ArrayList<String> getSentTexts()           { return sentTexts; }
    public static ArrayList<String> getMessageHashes()       { return messageHashes; }
    public static ArrayList<String> getMessageIDs()          { return messageIDs; }
}


    


    
        
    

