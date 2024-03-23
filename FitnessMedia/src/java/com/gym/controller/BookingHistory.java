/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.gym.controller;

import com.gym.entity.Bill;
import com.gym.services.coreServices.*;
import com.gym.services.customizeServices.BookingPerUserService;
import com.gym.services.entityForServices.UserAuthorization;
import jakarta.mail.Session;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 *
 * @author phamt
 */
public class BookingHistory extends HttpServlet {

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
            out.println("<title>Servlet BookingHistory</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BookingHistory at " + request.getContextPath() + "</h1>");
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
        BillService billService = new BillService();
        ScheduleService scheduleService = new ScheduleService();
        UserService userService = new UserService();
        GymService gymService = new GymService();
        BookingService bookingService = new BookingService();
        BookingPerUserService bookingPerUserService = new BookingPerUserService(userService, bookingService, scheduleService, billService);

        //chuyển đổi tiền tệ
        Locale locale = new Locale("vi", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        request.setAttribute("vndFormat", currencyFormatter);

        try {
            request.setAttribute("BookingMap", bookingPerUserService.getAll());
            request.setAttribute("GymMap", gymService.getGymMap());

        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("bookdateformat", new SimpleDateFormat("yyyy-MM-dd"));
        request.getRequestDispatcher("BookingHistory.jsp").forward(request, response);
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
        BillService billService = new BillService();
        String billId = request.getParameter("billId");
        if (billId != null) {
            try {
                Bill deleteBill = billService.getById(billId);
                deleteBill.setDeleted(true);
                billService.updateById(billId, deleteBill);
                billId = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

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
