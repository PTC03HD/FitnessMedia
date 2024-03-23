/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.services.customizeServices;

import com.gym.entity.*;
import com.gym.services.coreServices.*;
import com.gym.services.entityForServices.BookScheSlot;
import com.gym.services.entityForServices.BookingPerUser;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author phamt
 */
public class BookingPerUserService {

    UserService userService;
    BookingService bookingService;
    ScheduleService scheduleService;
    BillService billService;
    BillBookingService billBookingService;
    SlotService slotService;

    public BookingPerUserService(UserService userService, BookingService bookingService, ScheduleService scheduleService, BillService billService) {
        this.userService = userService;
        this.bookingService = bookingService;
        this.scheduleService = scheduleService;
        this.billService = billService;
        this.billBookingService = new BillBookingService();
        this.slotService = new SlotService();
    }

    public BookingPerUser getBookingPerUserServiceByUserId(String UserId) throws SQLException, ClassNotFoundException {
        List<Booking> bookingList = bookingService.getByMakerId(UserId);
        List<Schedule> scheduleList = new ArrayList<>();
        List<BillBooking> billBookingList = billBookingService.getAll();
        List<Bill> billList = new ArrayList<>();
        List<Bill> billL = billService.getAll();
        List<Slot> slotL = slotService.getAll();
        LinkedHashMap<Booking, Long> billBooking = new LinkedHashMap<>();
        for (Booking book : bookingList) {
            Schedule s = scheduleService.getById(book.getScheduleId().toString());
            scheduleList.add(s);
            for (BillBooking bb : billBookingList) {
                if (Objects.equals(bb.getBookingId(), book.getBookingId())) {
                    billList.add(billL.stream().filter(bill -> bill.getBillId() == bb.getBillId()).toList().get(0));
                    billBooking.put(book, bb.getBillId());
                    System.out.println(book + "  " + bb.getBillId());
                }
            }
        }
        billList = billList.stream().distinct().toList();
        return new BookingPerUser(bookingList, scheduleList, billList, slotL, billBooking);
    }

    public HashMap<Long, BookingPerUser> getAll() throws SQLException, ClassNotFoundException {
        HashMap<Long, BookingPerUser> result = new HashMap<>();
        List<User> userList = userService.getAll();
        for (User u : userList) {
            result.put(u.getUserId(), getBookingPerUserServiceByUserId(u.getUserId().toString()));
        }
        return result;
    }
    
    

    public static void main(String[] args) {
        UserService userService = new UserService();
        BookingService bookingService = new BookingService();
        ScheduleService scheduleService = new ScheduleService();
        BillService billService = new BillService();
        BillBookingService billBookingService = new BillBookingService();
        SlotService slotService = new SlotService();
        BookingPerUserService bookingPerUserService = new BookingPerUserService(userService, bookingService, scheduleService, billService);
        
        try {
            BookingPerUser bk = bookingPerUserService.getBookingPerUserServiceByUserId(16+"");
            for(Map.Entry<Bill, List<BookScheSlot<Booking, Schedule, Slot>>> b : bk.billMap().entrySet()) {
                System.out.println(b.getValue().get(1).getSche());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
