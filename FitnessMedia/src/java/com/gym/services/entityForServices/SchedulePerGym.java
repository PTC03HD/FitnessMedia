/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.services.entityForServices;

import com.gym.entity.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author phamt
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SchedulePerGym {

    Gym gym;
    List<Schedule> schedule;
    List<Slot> slot;

    public LocalDate ConvertDateToLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
    
    public Schedule getScheduleById(String scheduleId) {
        long id = Long.parseLong(scheduleId);
        for(Schedule s : schedule) {
            if(s.getScheduleId().equals(id))
                return s;
        }
        return null;
    }
    
    public Schedule getSchedulesBySlotId(Long slotId, LocalDate Date) {
        Schedule sch = null;
        for(Schedule s : schedule) {
            if(s.getSlotId().equals(slotId) && ConvertDateToLocalDate(s.getScheduleDate()).equals(Date)) {
                sch = s;
                break;
            }
        }
        return sch;
    }
    
    public Slot getSlotByslotOrder(String sId) {
        Long slotOrder = Long.parseLong(sId);
        for(Slot s: slot) {
            if(s.getSlotOrder().equals(slotOrder))
                return s;
        }
        return null;
    }
    
    public Slot getSlotBySlotId(String sId) {
        Long slotId = Long.parseLong(sId);
        for(Slot s : slot) {
            if(s.getSlotId() == slotId) {
                return s;
            }
        }
        return null;
    }
    
    public boolean isContainDate(String date) {
        for(Schedule s : schedule) {
            if(s.getScheduleDate().toString().equals(date))
                return true;
        }
        return false;
    }    

}
