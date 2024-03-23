/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.services.entityForServices;

/**
 *
 * @author ba Tung
 */
public class TotalReview {
    private int total;

    public TotalReview() {
    }

    public TotalReview(int total) {
        this.total = total;
    }
    

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "TotalReview{" + "total=" + total + '}';
    }
    
}
