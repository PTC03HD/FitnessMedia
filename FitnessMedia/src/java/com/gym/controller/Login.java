/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.gym.controller;

import com.gym.entity.User;
import com.gym.services.customizeServices.UserAuthorizationService;
import com.gym.services.coreServices.UserService;
import com.gym.services.entityForServices.UserAuthorization;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
public class Login extends HttpServlet {

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
            out.println("<title>Servlet Login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath() + "</h1>");
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
        UserAuthorization userAuthorization = (UserAuthorization) request.getSession().getAttribute("userAuthorization");

        if (userAuthorization != null) {
            
            response.sendRedirect("home");
        } else {
            String wrong = (String) request.getAttribute("wrong");
            if (wrong == null) {
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            } else {
                if (wrong.equals("true")) {
                    request.setAttribute("notice", "Tài khoản hoặc mật khẩu sai");
                    request.getRequestDispatcher("Login.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("Login.jsp").forward(request, response);
                }
            }
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
        UserService userService = new UserService();
        UserAuthorizationService userAuthorizationService = new UserAuthorizationService();

        String user_name = request.getParameter("user_name");
        String user_password = request.getParameter("user_password");

        User user = User.builder().userId(0L).build();
        UserAuthorization userAuthorization = UserAuthorization.builder().build();

        try {
            user = userService.getActiveUserByUserNameAndPassword(user_name, user_password);

            if (!user.getUserId().equals(0L)) {
                userAuthorization = userAuthorizationService.getUserAuthorizationByUserId(user.getUserId() + "");
                request.getSession().setAttribute("userAuthorization", userAuthorization);
                doGet(request, response);
            } else {
                request.setAttribute("wrong", "true");
                doGet(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("wrong", "true");
            doGet(request, response);
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
