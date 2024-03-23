/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.gym.controller;

import com.gym.entity.User;
import com.gym.entity.UserRole;
import com.gym.services.constantServices.RandomService;
import com.gym.services.coreServices.UserRoleService;
import com.gym.services.coreServices.UserService;
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
 * @author admin
 */
public class VerifyAccount extends HttpServlet {

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
            out.println("<title>Servlet VerifyAccount</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VerifyAccount at " + request.getContextPath() + "</h1>");
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
        String user_id = request.getParameter("user_id");
        String created_date = request.getParameter("created_date");
        if (user_id == null || created_date == null) {
            request.getRequestDispatcher("ErrorPage.jsp").forward(request, response);
        } else {
            UserService userService = new UserService();
            User user = User.builder().userId(0L).build();
            try {
                user = userService.getById(user_id);
                if (!user.getUserId().equals(0L)) {
                    if (user.isDeleted()) {
                        if (user.getActiveCode() != null) {
                            request.setAttribute("Error", "Tài khoản của bạn bị cấm hoạt động");
                            request.getRequestDispatcher("ErrorPage.jsp").forward(request, response);
                        } else {
                            RandomService randomService = new RandomService();
                            String active_code = randomService.getRandomCode(8);
                            boolean isTrueCreatedTime = false;

                            isTrueCreatedTime = created_date.equals(user.getCreatedDate().getTime() + "");
                            if (isTrueCreatedTime) {
                                Date currentTime = new Date();
                                if (currentTime.getTime() - user.getCreatedDate().getTime() <= 5 * 60 * 1000) {
                                    user.setActiveCode(active_code);
                                    user.setDeleted(false);
                                    userService.updateById(user_id, user);
                                    request.setAttribute("notice", "Tao tài khoản thành công");
                                    request.getRequestDispatcher("Login.jsp").forward(request, response);
                                } else {
                                    UserRoleService userRoleService = new UserRoleService();

                                    List<UserRole> listUserRoles = new ArrayList<>();
                                    listUserRoles = userRoleService.getListUserRolesByUserId(user_id);

                                    for (UserRole userRole : listUserRoles) {
                                        userRoleService.deleteById(userRole.getUserRoleId() + "");
                                    }

                                    userService.deleteById(user_id);
                                    request.setAttribute("Error", "Đường dẫn hết hạn vui lòng đăng kí lại");
                                    request.getRequestDispatcher("ErrorPage.jsp").forward(request, response);
                                }
                            } else {
                                request.getRequestDispatcher("ErrorPage.jsp").forward(request, response);
                            }
                        }
                    } else {
                        request.getRequestDispatcher("ErrorPage.jsp").forward(request, response);
                    }
                } else {
                    request.getRequestDispatcher("ErrorPage.jsp").forward(request, response);
                }
            } catch (Exception e) {
                request.getRequestDispatcher("ErrorPage.jsp").forward(request, response);
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
