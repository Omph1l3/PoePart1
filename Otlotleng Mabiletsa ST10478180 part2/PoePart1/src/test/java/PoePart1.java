/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

import java.util.Scanner;

/**
 *
 * @author RC_Student_lab
 */
public class PoePart1 {
    public static void main(String[] args) {
        registerUsers();
    }
        public static void registerUsers(){
        Login myObject = new Login();
        Scanner scan = new Scanner(System.in);
        String firstName;
        String lastName;
        String username;
        String password;
        
        while(true){
            System.out.println("Enter your first name");
            firstName = scan.nextLine();
            if(!firstName.isEmpty()){
            break;
            }else{
                System.out.println("invalid firt name, must not be empty");
                
                
        }
        }
        while(true){
            System.out.println("Enter your last name");
            lastName = scan.nextLine();
            if(!lastName.isEmpty()){
            break;
            }else{
                System.out.println("invalid last name, must not be empty");
                
                
        }
        }
        
        while(true){
            
            System.out.println("Please enter your username: ");
            username = "kyl_1";

            if(myObject.checkUsername(username)){
                System.out.println("Username successfully captured");
                break;
            }else{
                System.out.println("Username is not correctly fotmatted,please ensure that your usrename contains an underscore and is no more than five characters in length.");

            }
        }
        
        while(true){
        
            System.out.println("Please enter your password");
             password ="kyle!!!!!!!!";

            if (myObject.checkPassword(password)){
                System.out.println("Password successfuly captured");
                break;
            }else{
                 System.out.println("Password is not correctly formated,please enseure that the password contains at least eight characters,a capital letter,a number and a special character.");

            }
        }
        
        while(true){
        
            System.out.println("please enter cell phone number ");
            String cellnumber =scan.nextLine();

            if(myObject.checkCellNumber(cellnumber)){
                System.out.println("Cell phone number successfully added");
                break;
            }else{
                System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
                

            }
        }
        
        //Prompting the user for login details
       System.out.println("\nUser Log in");
       while(true){
           System.out.println("Please enter your username");
           String checkUsername = scan.nextLine();
           
          System.out.println("Please enter your password");
          String checkPassword = scan.nextLine();
          
          
          if(checkUsername.equals(username) && checkPassword.equals(password)){
              System.out.println("Welcome " + firstName +" "+lastName + ", it is great to see you again");
              break;
          }else{
              System.out.println("Username or password incorrect, please try again\n");
          }
           
           
       }
    }
    
    
}
