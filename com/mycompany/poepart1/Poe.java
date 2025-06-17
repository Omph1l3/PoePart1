/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poepart1;

/**
 *
 * @author RC_Student_lab
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import javax.swing.JOptionPane;

public class Poe {

    private static ArrayList<String> sentMessages = new ArrayList<>(); // Has all sent messages
    private static ArrayList<String> disregardedMessages = new ArrayList<>(); // Has all disregarded messages
    private static ArrayList<String> storedMessages = new ArrayList<>(); // Has stored messages
    private static ArrayList<String> messageHashes = new ArrayList<>(); // Has all message hashes
    private static ArrayList<String> messageIDs = new ArrayList<>(); // Has all message IDs
    private static int totalMessagesSent = 0;

    // Login handler
    private static class Login {
        public boolean checkUsername(String username) {
            return username != null && username.contains("_") && username.length() <= 5;
        }

        public boolean checkPassword(String password) {
            if (password == null || password.length() < 8) return false;
            return password.matches(".*[A-Z].*") && password.matches(".*[a-z].*") &&
                   password.matches(".*[0-9].*") && password.matches(".*[!@#$%^&+=?].*");
        }

        public boolean checkCellNumber(String cellNumber) {
            return cellNumber != null && cellNumber.matches("\\+27[6-8][0-9]{7}"); // Test data format
        }
    }

    // Login handler
    private static class MessageHandler {
        private static List<String> messageList = new ArrayList<>();

        public boolean checkMessageID(String messageID) {
            return messageID != null && messageID.length() == 10 && messageID.matches("\\d{10}");
        }

        public boolean checkCellNumber(String cellNumber) {
            return cellNumber != null && cellNumber.matches("\\+27[6-8][0-9]{7}");
        }

        public String createMessageHash(String messageContent) {
            return UUID.nameUUIDFromBytes(messageContent.getBytes()).toString();
        }

        public String sentMessage(String option, String message, String recipient) {
            String messageID = UUID.randomUUID().toString().replaceAll("[^0-9]", "").substring(0, 10);
            String messageHash = createMessageHash(message);
            messageIDs.add(messageID);
            messageHashes.add(messageHash);
            switch (option.toLowerCase()) {
                case "send":
                    messageList.add(message + " (Sent to: " + recipient + ")");
                    sentMessages.add(message + " (Sent to: " + recipient + ")");
                    return "Message sent with ID: " + messageID;
                case "store":
                    messageList.add("[Stored] " + message);
                    storedMessages.add("[Stored] " + message);
                    return "Message stored with ID: " + messageID;
                case "disregard":
                    messageList.add("[Disregarded] " + message);
                    disregardedMessages.add("[Disregarded] " + message);
                    return "Message disregarded with ID: " + messageID;
                default:
                    return "Invalid option.";
            }
        }

        public String printMessage() {
            return messageList.isEmpty() ? "No messages." : String.join("\n", messageList);
        }

        public int returnTotalMessages() {
            return messageList.size();
        }
    }

    private static Login loginHandler = new Login();
    private static MessageHandler messageHandler = new MessageHandler();

    public static void main(String[] args) {
        while (true) {
            String firstName = JOptionPane.showInputDialog(null, "Enter first name");
            if (!firstName.isEmpty()) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid first name, must not be empty");
            }
        }

        while (true) {
            String lastName = JOptionPane.showInputDialog(null, "Enter your last name");
            if (!lastName.isEmpty()) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid last name, must not be empty");
            }
        }

        while (true) {
            String userName = JOptionPane.showInputDialog(null, "Enter your username");
            if (loginHandler.checkUsername(userName)) {
                JOptionPane.showMessageDialog(null, "Username successfully captured");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.");
            }
        }

        while (true) {
            String passWord = JOptionPane.showInputDialog(null, "Enter your password");
            if (loginHandler.checkPassword(passWord)) {
                JOptionPane.showMessageDialog(null, "Password successfully captured");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
            }
        }

        while (true) {
            String cellPhone = JOptionPane.showInputDialog(null, "Enter your cellphone number");
            if (loginHandler.checkCellNumber(cellPhone)) {
                JOptionPane.showMessageDialog(null, "Cell phone number successfully added");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Cell phone number incorrectly formatted.");
            }
        }

        JOptionPane.showMessageDialog(null, "Welcome to QuickChat");

        // Prepare test data
        loadTestData();

        String[] messageOptions = {"Send", "Stored", "Discard", "Display Options"};
        while (true) {
            int messageOption = JOptionPane.showOptionDialog(null, "Choose an action", "Message options",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, messageOptions, messageOptions[0]);

            switch (messageOption) {
                case 0: // Send Message
                    JOptionPane.showMessageDialog(null, "Option 1: Send messages\nOption 2: Show recently sent messages\nOption 3: Quit");
                    String optionStr = JOptionPane.showInputDialog("Enter your option (1, 2, or 3):");
                    int option = Integer.parseInt(optionStr);

                    switch (option) {
                        case 1:
                            Random random = new Random();
                            Set<String> usedIds = new HashSet<>();
                            String messageCountStr = JOptionPane.showInputDialog("How many messages would you like to send?");
                            int messageCount = Integer.parseInt(messageCountStr);

                            for (int i = 1; i <= messageCount; i++) {
                                String myMessage = JOptionPane.showInputDialog("Type your message (max 250 characters) " + i + ":");
                                if (myMessage.length() > 250) {
                                    JOptionPane.showMessageDialog(null, "Message is too long. Please keep it under 250 characters.");
                                    continue;
                                }

                                String id;
                                do {
                                    id = String.format("%010d", Math.abs(random.nextLong() % 1_000_000_0000L));
                                } while (!usedIds.add(id));

                                String recipientNumber = JOptionPane.showInputDialog("Please enter the recipient's phone number:");
                                if (loginHandler.checkCellNumber(recipientNumber)) {
                                    String messageHash = messageHandler.createMessageHash(myMessage);
                                    String messageDetails = "MessageID: " + id + "\nMessage Hash: " + messageHash +
                                                           "\nRecipient: " + recipientNumber + "\nMessage: " + myMessage;
                                    JOptionPane.showMessageDialog(null, "Message Sent:\n" + messageDetails);

                                    messageHandler.sentMessage("send", myMessage, recipientNumber);
                                    sentMessages.add(messageDetails);
                                    totalMessagesSent++;
                                } else {
                                    JOptionPane.showMessageDialog(null, "Cell phone number incorrectly formatted.");
                                }
                            }
                            JOptionPane.showMessageDialog(null, "Total messages sent: " + totalMessagesSent);
                            break;

                        case 2: // Desplay Sent Messages
                            StringBuilder sentMessagesDisplay = new StringBuilder("Sent messages:\n");
                            for (String msg : sentMessages) {
                                sentMessagesDisplay.append(msg).append("\n");
                            }
                            JOptionPane.showMessageDialog(null, sentMessagesDisplay.toString());
                            break;

                        case 3: // Quit
                            JOptionPane.showMessageDialog(null, "Goodbye! Total messages sent: " + totalMessagesSent);
                            return;

                        default:
                            JOptionPane.showMessageDialog(null, "Please enter a valid option");
                    }
                    break;

                case 1: // Stored Messages
                    String storeMessage = JOptionPane.showInputDialog("Enter a message to store (or press Cancel to view stored messages):");
                    if (storeMessage != null && !storeMessage.isEmpty()) {
                        String id = UUID.randomUUID().toString().replaceAll("[^0-9]", "").substring(0, 10);
                        String messageHash = messageHandler.createMessageHash(storeMessage);
                        String messageDetails = "MessageID: " + id + "\nMessage Hash: " + messageHash + "\nMessage: " + storeMessage;
                        JOptionPane.showMessageDialog(null, "Message Stored:\n" + messageDetails);

                        messageHandler.sentMessage("store", storeMessage, "");
                        storedMessages.add(messageDetails);
                    }
                    StringBuilder storedMessagesDisplay = new StringBuilder("Stored messages:\n" + messageHandler.printMessage());
                    JOptionPane.showMessageDialog(null, storedMessagesDisplay.toString());
                    break;

                case 2: // Discard Functionality
                    String discardMessage = JOptionPane.showInputDialog("Enter a message to discard:");
                    if (discardMessage != null && !discardMessage.isEmpty()) {
                        messageHandler.sentMessage("disregard", discardMessage, "");
                        disregardedMessages.add("[Disregarded] " + discardMessage);
                        JOptionPane.showMessageDialog(null, "Message discarded.");
                    }
                    break;

                case 3: // Display Options
                    String[] displayOptions = {"1. Display sender and recipient of all sent messages",
                                            "2. Display the longest sent message",
                                            "3. Search for a message ID and display the corresponding recipient and message",
                                            "4. Search for all messages sent or stored regarding a particular recipient",
                                            "5. Delete a message using the message hash",
                                            "6. Display a report that lists the full details of all the sent messages",
                                            "7. Display all stored messages",
                                            "Back"};
                    int displayOption = JOptionPane.showOptionDialog(null, "Choose a display option", "Display Options",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, displayOptions, displayOptions[0]);

                    switch (displayOption) {
                        case 0: // Display sender and recipient of all sent messages
                            StringBuilder senderRecipientDisplay = new StringBuilder("Sender and Recipient of Sent Messages:\n");
                            for (String msg : sentMessages) {
                                String[] parts = msg.split("\n");
                                for (String part : parts) {
                                    if (part.startsWith("Recipient:")) {
                                        senderRecipientDisplay.append("Sender: User, " + part + "\n");
                                    }
                                }
                            }
                            JOptionPane.showMessageDialog(null, senderRecipientDisplay.toString().isEmpty() ? "No sent messages." : senderRecipientDisplay.toString());
                            break;

                        case 1: // Display the longest sent message
                            String longestMessage = "";
                            int maxLength = -1;
                            for (String msg : sentMessages) {
                                String[] parts = msg.split("\n");
                                for (String part : parts) {
                                    if (part.startsWith("Message:")) {
                                        String messageContent = part.replace("Message: ", "");
                                        if (messageContent.length() > maxLength) {
                                            maxLength = messageContent.length();
                                            longestMessage = messageContent;
                                        }
                                    }
                                }
                            }
                            JOptionPane.showMessageDialog(null, "Longest sent message: " + (maxLength > -1 ? longestMessage : "No sent messages."));
                            break;

                        case 2: // Search for a message ID
                            String searchID = JOptionPane.showInputDialog("Enter Message ID to search:");
                            StringBuilder searchResult = new StringBuilder();
                            for (String msg : sentMessages) {
                                if (msg.contains("MessageID: " + searchID)) {
                                    searchResult.append(msg).append("\n");
                                }
                            }
                            JOptionPane.showMessageDialog(null, searchResult.toString().isEmpty() ? "No message found with ID: " + searchID : searchResult.toString());
                            break;

                        case 3: // Look for all messages sent or stored regarding a particular recipient
                            String recipient = JOptionPane.showInputDialog("Enter recipient number to search:");
                            StringBuilder recipientMessages = new StringBuilder("Messages for " + recipient + ":\n");
                            boolean found = false;
                            for (String msg : sentMessages) {
                                if (msg.contains("Recipient: " + recipient)) {
                                    recipientMessages.append(msg).append("\n");
                                    found = true;
                                }
                            }
                            for (String msg : storedMessages) {
                                if (msg.contains("Message: " + recipient)) { // Approximate match for stored
                                    recipientMessages.append(msg).append("\n");
                                    found = true;
                                }
                            }
                            JOptionPane.showMessageDialog(null, found ? recipientMessages.toString() : "No messages found for recipient: " + recipient);
                            break;

                        case 4: // Delete message using the message hash
                            String hashToDelete = JOptionPane.showInputDialog("Enter Message Hash to delete:");
                            boolean deleted = false;
                            for (int i = sentMessages.size() - 1; i >= 0; i--) {
                                if (sentMessages.get(i).contains("Message Hash: " + hashToDelete)) {
                                    sentMessages.remove(i);
                                    deleted = true;
                                }
                            }
                            JOptionPane.showMessageDialog(null, deleted ? "Message with hash " + hashToDelete + " successfully deleted." : "No message found with hash: " + hashToDelete);
                            break;

                        case 5: // Display a report that lists the full details of all the sent messages
                            StringBuilder report = new StringBuilder("Full Report of Sent Messages:\n");
                            for (String msg : sentMessages) {
                                report.append(msg).append("\n");
                            }
                            JOptionPane.showMessageDialog(null, report.toString().isEmpty() ? "No sent messages." : report.toString());
                            break;

                        case 6: // Display all stored messages
                            StringBuilder storedDisplay = new StringBuilder("All Stored Messages:\n");
                            for (String msg : storedMessages) {
                                storedDisplay.append(msg).append("\n");
                            }
                            JOptionPane.showMessageDialog(null, storedDisplay.toString().isEmpty() ? "No stored messages." : storedDisplay.toString());
                            break;

                        case 7: // Back
                            break;
                    }
                    break;
            }
        }
    }

    //Load test data method
    private static void loadTestData() {
        String[][] testData = {
           
        };

        Random random = new Random();
        Set<String> usedIds = new HashSet<>();
        for (String[] data : testData) {
            String recipient = data[0];
            String message = data[1];
            String flag = data[2];

            String id;
            do {
                id = String.format("%010d", Math.abs(random.nextLong() % 1_000_000_0000L));
            } while (!usedIds.add(id));

            String messageHash = new MessageHandler().createMessageHash(message);
            String messageDetails = "MessageID: " + id + "\nMessage Hash: " + messageHash +
                                   "\nRecipient: " + recipient + "\nMessage: " + message;

            if ("Sent".equalsIgnoreCase(flag)) {
                messageHandler.sentMessage("send", message, recipient);
                sentMessages.add(messageDetails);
                totalMessagesSent++;
            } else if ("Stored".equalsIgnoreCase(flag)) {
                messageHandler.sentMessage("store", message, recipient);
                storedMessages.add(messageDetails);
            } else if ("Disregard".equalsIgnoreCase(flag)) {
                messageHandler.sentMessage("disregard", message, recipient);
                disregardedMessages.add(messageDetails);
            }
        }
    }
}
