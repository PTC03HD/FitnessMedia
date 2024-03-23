/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.gym.controller;

import com.gym.DAO.GymPostDAO;
import com.gym.entity.Gym;
import com.gym.entity.GymPost;
import com.gym.services.coreServices.GymPostService;
import com.gym.services.coreServices.GymService;
import com.gym.services.entityForServices.UserAuthorization;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dodat
 */
public class GymPostList extends HttpServlet {

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
            out.println("<title>Servlet GymPostList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GymPostList at " + request.getContextPath() + "</h1>");
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
        
        response.setContentType("text/html;charset=UTF-8");
        GymPostService gympostservice = new GymPostService();
        List<GymPost> recentnewslist = new ArrayList<>();
        List<GymPost> gympostlist = new ArrayList<>();
        List<Gym> gymlist = new ArrayList<>();
        int row = 0;
        try {
            recentnewslist = gympostservice.getRecentNews();
            row = gympostservice.getAllActive().size();
        } catch (Exception e) {
        }
        request.setAttribute("recentnewslist", recentnewslist);
        String index_raw = request.getParameter("index");
        int index = Integer.parseInt(index_raw);
        int pageSize = 3;
        int endPage=row/pageSize;
        if(row%pageSize!=0)endPage++;
        GymService gymservice=new GymService();
        try {
            gympostlist=gympostservice.getGympostfromto(pageSize, index);
            gymlist=gymservice.getAll();
            
        } catch (Exception e) {
        }
        //edit button update and delete
        UserAuthorization userAuthorization = (UserAuthorization) request.getSession().getAttribute("userAuthorization");
        Gym gym=Gym.builder().build();
        try {
         gym= gymservice.getGymByManagerId(userAuthorization.getUser().getUserId().toString());
        } catch (Exception e) {
        }
        request.setAttribute("gymid", gym.getGymId());
        request.setAttribute("gymlist",gymlist);
        request.setAttribute("gympostlist", gympostlist);
        request.setAttribute("endPage",endPage);
        request.setAttribute("index_save", index);
        request.getRequestDispatcher("/bloglist.jsp").forward(request, response);
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
