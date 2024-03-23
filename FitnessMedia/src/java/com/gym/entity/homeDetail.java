/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.entity;

import java.util.Date;

/**
 *
 * @author ba Tung
 */
public class homeDetail {

    private int gym_id;
    private int owner_id;
    private String gym_name;
    private Double gym_cost;
    private String gym_nation;
    private String gym_province;
    private String gym_wall;
    private String gym_avatar;
    private boolean closed;

    public homeDetail() {
    }

    public homeDetail(int gym_id, int owner_id, String gym_name, Double gym_cost, String gym_nation, String gym_province, String gym_wall, String gym_avatar, boolean closed) {
        this.gym_id = gym_id;
        this.owner_id = owner_id;
        this.gym_name = gym_name;
        this.gym_cost = gym_cost;
        this.gym_nation = gym_nation;
        this.gym_province = gym_province;
        this.gym_wall = gym_wall;
        this.gym_avatar = gym_avatar;
        this.closed = closed;
    }

    public int getGym_id() {
        return gym_id;
    }

    public void setGym_id(int gym_id) {
        this.gym_id = gym_id;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public String getGym_name() {
        return gym_name;
    }

    public void setGym_name(String gym_name) {
        this.gym_name = gym_name;
    }

    public Double getGym_cost() {
        return gym_cost;
    }

    public void setGym_cost(Double gym_cost) {
        this.gym_cost = gym_cost;
    }

    public String getGym_nation() {
        return gym_nation;
    }

    public void setGym_nation(String gym_nation) {
        this.gym_nation = gym_nation;
    }

    public String getGym_province() {
        return gym_province;
    }

    public void setGym_province(String gym_province) {
        this.gym_province = gym_province;
    }

    public String getGym_wall() {
        return gym_wall;
    }

    public void setGym_wall(String gym_wall) {
        this.gym_wall = gym_wall;
    }

    public String getGym_avatar() {
        return gym_avatar;
    }

    public void setGym_avatar(String gym_avatar) {
        this.gym_avatar = gym_avatar;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    @Override
    public String toString() {
        return "homePage{" + "gym_id=" + gym_id + ", owner_id=" + owner_id + ", gym_name=" + gym_name + ", gym_cost=" + gym_cost + ", gym_nation=" + gym_nation + ", gym_province=" + gym_province + ", gym_wall=" + gym_wall + ", gym_avatar=" + gym_avatar + ", closed=" + closed + '}';
    }

    
   

}
