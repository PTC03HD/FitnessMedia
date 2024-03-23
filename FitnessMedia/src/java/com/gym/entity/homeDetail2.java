/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.entity;

/**
 *
 * @author ba Tung
 */
public class homeDetail2 {
    private String img_avatar;
    private String img_wall;
    private String per_phone;
    private String totalRoom;

    public homeDetail2() {
    }

    public homeDetail2(String img_avatar, String img_wall, String per_phone, String totalRoom) {
        this.img_avatar = img_avatar;
        this.img_wall = img_wall;
        this.per_phone = per_phone;
        this.totalRoom = totalRoom;
    }

    public String getImg_avatar() {
        return img_avatar;
    }

    public void setImg_avatar(String img_avatar) {
        this.img_avatar = img_avatar;
    }

    public String getImg_wall() {
        return img_wall;
    }

    public void setImg_wall(String img_wall) {
        this.img_wall = img_wall;
    }

    public String getPer_phone() {
        return per_phone;
    }

    public void setPer_phone(String per_phone) {
        this.per_phone = per_phone;
    }

    public String getTotalRoom() {
        return totalRoom;
    }

    public void setTotalRoom(String totalRoom) {
        this.totalRoom = totalRoom;
    }

    @Override
    public String toString() {
        return "homeDetail2{" + "img_avatar=" + img_avatar + ", img_wall=" + img_wall + ", per_phone=" + per_phone + ", totalRoom=" + totalRoom + '}';
    }

    
}
