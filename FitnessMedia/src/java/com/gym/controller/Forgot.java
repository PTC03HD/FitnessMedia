/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.gym.controller;

import com.gym.entity.User;
import com.gym.services.constantServices.MailService;
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
public class Forgot extends HttpServlet {

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
            out.println("<title>Servlet Forgot</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Forgot at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("Forgot.jsp").forward(request, response);
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
        String user_name = "";
        String user_email = "";
        String per_phone = "";

        user_name = request.getParameter("user_name");
        user_email = request.getParameter("user_email");
        per_phone = request.getParameter("per_phone");
        
        request.setAttribute("user_name", user_name);
        request.setAttribute("user_email", user_email);
        request.setAttribute("per_phone", per_phone);

        UserService userService = new UserService();
        try {
            User user = User.builder().userId(0L).build();
            user = userService.getUserByUserName(user_name);
            if (!user.getUserId().equals(0L)) {
                if (!user.isDeleted()) {
                    if (user.getUserEmail().equals(user_email)) {
                        if (user.getPerPhone().equals(per_phone)) {
                            MailService mailService = new MailService();
                            String url = "http://localhost:9999/FitnessMedia/ResetPassword?" + "user_id=" + user.getUserId() + "&" + "active_code=" + user.getActiveCode();
                            mailService.sentEmail(user.getUserEmail(), "Email xác nhận thay đổi mật khẩu", "<a href='" + url + "'>Nhấp vào đây để xác minh</a>");
                            response.sendRedirect("Login");
                        } else {
                            request.setAttribute("error_per_phone", "Số điện thoại không chính xác");
                            request.getRequestDispatcher("Forgot.jsp").forward(request, response);
                        }
                    } else {
                        request.setAttribute("error_user_email", "Email không chính xác");
                        request.getRequestDispatcher("Forgot.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("error_user_name", "Tài khoản đã bị xóa hoặc bị đình chỉ hoạt động");
                    request.getRequestDispatcher("Forgot.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("error_user_name", "Tài khoản không tồn tại");
                request.getRequestDispatcher("Forgot.jsp").forward(request, response);
            }

        } catch (Exception e) {
            request.setAttribute("error_system", "Hệ thông gặp sự cố vui lòng thử lại sau");
            request.getRequestDispatcher("Forgot.jsp").forward(request, response);
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
