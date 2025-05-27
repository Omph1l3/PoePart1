/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poepart1;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author RC_Student_lab
 */
class message {
    
    private static List<String> messageList = new ArrayList<>();
    
    public boolean checkMessageID(String messageID){
        //return messageID.matches("\\d(10)");
        return messageID.length() ==10;
    }
    
    public boolean validPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\+27[6-8]\\d{8}");
            
    }
    
    public String createMessageHash(String messageContent) {
        return UUID.nameUUIDFromBytes(messageContent.getBytes()).toString();
        
    }
    
    public String sentMessage(String option, String message) {
        switch (option.toLowerCase()) {
            case  "send":
               messageList.add(message);
               return "Message sent.";
            case "store":
                messageList.add("[Stored}" + message);
            case "disregard":
                return "message disregard";
            default:
                return "Invalid option.";
                
        }
    }
    public String printMessage() {
        return messageList.isEmpty() ? "No messages sent." : String.join("\n", messageList);
    }
    
    public int returnTotalMessages() {
        return messageList.size();
    }
    
}
