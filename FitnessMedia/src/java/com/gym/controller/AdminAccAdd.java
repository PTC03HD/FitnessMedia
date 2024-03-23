/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.gym.controller;

import com.gym.entity.Role;
import com.gym.entity.User;
import com.gym.entity.UserRole;
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
 * @author ba Tung
 */
public class AdminAccAdd extends HttpServlet {

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
            out.println("<title>Servlet AdminAccAdd</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminAccAdd at " + request.getContextPath() + "</h1>");
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
        String pid = Long.toString(user.getUser().getUserId());
        RoleService role = new RoleService();
        try {
            List<Role> r = role.getRole();
            request.setAttribute("role", r);
        } catch (Exception e) {
        }
        request.getRequestDispatcher("AccountAddAdmin.jsp").forward(request, response);

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
        Validate v = new Validate();
        UserService user = new UserService();
        String btnAdd = request.getParameter("btnAdd");
        String btnX = request.getParameter("btnX");
        String btnCancel = request.getParameter("btnCancel");
        String userName = request.getParameter("username");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String role = request.getParameter("role");
        String password = request.getParameter("password");
        boolean checkUserName = true;
        boolean checkUserEmail = true;
        boolean checkPerPhone = true;
        try {
            checkUserName = user.checkExistUserName(userName);
            checkUserEmail = user.checkExistUserEmail(email);
            checkPerPhone = user.checkExistPerPhone(phone);

        } catch (Exception e) {
        }
        if (btnX != null) {
            response.sendRedirect("AdminList");
        }
        if (btnCancel != null) {
            response.sendRedirect("AdminList");
        }
        if (btnAdd != null) {
            if (!v.isValidEmail(email) || !v.isValidPassword(password) || !v.isValidPhone(phone)) {
                if (!v.isValidEmail(email)) {
                    request.setAttribute("mess1", "Invalid Email");
                    
                }
                if (!v.isValidPassword(password)) {
                    request.setAttribute("mess2", "Invalid Password");
                    
                }
                if(!v.isValidPhone(phone)) {
                    request.setAttribute("MessPhone", "Invalid Phone");
                }
                doGet(request, response);
            } else {
                if (checkUserName || checkUserEmail || checkPerPhone) {
                    if (checkUserName) {
                        request.setAttribute("mess3", "Exist Username");
                    }
                    if (checkUserEmail) {
                        request.setAttribute("mess4", "Exist Email");
                    }
                    if (checkPerPhone) {
                        request.setAttribute("MessPhone2", "Exist Phone number");
                    }
                    
                    doGet(request, response);
                } else {
                    UserRoleService userRoleService = new UserRoleService();
                    RoleService roleService = new RoleService();
                    User newUser = User.builder()
                            .userName(userName)
                            .userPassword(password)
                            .userEmail(email)
                            .perPhone(phone)
                            .createdDate(new Date())
                            .deleted(false)
                            .build();

                    try {
                        user.addNew(newUser);
                        newUser = user.getUserByUserName(userName);
                        List<Role> listRoles = new ArrayList<>();
                        listRoles = roleService.getAll();
                        for (Role roe : listRoles) {
                            if (roe.getRoleName().equals(role)) {
                                UserRole userole = UserRole.builder()
                                        .userId(newUser.getUserId())
                                        .roleId(roe.getRoleId())
                                        .build();
                                userRoleService.addNew(userole);
                                break;
                            }
                        }
                    } catch (Exception e) {
                        request.setAttribute("Error", "Error");
                        doGet(request, response);
                    }
                    response.sendRedirect("AdminList");
                }
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
