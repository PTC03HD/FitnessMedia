/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.controller;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author ba Tung
 */
public class Validate {
     private static final String PHONE_REGEX = "^\\(?(\\+\\d{1,3})?\\)?[-.\\s]?\\d{3}[-.\\s]?\\d{3}[-.\\s]?\\d{4}$";
    private static final String ID_REGEX = "^[0-9]{12}$";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)\\.com$";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";

    public  boolean isValidPhone(String phone) {
        Pattern pattern = Pattern.compile(PHONE_REGEX);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    public  boolean isValidId(String id) {
        Pattern pattern = Pattern.compile(ID_REGEX);
        Matcher matcher = pattern.matcher(id);
        return matcher.matches();
    }
    
    public  boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public  boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    
    public boolean validatePassword(String password) {

        if (password.length() < 6) {
            return false;
        }
        boolean containsDigit = false;
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                containsDigit = true;
                break;
            }
        }

        boolean containsLetter = false;
        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                containsLetter = true;
                break;
            }
        }

        return containsDigit && containsLetter;
    }
    
}
