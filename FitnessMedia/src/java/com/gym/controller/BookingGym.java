/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.gym.controller;

import com.gym.entity.*;
import com.gym.services.coreServices.*;
import com.gym.services.customizeServices.*;
import com.gym.services.entityForServices.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.swing.JSpinner;

/**
 *
 * @author phamt
 */
public class BookingGym extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Booking</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Booking at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setHeader("Expires", "0"); // Proxies.
        Object u = request.getSession().getAttribute("userAuthorization");
        Long gymId = null;
        try {
            gymId = (Long) request.getSession().getAttribute("gymIdValue");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (u == null || gymId == null) {
            response.sendRedirect("home");
        } else {
            request.getRequestDispatcher("Booking.jsp").forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        GymService gymService = new GymService();
        SlotService slotService = new SlotService();
        ScheduleService scheduleService = new ScheduleService();
        SchedulePerGymService schedulePerGymService = new SchedulePerGymService(gymService, scheduleService, slotService);
        WeekOfYearService weekOfYearService = new WeekOfYearService();
        BillService billService = new BillService();
        BookingService bookingService = new BookingService();
        BillBookingService billBookingService = new BillBookingService();

        UserAuthorization userA = (UserAuthorization) request.getSession().getAttribute("userAuthorization");

        Long gymId = 1L;
        try {
            gymId = Long.valueOf(request.getParameter("gymId"));
            request.setAttribute("gymId", gymId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //choose week
        LocalDate curDate = LocalDate.now();
        request.setAttribute("curDate", curDate);
        int year = Integer.parseInt(Year.now().toString()); //select year
        request.setAttribute("totalWeek", weekOfYearService.getNumOfWeekInYear(year));
        request.setAttribute("WeekMap", weekOfYearService.getAllWeek(year));//Display week
        int weekIndex = weekOfYearService.getCurrentWeekNumber();
        try {
            weekIndex = Integer.parseInt(request.getParameter("Weeks"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        HashMap<Integer, Date> dayInWeek = weekOfYearService.getAllDayInWeek(weekIndex);
        request.setAttribute("dayInWeek", dayInWeek);
        request.setAttribute("curWeekNum", weekIndex);
        //end choose week

        SchedulePerGym schPerGym = null;
        try {
            schPerGym = schedulePerGymService.getSchedulePerGymByGymid(gymId.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //booking gym
        Object bookBtn = request.getParameter("bookBtn");
        boolean check = true;
        request.setAttribute("bookBtn", bookBtn);
        if (bookBtn != null) {
            LocalDate bookDate = LocalDate.now();
            int slotStart = -1;
            int slotEnd = -1;
            boolean validBookDate = false;
            try {
                bookDate = LocalDate.parse(request.getParameter("bookDate"));
                slotStart = Integer.parseInt(request.getParameter("bookSlot_Start"));
                slotEnd = Integer.parseInt(request.getParameter("bookSlot_End"));
            } catch (Exception e) {
                e.printStackTrace();
            }

            int totalBookSlot = slotEnd == -1 ? 1 : (slotEnd - slotStart + 1);
            slotEnd = slotEnd <= -1 ? slotStart : slotEnd;
            try {
                //kiểm tra xem đã từng book những slot trong này chưa
                List<Booking> makerBook = bookingService.getByMakerId(userA.getUser().getUserId().toString());
                boolean bc = true;
                for (Booking b : makerBook) {
                    Schedule tmp = schPerGym.getScheduleById(b.getScheduleId().toString());
                    if (tmp != null) {
                        Slot t = schPerGym.getSlotBySlotId(tmp.getSlotId().toString());
                        for (long i = slotStart; i <= slotEnd; i++) {
                            if (t.getSlotOrder() == i && weekOfYearService.ConvertDateToLocalDate(tmp.getScheduleDate()).equals(bookDate)) {
                                bc = false;
                                throw new Exception("Book existed");
                            }
                        }
                    }
                }
                //kiểm tra xem đã từng book những slot trong này chưa
                //kiểm tra xem slot nhập có đúng với ngày hôm đấy không
                for (long i = slotStart; i <= slotEnd; i++) {
                    try {
                        Slot sl = schPerGym.getSlotByslotOrder(i + "");
                        Schedule s = schPerGym.getSchedulesBySlotId(sl.getSlotId(), bookDate);
                        if (s == null) {
                            throw new Exception();
                        }
                    } catch (Exception e) {
                        bc = false;
                        break;
                    }
                }
                //kiểm tra xem slot nhập có đúng với ngày hôm đấy không
                if (bookDate.compareTo(LocalDate.now()) >= 0 && bc) {
                    validBookDate = true;
                }
            } catch (Exception e) {
                check = false;
                e.printStackTrace();
            }
            if (totalBookSlot > 0 && validBookDate) {
                Bill bill = Bill.builder().totalMoney(schPerGym.getGym().getGymCost() * totalBookSlot).build();
                try {
                    billService.addNew(bill);
                    bill = billService.getLastBill();
                } catch (Exception e) {
                    check = false;
                    e.printStackTrace();
                }
                if (bill != null && check) {
                    for (long i = slotStart; i <= slotEnd; i++) {
                        long sId = schPerGym.getSlotByslotOrder(i + "").getSlotId();
                        Schedule s = schPerGym.getSchedulesBySlotId(sId, bookDate);
                        if (s != null) {
                            Booking b = Booking.builder()
                                    .createdDate(new Date())
                                    .makerId(userA.getUser().getUserId())
                                    .scheduleId(s.getScheduleId())
                                    .build();
                            try {
                                bookingService.addNew(b);
                            } catch (Exception e) {
                                check = false;
                                e.printStackTrace();
                            }
                        } else {
                            validBookDate = false;
                        }
                    }
                    List<Booking> newBookList = new ArrayList<>();
                    try {
                        newBookList = bookingService.getNewestBook(totalBookSlot + "");
                    } catch (Exception e) {
                        check = false;
                        e.printStackTrace();
                    }
                    for (Booking b : newBookList) {
                        Schedule temp = schPerGym.getScheduleById(b.getScheduleId().toString());
                        BillBooking billB = null;
                        if (temp != null) {
                            billB = BillBooking.builder()
                                    .billId(bill.getBillId())
                                    .bookingId(b.getBookingId())
                                    .price(schPerGym.getGym().getGymCost())
                                    .endDate(new Date(temp.getScheduleDate().getTime() - 2 * (3600 * 1000)))
                                    .build();
                        }
                        try {
                            billBookingService.addNew(billB);
                        } catch (Exception e) {
                            check = false;
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                validBookDate = false;
            }
            request.setAttribute("booked", check);
            request.setAttribute("validBookDate", validBookDate && check);
        }
        //end booking gym

        //view timetable
        schedulePerGymService = new SchedulePerGymService(gymService, scheduleService, slotService);
        long[][] timetable = schedulePerGymService.getGymTimetable(gymId.toString(), weekIndex);
        request.setAttribute("timetable", timetable);
        request.setAttribute("timeMap", weekOfYearService.TIME_SLOTS);
        request.setAttribute("curBookNum", schedulePerGymService.
                getCurrentBookUser(timetable));
        //end view timetable

        request.setAttribute("schedulePerGyms", schPerGym);
        request.setAttribute("formatter", new SimpleDateFormat("dd-MM"));
        request.setAttribute("bookdateformat", new SimpleDateFormat("yyyy-MM-dd"));
        request.getSession().setAttribute("gymIdValue", gymId);
        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
