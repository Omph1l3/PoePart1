/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.poepart1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.swing.JOptionPane;

public class PoePart1 {

    private static ArrayList<String> sentMessages = new ArrayList<>();
    private static ArrayList<String> disregardMessage = new ArrayList<>();

    public static void main(String[] args) {
        Login myObject = new Login();

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
            if (myObject.checkUsername(userName)) {
                JOptionPane.showMessageDialog(null, "Username successfully captured");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.");
            }
        }

        while (true) {
            String passWord = JOptionPane.showInputDialog(null, "Enter your password");
            if (myObject.checkPassword(passWord)) {
                JOptionPane.showMessageDialog(null, "Password successfully captured");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
            }
        }

        while (true) {
            String cellPhone = JOptionPane.showInputDialog(null, "Enter your cellphone number");
            if (myObject.checkCellNumber(cellPhone)) {
                JOptionPane.showMessageDialog(null, "Cell phone number successfully added");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Cell phone number incorrectly formatted or does not contain international code.");
            }
        }

        JOptionPane.showMessageDialog(null, "Welcome to QuickChat");

        String[] messageOptions = {"Send", "Stored", "Discard"};

        while (true) {
            int messageOption = JOptionPane.showOptionDialog(null, "Choose an action for this message", "Message options",
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

                                JOptionPane.showMessageDialog(null, "Message: " + myMessage + "\nMessage ID: " + id + "\n");
                            }

                            String recipientNumber = JOptionPane.showInputDialog("Please enter the recipient's phone number:");
                            if (validPhoneNumber(recipientNumber)) {
                                String myMessage = JOptionPane.showInputDialog("Enter the message you wish to send:");
                                sentMessages.add("Message: " + myMessage + " Sent to: " + recipientNumber);
                                JOptionPane.showMessageDialog(null, "Message sent successfully!");
                            } else {
                                JOptionPane.showMessageDialog(null, "Cell phone number incorrectly formatted or does not contain international code.");
                            }
                            break;

                        case 2: // Show Sent Messages
                            StringBuilder storedMessages = new StringBuilder("Stored messages:\n");
                            for (String msg : sentMessages) {
                                storedMessages.append(msg).append("\n");
                            }
                            JOptionPane.showMessageDialog(null, storedMessages.toString());
                            break;

                        case 3: // Quit
                            JOptionPane.showMessageDialog(null, "Goodbye!");
                            return;

                        default:
                            JOptionPane.showMessageDialog(null, "Please enter a valid option");
                    }
                    break;

                case 1: // Stored Messages
                    JOptionPane.showMessageDialog(null, "Coming soon");
                    break;

                case 2: // Discard Functionality
                    JOptionPane.showMessageDialog(null, "Discard functionality not implemented yet");
                    break;
            }
        }
    }

    public static boolean validPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^\\+\\d{1,3}\\d{7,15}$");
    }
}