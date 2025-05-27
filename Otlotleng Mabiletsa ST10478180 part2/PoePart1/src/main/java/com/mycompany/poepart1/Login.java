/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poepart1;

/**
 *
 * @author RC_Student_lab
 */
class Login {
    public boolean checkUsername(String username){
        if (username.contains("_")&& username.length() <=5){
            return true;
          
      }else{
            return false;
        }
    }
    
    public boolean checkPassword(String password){
        if(password.length() >=8 && password.matches(".*[A-Z].*") && password.matches(".*[a-z].*") && password.matches(".*[0-10].*") && password.matches(".*[!@#$%^&+=?].*")){
            return true;
            
        }else{
            return false;
        }
    }
    
    public boolean checkCellNumber(String cellnumber){
        if(cellnumber.matches("\\+27[6-8]\\d{8}")){
            return true;
        }else{
            return false;
        }
    }
    
}
