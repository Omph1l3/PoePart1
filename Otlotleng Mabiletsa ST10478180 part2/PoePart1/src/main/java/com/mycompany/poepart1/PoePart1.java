/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.poepart1;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author RC_Student_lab
 */
public class PoePart1 {

    public static void main(String[] args) {

        Login myObject = new Login();
        Scanner scan = new Scanner(System.in);
        String firstName;
        String lastName;
        String username;
        String password;

        while (true) {
            System.out.println("Enter your first name");
            firstName = scan.nextLine();
            if (!firstName.isEmpty()) {
                break;
            } else {
                System.out.println("invalid firt name, must not be empty");

            }
        }
        while (true) {
            System.out.println("Enter your last name");
            lastName = scan.nextLine();
            if (!lastName.isEmpty()) {
                break;
            } else {
                System.out.println("invalid last name, must not be empty");

            }
        }

        while (true) {

            System.out.println("Please enter your username: ");
            username = scan.nextLine();

            if (myObject.checkUsername(username)) {
                System.out.println("Username successfully captured");
                break;
            } else {
                System.out.println("Username is not correctly fotmatted,please ensure that your usrename contains an underscore and is no more than five characters in length.");

            }
        }

        while (true) {

            System.out.println("Please enter your password");
            password = scan.nextLine();

            if (myObject.checkPassword(password)) {
                System.out.println("Password successfuly captured");
                break;
            } else {
                System.out.println("Password is not correctly formated,please enseure that the password contains at least eight characters,a capital letter,a number and a special character.");

            }
        }

        while (true) {

            System.out.println("please enter cell phone number ");
            String cellnumber = scan.nextLine();

            if (myObject.checkCellNumber(cellnumber)) {
                System.out.println("Cell phone number successfully added");
                break;
            } else {
                System.out.println("Cell phone number incorrectly formatted or does not contain international code.");

            }
        }

        //Prompting the user for login details
        System.out.println("\nUser Log in");
        while (true) {
            System.out.println("Please enter your username");
            String checkUsername = scan.nextLine();

            System.out.println("Please enter your password");
            String checkPassword = scan.nextLine();

            if (checkUsername.equals(username) && checkPassword.equals(password)) {
                System.out.println("Welcome " + firstName + " " + lastName + ", it is great to see you again");
                break;
            } else {
                System.out.println("Username or password incorrect, please try again\n");
            }

        }
        message message = new message();
        String recipientNumber;
        String myMessage = "";

        System.out.println("Welcom to QuickChat");

        while (true) {
            System.out.println("Option 1:Send messages");
            System.out.println("Option 2:Show recently sent messages");
            System.out.println("Option 3:Quit");
            System.out.println("\nEnter your option: "); //choose
            int option = scan.nextInt();
            scan.nextLine();

            switch (option) {
                case 1 -> {
                    while (true) {
                        Random random = new Random();
                        Set<String> usedIds = new HashSet<>();

                        System.out.println("How many messages would you like to send");
                        int messageCount = Integer.parseInt(scan.nextLine());

                        for (int i = 1; i <= messageCount; i++) {
                            System.out.println("Type you message(max 250 characters)" + i + ":");
                            myMessage = scan.nextLine();

                            //Generate unique 10-digit ID
                            String id;
                            do {
                                id = String.format("%010d", Math.abs(random.nextLong() % 1_000_000_0000L));
                            } while (!usedIds.add(id));//Add and check uniqueness

                            System.out.println(myMessage);
                            System.out.println("Message ID: " + id + "\n");
                        }
                        System.out.println("Please enter the recipient's phone number: ");
                        recipientNumber = scan.nextLine();

                        if (message.validPhoneNumber(recipientNumber)) {
                            break;
                        } else {
                            System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
                        }
                    }
                    while (true) {
                        if (myMessage.length() > 50) {
                            System.out.println("Please enter a message of less than 50 characters");
                        } else {
                            break;
                          
                        }
                    }

                    System.out.println("Please choose an option");
                    System.out.println("send");
                    System.out.println("Stores");
                    System.out.println("Disregard");
                    String choose = scan.nextLine();
                    System.out.println(message.sentMessage(choose, myMessage));
                }

                case 2 -> System.out.println("Coming soon");
                case 3 -> {
                    System.out.println("Quit\n");
                    return;
                }
                default -> System.out.println("Please enter valid option\n");

            }

        }

    }

}
