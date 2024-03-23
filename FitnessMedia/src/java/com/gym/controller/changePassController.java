/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.gym.controller;

import com.gym.DAO.UserDAO;
import com.gym.entity.User;
import com.gym.services.entityForServices.UserAuthorization;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author ba Tung
 */
public class changePassController extends HttpServlet {

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
        String uid = request.getParameter("uid");
        UserAuthorization user = (UserAuthorization) request.getSession().getAttribute("userAuthorization");
        String pid = Long.toString(user.getUser().getUserId());
        try {
            int id = Integer.parseInt(uid);
            int idcheck = Integer.parseInt(pid);
            UserDAO dao = new UserDAO();
            User list = dao.getById(uid);
            request.setAttribute("user", list);
            if (id < 0) {
                response.sendRedirect("ErrorPage");
            } else if (id != idcheck) {
                response.sendRedirect("ErrorPage");
            } else {
                request.getRequestDispatcher("changePassword.jsp").forward(request, response);
            }
        } catch (Exception e) {
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
        String btnChange = request.getParameter("btnChange");
        String oldPass = request.getParameter("oldPass");
        String newPass = request.getParameter("newPass");
        String conPass = request.getParameter("ConPass");
        UserAuthorization user = (UserAuthorization) request.getSession().getAttribute("userAuthorization");
        String pass = user.getUser().getUserPassword();
        String uid2 = request.getParameter("userid_pass");
        if (btnChange != null) {
            try {
                UserDAO dao = new UserDAO();
                User u = dao.getById(uid2);
                Validate v = new Validate();
                if (!oldPass.equals(pass)) {
                    request.setAttribute("mess4", "Password not true!");
                    request.getRequestDispatcher("changePassword.jsp").forward(request, response);
                } else {
                    if (!v.isValidPassword(newPass)) {
                        request.setAttribute("mess5", "Not validation!");
                        request.getRequestDispatcher("changePassword.jsp").forward(request, response);
                    } else {
                        if (!conPass.equals(newPass)) {      
                            request.setAttribute("mess6", "Not match with new Password");
                            request.getRequestDispatcher("changePassword.jsp").forward(request, response);
                        } else {
                            u.setUserPassword(newPass);
                        }
                    }
                }
                dao.updateById(uid2, u);
                response.sendRedirect("userprofile");
            } catch (Exception e) {
                e.printStackTrace();
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
