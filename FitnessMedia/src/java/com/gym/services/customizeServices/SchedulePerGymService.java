/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.services.customizeServices;

import com.gym.DAO.CustomerScheduleDAO;
import com.gym.entity.*;
import com.gym.services.coreServices.*;
import com.gym.services.entityForServices.ScheduleCustomer;
import com.gym.services.entityForServices.SchedulePerGym;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author phamt
 */
public class SchedulePerGymService {

    private GymService gymService;
    private ScheduleService scheduleService;
    private SlotService slotService;

    public SchedulePerGymService(GymService gymService, ScheduleService scheduleService, SlotService slotService) {
        this.gymService = gymService;
        this.scheduleService = scheduleService;
        this.slotService = slotService;
    }

    public SchedulePerGym getSchedulePerGymByGymid(String gymId) throws SQLException, ClassNotFoundException {
        List<Schedule> scheduleList = scheduleService.getAllByGymId(gymId);
        List<Slot> slotList = new ArrayList<>();
        for (Schedule schedule : scheduleList) {
            if (!slotList.stream().anyMatch(slot -> slot.getSlotId() == schedule.getSlotId())) {
                Slot s = slotService.getById(schedule.getSlotId().toString());
                slotList.add(s);
            }
        }
        Gym gym = gymService.getById(gymId);
        return new SchedulePerGym(gym, scheduleList, slotList);
    }

    public HashMap<Long, SchedulePerGym> getAll() throws SQLException, ClassNotFoundException {
        HashMap<Long, SchedulePerGym> result = new HashMap<>();
        List<Gym> ls = gymService.getAll();
        for (Gym g : ls) {
            result.put(g.getGymId(), getSchedulePerGymByGymid(g.getGymId().toString()));
        }
        return result;
    }

    public long[][] getGymTimetable(String gymId, int weekIndex) {
        WeekOfYearService weekOfYearService = new WeekOfYearService();
        List<Schedule> schedulelist = new ArrayList<>();
        List<Slot> slotls = new ArrayList<>();
        HashMap<Integer, Date> dayinweek = weekOfYearService.getAllDayInWeek(weekIndex);

        try {
            slotls = slotService.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            schedulelist = getSchedulePerGymByGymid(gymId).getSchedule();
        } catch (Exception e) {
            e.printStackTrace();
        }

        long arr[][] = new long[24][7];
        int j = 0;
        for (Slot slot : slotls) {
            j = 0;
            for (Map.Entry<Integer, Date> entry : dayinweek.entrySet()) {
                arr[slot.getSlotOrder().intValue() - 1][j] = -1;
                for (Schedule s : schedulelist.stream().filter(s -> s.getSlotId().equals(slot.getSlotId())).toList()) {
                    if (weekOfYearService.ConvertDateToLocalDate(s.getScheduleDate()).
                            equals(weekOfYearService.ConvertDateToLocalDate(entry.getValue()))) {
                        arr[slot.getSlotOrder().intValue() - 1][j] = s.getScheduleId();
                    }
                }
                j++;
            }
        }
        return arr;
    }

    public String[][] getCurrentBookUser(long[][] timetable) {
        BookingService bookingService = new BookingService();
        ScheduleService scheduleService = new ScheduleService();

        List<Booking> bookingLs = new ArrayList<>();
        try {
            bookingLs = bookingService.getAll().stream().filter(b -> !b.isDeleted()).toList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[][] result = new String[24][7];
        Schedule s = null;
        int curBookingUser = 0;
        for (int i = 1; i <= 24; i++) {
            for (int j = 1; j <= 7; j++) {
                try {
                    s = scheduleService.getById(timetable[i - 1][j - 1] + "");
                    curBookingUser = bookingService.getBookNumberOfASchedule(timetable[i - 1][j - 1], bookingLs);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                result[i - 1][j - 1] = curBookingUser + "/" + s.getMaxNumber() + " slots available";
            }
        }
        return result;
    }

    public ScheduleCustomer getScheduleByMakerid(String makerId) throws SQLException, ClassNotFoundException {
        CustomerScheduleDAO dao = new CustomerScheduleDAO();
        List<CustomerSchedule> schedulelist = dao.TimeSchedule(makerId);
        List<Slot> slotList = new ArrayList<>();
        for (CustomerSchedule schedule : schedulelist) {
            String slotid = Long.toString(schedule.getSlot_id());
            Slot s = slotService.getById(slotid);
            slotList.add(s);
        }
        return new ScheduleCustomer(schedulelist, slotList);
    }

    public CustomerSchedule[][] getCustomerTimetable(String makerId, int weekIndex) {
        WeekOfYearService weekOfYearService = new WeekOfYearService();
        List<CustomerSchedule> schedulelist = new ArrayList<>();
        List<Slot> slotls = new ArrayList<>();
        HashMap<Integer, Date> dayinweek = weekOfYearService.getAllDayInWeek(weekIndex);

        try {
            slotls = slotService.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            schedulelist = getScheduleByMakerid(makerId).getSchedule();
        } catch (Exception e) {
            e.printStackTrace();
        }

        CustomerSchedule arr[][] = new CustomerSchedule[24][7];
        int j = 0;
        for (Slot slot : slotls) {
            j = 0;
            for (Map.Entry<Integer, Date> entry : dayinweek.entrySet()) {
                 arr[slot.getSlotOrder().intValue() - 1][j] = new CustomerSchedule();
                for (CustomerSchedule s : schedulelist.stream().filter(s -> s.getSlot_id().equals(slot.getSlotId())).toList()) {
                    if (weekOfYearService.ConvertDateToLocalDate(s.getSchedule_date()).
                            equals(weekOfYearService.ConvertDateToLocalDate(entry.getValue()))) {
                        arr[slot.getSlotOrder().intValue() - 1][j] = s;
                    }
                }
                j++;
            }
        }
        return arr;
    }

    public static void main(String args[]) {
        GymService gymService = new GymService();
        ScheduleService scheduleService = new ScheduleService();
        SlotService slotService = new SlotService();
        SchedulePerGymService schedulePerGymService = new SchedulePerGymService(gymService, scheduleService, slotService);
        long[][] arr = schedulePerGymService.getGymTimetable("3", 9);
        for(int i = 0; i < 7; i++) {
            for(int j = 0; j<24; j++) {
                System.out.print(arr[j][i] );
            }
            System.out.println("");
        }
    }

}
