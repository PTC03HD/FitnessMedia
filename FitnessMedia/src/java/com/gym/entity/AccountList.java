/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.entity;

/**
 *
 * @author ba Tung
 */
public class AccountList {
    private int user_id;
    private String user_name;
    private String user_email;
    private int role_id;
    private String role_name;
    private boolean deleted;

    public AccountList() {
    }

    public AccountList(int user_id, String user_name, String user_email, int role_id, String role_name, boolean deleted) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_email = user_email;
        this.role_id = role_id;
        this.role_name = role_name;
        this.deleted = deleted;
    }

    

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    
    

    @Override
    public String toString() {
        return "AccountList{" + "user_id=" + user_id + ", user_name=" + user_name + ", user_email=" + user_email + ", role_id=" + role_id + ", role_name=" + role_name + '}';
    }
    
    
}
