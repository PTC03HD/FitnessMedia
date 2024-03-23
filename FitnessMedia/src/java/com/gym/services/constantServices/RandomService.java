/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.services.constantServices;

import java.util.Random;

/**
 *
 * @author admin
 */
public class RandomService {
    public String getRandomCode(int n) {
        String ALLOWED_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789";
        String code = "";
        Random random = new Random();
        
        for (int i = 0; i < n; i++) {
            int randomIndex = random.nextInt(ALLOWED_CHARACTERS.length());
            code += (Character.toString(ALLOWED_CHARACTERS.charAt(randomIndex)));
        }
        
        return code;
    }
}
