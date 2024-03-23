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
public class CustomerSchedule {
    private int schedule_id;
    private Date schedule_date;
    private Long slot_id;
    private int slot_order;
    private int start_time;
    private int end_time;
    private int gym_id;
    private String gym_name;
    private int booking_id;
    private int maker_id;
    private int user_id;
    private String imgWall;

    public CustomerSchedule() {
    }

    public CustomerSchedule(int schedule_id, Date schedule_date, long slot_id, int slot_order, int start_time, int end_time, int gym_id, String gym_name, int booking_id, int maker_id, int user_id, String imgWall) {
        this.schedule_id = schedule_id;
        this.schedule_date = schedule_date;
        this.slot_id = slot_id;
        this.slot_order = slot_order;
        this.start_time = start_time;
        this.end_time = end_time;
        this.gym_id = gym_id;
        this.gym_name = gym_name;
        this.booking_id = booking_id;
        this.maker_id = maker_id;
        this.user_id = user_id;
        this.imgWall = imgWall;
    }

    public int getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(int schedule_id) {
        this.schedule_id = schedule_id;
    }

    public Date getSchedule_date() {
        return schedule_date;
    }

    public void setSchedule_date(Date schedule_date) {
        this.schedule_date = schedule_date;
    }

    public Long getSlot_id() {
        return slot_id;
    }

    public void setSlot_id(long slot_id) {
        this.slot_id = slot_id;
    }

    public int getSlot_order() {
        return slot_order;
    }

    public void setSlot_order(int slot_order) {
        this.slot_order = slot_order;
    }

    public int getStart_time() {
        return start_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public int getEnd_time() {
        return end_time;
    }

    public void setEnd_time(int end_time) {
        this.end_time = end_time;
    }

    public int getGym_id() {
        return gym_id;
    }

    public void setGym_id(int gym_id) {
        this.gym_id = gym_id;
    }

    public String getGym_name() {
        return gym_name;
    }

    public void setGym_name(String gym_name) {
        this.gym_name = gym_name;
    }

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public int getMaker_id() {
        return maker_id;
    }

    public void setMaker_id(int maker_id) {
        this.maker_id = maker_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getImgWall() {
        return imgWall;
    }

    public void setImgWall(String imgWall) {
        this.imgWall = imgWall;
    }

    @Override
    public String toString() {
        return "CustomerSchedule{" + "schedule_id=" + schedule_id + ", schedule_date=" + schedule_date + ", slot_id=" + slot_id + ", slot_order=" + slot_order + ", start_time=" + start_time + ", end_time=" + end_time + ", gym_id=" + gym_id + ", gym_name=" + gym_name + ", booking_id=" + booking_id + ", maker_id=" + maker_id + ", user_id=" + user_id + ", imgWall=" + imgWall + '}';
    }

    
    
    
    
}
