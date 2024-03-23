/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.gym.controller;

import com.gym.entity.User;
import com.gym.services.constantServices.RandomService;
import com.gym.services.coreServices.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
public class ResetPassword extends HttpServlet {

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
            out.println("<title>Servlet ResetPassWord</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ResetPassWord at " + request.getContextPath() + "</h1>");
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
        String user_id = "";
        String active_code = "";

        user_id = request.getParameter("user_id");
        active_code = request.getParameter("active_code");

        if (user_id != null && active_code != null) {
            UserService userService = new UserService();
            User user = User.builder().userId(0L).build();

            try {
                user = userService.getById(user_id);
                if (!user.getUserId().equals(0L)) {
                    if (user.getActiveCode().equals(active_code)) {
                        RandomService randomService = new RandomService();
                        String new_active_code = randomService.getRandomCode(8);
                        user.setActiveCode(new_active_code);
                        userService.updateById(user.getUserId() + "", user);
                        request.setAttribute("user", user);
                        request.getRequestDispatcher("ResetPassword.jsp").forward(request, response);
                    } else {
                        response.sendRedirect("ErrorPage");
                    }
                } else {
                    response.sendRedirect("ErrorPage");
                }
            } catch (Exception e) {
                response.sendRedirect("ErrorPage");
            }
        } else {
            response.sendRedirect("ErrorPage");
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
        String user_password = "";
        String re_user_password = "";
        String user_id = "";
        user_password = request.getParameter("user_password");
        re_user_password = request.getParameter("re_user_password");
        user_id = request.getParameter("user_id");
        if (user_password != null || re_user_password != null || user_id != null) {
            if (user_password.equals(re_user_password)) {
                UserService userService = new UserService();
                User user = User.builder().userId(0L).build();

                try {
                    user = userService.getById(user_id);
                    if (!user.getUserId().equals(0L)) {
                        user.setUserPassword(user_password);
                        userService.updateById(user.getUserId() + "", user);
                        response.sendRedirect("Login");
                    } else {
                        response.sendRedirect("ErrorPage");
                    }
                } catch (Exception e) {
                }
            } else {
                response.sendRedirect("ErrorPage");
            }
        } else {
            response.sendRedirect("ErrorPage");
        }
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
