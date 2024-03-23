/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.gym.controller;

import com.gym.DAO.CustomerScheduleDAO;
import com.gym.entity.CustomerSchedule;
import com.gym.entity.Slot;
import com.gym.services.coreServices.GymService;
import com.gym.services.coreServices.ScheduleService;
import com.gym.services.coreServices.SlotService;
import com.gym.services.customizeServices.SchedulePerGymService;
import com.gym.services.customizeServices.WeekOfYearService;
import com.gym.services.entityForServices.UserAuthorization;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;

import java.time.Year;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author ba Tung
 */
public class scheduleDetailController extends HttpServlet {

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
            out.println("<title>Servlet scheduleDetailController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet scheduleDetailController at " + request.getContextPath() + "</h1>");
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
        SlotService slot = new SlotService();
        UserAuthorization user = (UserAuthorization) request.getSession().getAttribute("userAuthorization");
        CustomerScheduleDAO dao = new CustomerScheduleDAO();
        GymService gymService = new GymService();
        SlotService slotService = new SlotService();
        ScheduleService scheduleService = new ScheduleService();
        SchedulePerGymService schedulePerGymService = new SchedulePerGymService(gymService, scheduleService, slotService);
        WeekOfYearService weekOfYearService = new WeekOfYearService();
        String uid = Long.toString(user.getUser().getUserId());
        try {
            List<Slot> list = slot.getAll();
            request.setAttribute("time", list);
        } catch (Exception e) {
        }
        
        
        List<CustomerSchedule> my = dao.TimeSchedule(uid);
        int year = Integer.parseInt(Year.now().toString()); //select year
        request.setAttribute("totalWeek", weekOfYearService.getNumOfWeekInYear(year));
        request.setAttribute("WeekMap", weekOfYearService.getAllWeek(year));//Display week
        int weekIndex = weekOfYearService.getCurrentWeekNumber();

        
        request.setAttribute("schedule", my);
        request.getRequestDispatcher("scheduleDetail.jsp").forward(request, response);
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

        Long makerId = 1L;
        try {
            makerId = Long.valueOf(request.getParameter("customerid"));
            request.setAttribute("makerId", makerId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        

        //choose week
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
        
        //view timetable
        CustomerSchedule[][] timetable = schedulePerGymService.getCustomerTimetable(makerId.toString(), weekIndex);
        request.setAttribute("timetable", timetable);
        request.setAttribute("timeMap", weekOfYearService.TIME_SLOTS);
     
        
        //end view timetable

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM");
        request.setAttribute("formatter", formatter);
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
