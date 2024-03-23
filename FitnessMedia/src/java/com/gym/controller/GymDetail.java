/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.gym.controller;

import com.gym.entity.*;
import com.gym.services.coreServices.BillService;
import com.gym.services.coreServices.BookingService;
import com.gym.services.coreServices.GymReviewService;
import com.gym.services.coreServices.GymService;
import com.gym.services.coreServices.ScheduleService;
import com.gym.services.coreServices.UserService;
import com.gym.services.customizeServices.*;
import com.gym.services.entityForServices.UserAuthorization;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author phamt
 */
public class GymDetail extends HttpServlet {

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
            out.println("<title>Servlet GymDetail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GymDetail at " + request.getContextPath() + "</h1>");
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
        UserService userService = new UserService();
        BookingService bookingService = new BookingService();
        ScheduleService scheduleService = new ScheduleService();
        GymReviewService gymReviewService = new GymReviewService();
        BillService billService = new BillService();
        ReviewPerGymService reviewPerGymService = new ReviewPerGymService(gymReviewService, gymService);
        BookingPerUserService bookingPerUserService = new BookingPerUserService(userService, bookingService, scheduleService, billService);

        Long gymId = 1L;
        try {
            gymId = (Long)request.getSession().getAttribute("gymIdValue");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //filter
        int order = 0;
        try {
            order = Integer.parseInt(request.getParameter("order"));
        } catch (Exception e) {
            e.printStackTrace();;
        }
        //filter end
        
        try {
            request.setAttribute("reviewPerGymList", reviewPerGymService.getAllByOrder(order));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            request.setAttribute("BookingMap", bookingPerUserService.getAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        request.getSession().setAttribute("gymIdValue", gymId);
        request.setAttribute("dateFormat", dateFormat);
        request.setAttribute("gymId", gymId);
        request.setAttribute("UserMap", userService.getUserMap());
        if(gymId == null) {
            response.sendRedirect("GymList");
        }
        else {
            request.getRequestDispatcher("gym_detail.jsp").forward(request, response);
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
        UserAuthorization userA = (UserAuthorization) request.getSession().getAttribute("userAuthorization");
        GymService gymService = new GymService();
        UserService userService = new UserService();
        BookingService bookingService = new BookingService();
        ScheduleService scheduleService = new ScheduleService();
        GymReviewService gymReviewService = new GymReviewService();
        BillService billService = new BillService();
        ReviewPerGymService reviewPerGymService = new ReviewPerGymService(gymReviewService, gymService);
        BookingPerUserService bookingPerUserService = new BookingPerUserService(userService, bookingService, scheduleService, billService);

        Long gymId = 1L;
        try {
            gymId = Long.parseLong(request.getParameter("gymId"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //write review
        boolean error = false;
        try {
            Date currentDate = new Date();
            Long vote = Long.valueOf(request.getParameter("rating"));
            String review = request.getParameter("review").trim();
            if(review.equals("")) {
                error = true;
                throw new Exception("Review content is left blank");
            }
            GymReview gymReview = GymReview.builder()
                    .makerId(userA.getUser().getUserId())
                    .gymId(gymId)
                    .reviewContent(review)
                    .vote(vote)
                    .createdDate(currentDate)
                    .hide(false)
                    .build();
            gymReviewService.addNew(gymReview);
            request.setAttribute("r", gymReview);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("error", error);
        //end write review

        //Delete review
        String deleteId = request.getParameter("deleteId");
        if (deleteId != null) {
            try {
                GymReview gymreview = gymReviewService.getById(deleteId);
                gymreview.setHide(true);
                boolean check = gymReviewService.updateById(deleteId, gymreview);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //end delete review

        //update review
//        UserAuthorization userAuthorization = (UserAuthorization) request.getSession().getAttribute("userAuthorization");
//        List<GymReview> list= new ArrayList<>();
//        try {
//            list=gymReviewService.getByGymId(request.getParameter("gymId"));
//        } catch (Exception e) {
//        }
//        for (GymReview gymReview : list) {
//            if(gymReview.getMakerId()==userAuthorization.getUser().getUserId()){
//                request.setAttribute("vote",gymReview.getVote());
//            }
//        }

        try {
            request.setAttribute("reviewPerGymList", reviewPerGymService.getAllByOrder(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            request.setAttribute("BookingMap", bookingPerUserService.getAll());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        request.setAttribute("dateFormat", dateFormat);
        request.setAttribute("gymId", gymId);
        request.getSession().setAttribute("gymIdValue", gymId);
        request.setAttribute("UserMap", userService.getUserMap());
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
