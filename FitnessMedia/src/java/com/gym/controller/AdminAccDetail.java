/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package com.gym.controller;

import com.gym.DAO.AccountDAO;
import com.gym.DAO.UserDAO;
import com.gym.entity.AccountList;
import com.gym.entity.User;
import com.gym.services.entityForServices.UserAuthorization;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author ba Tung
 */
public class AdminAccDetail extends HttpServlet {
   
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
            out.println("<title>Servlet AdminAccDetail</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminAccDetail at " + request.getContextPath () + "</h1>");
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
        String aid = request.getParameter("aid");
        UserAuthorization user = (UserAuthorization) request.getSession().getAttribute("userAuthorization");
        String pid = Long.toString(user.getUser().getUserId());
        try {
            int id = Integer.parseInt(aid);
            int idcheck = Integer.parseInt(pid);
            UserDAO dao = new UserDAO();
            AccountDAO a = new AccountDAO();
            User list = dao.getById(aid);
            AccountList rol = a.GetAccount(aid);
            request.setAttribute("user", list);
            request.setAttribute("account", rol);
            if (id < 0) {
                response.sendRedirect("ErrorPage");
            }else {
                request.getRequestDispatcher("AccountDetailAdmin.jsp").forward(request, response);
            }
        } catch (Exception e) {
            response.sendRedirect("ErrorPage");
        }
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
        String btnX = request.getParameter("btnX");
        if(btnX!=null){
            response.sendRedirect("AdminList");
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
