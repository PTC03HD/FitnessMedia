/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package com.gym.controller;

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
import java.util.Date;
import java.util.List;

/**
 *
 * @author dodat
 */
public class CUDGymPost extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet CUGymPost</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CUGymPost at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        UserAuthorization userAuthorization = (UserAuthorization) request.getSession().getAttribute("userAuthorization");
        GymService gymservice=new GymService();
        Gym gym = Gym.builder().build();
        try {
        gym = gymservice.getGymByManagerId(userAuthorization.getUser().getUserId().toString());
        } catch (Exception e) {
        }
        request.setAttribute("gym",gym);
//        delete gympost
        String gympostid=request.getParameter("gympostid");
        if(gympostid!=null){
            GymPostService gympostservice=new GymPostService();
            try {
                gympostservice.deleteActive(gympostid);
                request.setAttribute("mess", "delete success");
            } catch (Exception e) {
                request.setAttribute("mess", "delete failed");
            }
            
        }
        request.getRequestDispatcher("/AddGympost.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        //load addgympost.jsp
        String title= request.getParameter("title");
        String description= request.getParameter("description");
        String image= request.getParameter("image");
        String gymid=request.getParameter("gymid");
        String gympostid=request.getParameter("gympostid");
        GymPost gp=new GymPost();
        GymPostService gympostservice=new GymPostService();
        //lay gym
        UserAuthorization userAuthorization = (UserAuthorization) request.getSession().getAttribute("userAuthorization");
        GymService gymservice=new GymService();
        Gym gym= Gym.builder().build();
        try {
        gym = gymservice.getGymByManagerId(userAuthorization.getUser().getUserId().toString());
        } catch (Exception e) {
        }
       
        request.setAttribute("gym",gym);
        request.setAttribute("postid",gympostid);
        if(request.getParameter("update")!=null){
            try {
            gp=gympostservice.getById(gympostid);
            } catch (Exception e) {
            }
            request.setAttribute("gympost_before",gp);
            request.getRequestDispatcher("/AddGympost.jsp").forward(request, response);
        }
        //add gympost
        if(gympostid==null||gympostid.equals("")){
        gp.setCreatedDate(new Date());
        gp.setGymId(Long.valueOf(gymid));
        gp.setGymPostImg(image);
        gp.setGymPostStatus(description);
        gp.setGymPostTile(title);
        gp.setDeleted(false);
        try {
        gympostservice.addNew(gp);
        request.setAttribute("mess", "add successfull!");
        } catch (Exception e) {
        request.setAttribute("mess", "add failed!");    
        }
        request.getRequestDispatcher("/AddGympost.jsp").forward(request, response);
        }else{
        //update gympost
        gp.setCreatedDate(new Date());
        gp.setGymId(Long.valueOf(gymid));
        gp.setGymPostImg(image);
        gp.setGymPostStatus(description);
        gp.setGymPostTile(title);
        gp.setDeleted(false);
        gp.setGymPostId(Long.valueOf(gympostid));
            try {
                gympostservice.updateById(gympostid, gp);
                request.setAttribute("mess", "update successfull!");
            } catch (Exception e) {
                request.setAttribute("mess", "update failed!");    
            }
        request.getRequestDispatcher("/AddGympost.jsp").forward(request, response);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
