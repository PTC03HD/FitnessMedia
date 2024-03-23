/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.gym.controller;

import com.gym.entity.Gym;
import com.gym.services.coreServices.*;
import com.gym.services.customizeServices.ReviewPerGymService;
import com.gym.services.entityForServices.ReviewPerGym;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author phamt
 */
public class GymList extends HttpServlet {

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
            out.println("<title>Servlet GymList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GymList at " + request.getContextPath() + "</h1>");
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
        GymService gymService = new GymService();
        GymReviewService gymReviewService = new GymReviewService();
        ReviewPerGymService reviewPerGymService = new ReviewPerGymService(gymReviewService, gymService);
        //chuyển đổi tiền tệ
        Locale locale = new Locale("vi", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        request.setAttribute("vndFormat", currencyFormatter);

        //pagination
        int index = 1, size = 3;
        try {
            index = Integer.parseInt(request.getParameter("index"));
            size = Integer.parseInt(request.getParameter("size"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //search
        String keyword = request.getParameter("keyword");
        keyword = keyword==null?"":keyword.trim();
        int order = Integer.MAX_VALUE;
        try {
            order = Integer.parseInt(request.getParameter("order"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        List<Gym> gyms = new ArrayList<>();
        try {
            gyms = gymService.getAll(index, size,keyword, order);
            request.setAttribute("gyms", gyms);
            request.setAttribute("reviewPerGymList", reviewPerGymService.getAllByOrder(5));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
//        gyms.get(1).getDescription().length()
        
        request.setAttribute("size", size);
        request.setAttribute("index", index);
        request.setAttribute("endP", gymService.getTotalPage(size == 0 ? 3 : size, gymService.getAllPagination(keyword, order)));
        request.getRequestDispatcher("gym_list.jsp").forward(request, response);
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
