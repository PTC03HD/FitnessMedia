/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.services.customizeServices;

import com.gym.services.entityForServices.WeekOfYear;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author phamt
 */
public class WeekOfYearService {

    public static Map<Integer, String> TIME_SLOTS = new HashMap<>();

    static {
        HashMap<Integer, String> tempMap = new HashMap<>();
        tempMap.put(0, "24:00 - 24:59");
        for (int i = 1; i < 24; i++) {
            String startTime = String.format("%02d:00", i);
            String endTime = String.format("%02d:59", i);
            tempMap.put(i, startTime + " - " + endTime);
        }
        TIME_SLOTS = Collections.unmodifiableMap(tempMap);
    }

    public long getNumOfWeekInYear(int yearNum) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, yearNum);
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DAY_OF_MONTH, 31);

        int ordinalDay = cal.get(Calendar.DAY_OF_YEAR);
        int weekDay = cal.get(Calendar.DAY_OF_WEEK) - 1; // Sunday = 0
        int numberOfWeeks = (ordinalDay - weekDay + 10) / 7;
        return numberOfWeeks;
    }

    public LocalDate getFirstDayOfWeek(int weekNumber) {
        return LocalDate
                .of(Year.now().getValue(), 2, 1)
                .with(WeekFields.of(Locale.getDefault()).getFirstDayOfWeek().plus(1))
                .with(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear(), weekNumber);
    }

    public LocalDate getLastDayOfWeek(int weekNumber) {
        return getFirstDayOfWeek(weekNumber).plusDays(6);
    }

    public Date ConvertLocalDateToDate(LocalDate lDate) {
        return Date.from(lDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public LocalDate ConvertDateToLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public int getCurrentWeekNumber() {
        LocalDate currentDate = LocalDate.now();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int weekOfYear = currentDate.get(weekFields.weekOfWeekBasedYear());
        return weekOfYear;
    }

    public HashMap<Integer, WeekOfYear> getAllWeek(int year) {
        HashMap<Integer, WeekOfYear> result = new HashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM");
        String startDate, lastDate;
        for (int i = 1; i <= getNumOfWeekInYear(year); i++) {
            startDate = dateFormat.format(ConvertLocalDateToDate(getFirstDayOfWeek(i)));
            lastDate = dateFormat.format(ConvertLocalDateToDate(getLastDayOfWeek(i)));
            WeekOfYear tmp = WeekOfYear.builder()
                    .startDate(startDate)
                    .endDate(lastDate)
                    .build();
            result.put(i, tmp);
        }
        return result;
    }

    public HashMap<Integer, Date> getAllDayInWeek(int weekIndex) {
        HashMap<Integer, Date> result = new HashMap<>();
        LocalDate startDate = getFirstDayOfWeek(weekIndex);
        LocalDate endDate = getLastDayOfWeek(weekIndex).plusDays(1);
        List<LocalDate> listOfDates = startDate.datesUntil(endDate).collect(Collectors.toList());
        int index = 0;
        for (LocalDate d : listOfDates) {
            result.put(index, ConvertLocalDateToDate(d));
            index++;
        }
        return result;
    }
}
