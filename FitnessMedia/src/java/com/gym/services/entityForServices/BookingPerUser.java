/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.services.entityForServices;

import com.gym.entity.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.*;

/**
 *
 * @author phamt
 */
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BookingPerUser {

    private List<Booking> bookingList;
    private List<Schedule> scheduleList;
    private List<Bill> billList;
    private List<Slot> slotList;
    private LinkedHashMap<Booking, Long> billBooking;

    public boolean isExperienced(Long gymId) {
        Long[] scheduleId = {null};
        for (Schedule s : scheduleList) {
            if (s.getGymId() == gymId) {
                scheduleId[0] = s.getScheduleId();
                break;
            }
        }
        if (scheduleId[0] != null) {
            return bookingList.stream()
                    .anyMatch(b -> b.isExperienced() && b.getScheduleId().equals(scheduleId[0]));
        }
        return false;
    }

    public LinkedHashMap<Bill, List<BookScheSlot<Booking, Schedule, Slot>>> billMap() {
        LinkedHashMap<Bill, List<BookScheSlot<Booking, Schedule, Slot>>> BookingPerBill = new LinkedHashMap<>();
        List<BookScheSlot<Booking, Schedule, Slot>> bookScheSlot = new ArrayList<>();
        LinkedHashMap<Long, Schedule> scheduleMap = scheduleMap();
        LinkedHashMap<Long, Slot> slotMap = SlotMap();

        for (Bill b : billList) {
            for (Map.Entry<Booking, Long> bk : billBooking.entrySet()) {
                if (Objects.equals(bk.getValue(), b.getBillId())) {
                    bookScheSlot.add(new BookScheSlot(bk.getKey(), scheduleMap.get(bk.getKey().getBookingId()), slotMap.get(bk.getKey().getScheduleId())));
                }
            }
            BookingPerBill.put(b, bookScheSlot);
            bookScheSlot = new ArrayList<>();
        }

        return BookingPerBill;
    }

    public LinkedHashMap<Long, Schedule> scheduleMap() {
        LinkedHashMap<Long, Schedule> schMap = new LinkedHashMap<>();
        for (Booking b : bookingList) {
            for (Schedule s : scheduleList) {
                if (Objects.equals(b.getScheduleId(), s.getScheduleId())) {
                    schMap.put(b.getBookingId(), s);
                }
            }
        }
        return schMap;
    }

    public LinkedHashMap<Long, Slot> SlotMap() {
        LinkedHashMap<Long, Slot> slMap = new LinkedHashMap<>();
        for (Slot sl : slotList) {
            for (Schedule s : scheduleList) {
                if (s.getSlotId() == sl.getSlotId()) {
                    slMap.put(s.getScheduleId(), sl);
                }
            }
        }
        return slMap;
    }

}
