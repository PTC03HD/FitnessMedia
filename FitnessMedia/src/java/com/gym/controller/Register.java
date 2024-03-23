/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.gym.controller;

import com.gym.entity.Role;
import com.gym.entity.User;
import com.gym.entity.UserRole;
import com.gym.services.constantServices.MailService;
import com.gym.services.coreServices.RoleService;
import com.gym.services.coreServices.UserRoleService;
import com.gym.services.coreServices.UserService;
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
 * @author admin
 */
public class Register extends HttpServlet {

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
            out.println("<title>Servlet Register</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Register hehe " + request.getContextPath() + "</h1>");
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
            request.getRequestDispatcher("Register.jsp").forward(request, response);
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
        String user_name = request.getParameter("user_name");
        String user_password = request.getParameter("user_password");
        String user_email = request.getParameter("user_email");
        String per_phone = request.getParameter("per_phone");

        request.setAttribute("user_name", user_name);
        request.setAttribute("user_password", user_password);
        request.setAttribute("user_email", user_email);
        request.setAttribute("per_phone", per_phone);

        UserService userService = new UserService();
        MailService mailService = new MailService();

        boolean checkUserName = true;
        boolean checkUserEmail = true;
        boolean isRealEmail = false;
        boolean checkPerPhone = true;

        try {
            checkUserName = userService.checkExistUserName(user_name);
            checkUserEmail = userService.checkExistUserEmail(user_email);
            checkPerPhone = userService.checkExistPerPhone(per_phone);
            isRealEmail = mailService.isRealMail(user_email);
        } catch (Exception e) {
        }

        if (checkUserName || checkUserEmail || (!isRealEmail) || checkPerPhone) {
            if (checkUserName) {
                request.setAttribute("error_user_name", "Tên tài khoản đã tồn tại");
            }
            if (checkUserEmail) {
                request.setAttribute("error_user_email", "Email đã được sử dụng");
            }
            if (!isRealEmail) {
                request.setAttribute("error_user_email", "Email không tồn tại hoặc không được cấp quyền");
            }
            if (checkPerPhone) {
                request.setAttribute("error_per_phone", "Số điện thoại đã được sử dụng");
            }
            request.getRequestDispatcher("Register.jsp").forward(request, response);
        } else {
            UserRoleService userRoleService = new UserRoleService();
            RoleService roleService = new RoleService();
            User newUser = User.builder()
                    .userName(user_name)
                    .userPassword(user_password)
                    .userEmail(user_email)
                    .perPhone(per_phone)
                    .createdDate(new Date())
                    .deleted(true)
                    .build();
            try {
                userService.addNew(newUser);
                newUser = userService.getUserByUserName(user_name);
                List<Role> listRoles = new ArrayList<>();
                listRoles = roleService.getAll();
                for (Role role : listRoles) {
                    if (role.getRoleName().equals("ROLE_CUSTOMER")) {
                        UserRole userRole = UserRole.builder()
                                .userId(newUser.getUserId())
                                .roleId(role.getRoleId())
                                .build();
                        userRoleService.addNew(userRole);
                        break;
                    }
                }
                String url = "http://localhost:9999/FitnessMedia/VerifyAccount?"+"user_id="+newUser.getUserId()+"&created_date="+newUser.getCreatedDate().getTime();
                mailService.sentEmail(user_email, "Xác nhận tạo tài khoản", "<a href='"+url+"'>Nhấp vào đây để xác minh</a>");
            } catch (Exception e) {
                request.setAttribute("error_system", "Lỗi hệ thống vui lòng đăng kí lại!!!");
                request.getRequestDispatcher("Register.jsp").forward(request, response);
            }
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
