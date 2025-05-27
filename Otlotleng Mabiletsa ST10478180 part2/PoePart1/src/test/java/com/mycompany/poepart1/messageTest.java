/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.poepart1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author RC_Student_lab
 */
public class messageTest {
    
    public messageTest() {
    }
    
   

    /**
     * Test of checkMessageID method, of class message.
     */
    @Test
    public void testCheckMessageID() {
        System.out.println("checkMessageID");
        String messageID = "";
        message instance = new message();
        boolean expResult = false;
        boolean result = instance.checkMessageID(messageID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validPhoneNumber method, of class message.
     */
    @Test
    public void testValidPhoneNumber() {
        System.out.println("validPhoneNumber");
        String phoneNumber = "";
        message instance = new message();
        boolean expResult = false;
        boolean result = instance.validPhoneNumber(phoneNumber);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createMessageHash method, of class message.
     */
    @Test
    public void testCreateMessageHash() {
        System.out.println("createMessageHash");
        String messageContent = "";
        message instance = new message();
        String expResult = "";
        String result = instance.createMessageHash(messageContent);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sentMessage method, of class message.
     */
    @Test
    public void testSentMessage() {
        System.out.println("sentMessage");
        String option = "";
        String message = "";
        message instance = new message();
        String expResult = "";
        String result = instance.sentMessage(option, message);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printMessage method, of class message.
     */
    @Test
    public void testPrintMessage() {
        System.out.println("printMessage");
        message instance = new message();
        String expResult = "";
        String result = instance.printMessage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of returnTotalMessages method, of class message.
     */
    @Test
    public void testReturnTotalMessages() {
        System.out.println("returnTotalMessages");
        message instance = new message();
        int expResult = 0;
        int result = instance.returnTotalMessages();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
