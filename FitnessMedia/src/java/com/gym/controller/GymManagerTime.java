/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.gym.controller;

import com.gym.entity.Gym;
import com.gym.entity.Slot;
import com.gym.services.coreServices.GymReviewService;
import com.gym.services.coreServices.GymService;
import com.gym.services.coreServices.SlotService;
import com.gym.services.entityForServices.TotalReview;
import com.gym.services.entityForServices.TotalVote;
import com.gym.services.entityForServices.UserAuthorization;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ba Tung
 */
public class GymManagerTime extends HttpServlet {

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
            out.println("<title>Servlet GymManagerTime</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GymManagerTime at " + request.getContextPath() + "</h1>");
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
        UserAuthorization user = (UserAuthorization) request.getSession().getAttribute("userAuthorization");
        String managerId = Long.toString(user.getUser().getUserId());
        GymService gym = new GymService();
        SlotService slot = new SlotService();
        GymReviewService total = new GymReviewService();
        String btnSave = request.getParameter("btnSave");

        try {
            Gym myGym = gym.getGymByManagerId(managerId);
            Slot mySlotStart = slot.getSlot("6");
            Slot mySlotEnd = slot.getSlot("24");

            if (btnSave != null) {
                String status = request.getParameter("status");
                Long open = Long.parseLong(request.getParameter("open"));
                Long closed = Long.parseLong(request.getParameter("closed"));
                if (status.equals("Openning")) {
                    request.setAttribute("mess", "The gym is openning, can't not change time");
                } else {
                    mySlotStart.setStartTime(open);
                    mySlotEnd.setEndTime(closed);
                    slot.updateById("6", mySlotStart);
                    slot.updateById("24", mySlotEnd);
                }

            }
            TotalReview r = total.TotalReview();
            request.setAttribute("gym", myGym);
            request.setAttribute("Open", mySlotStart);
            request.setAttribute("Close", mySlotEnd);
            request.setAttribute("All", r);
        } catch (Exception e) {
        }

        List<TotalVote> list = total.TotalVote();
        int count = 0;
        int star = 0;
        for (TotalVote totalVote : list) {
            star += totalVote.getTotal();
            count++;
        }
        double avg = (double) star / count;

        Date currentDate = new Date();
        // Format the time to display only the hour
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH");
        String formattedTime = timeFormat.format(currentDate);
        request.setAttribute("Vote", Double.toString(avg));
        request.setAttribute("count", Integer.toString(count));
        request.setAttribute("currentDate", formattedTime);
        request.getRequestDispatcher("GymManager.jsp").forward(request, response);

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
