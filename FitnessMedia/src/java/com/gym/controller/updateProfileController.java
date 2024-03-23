/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.gym.controller;

import com.gym.DAO.UserDAO;
import com.gym.entity.User;
import com.gym.services.coreServices.UserService;
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
public class updateProfileController extends HttpServlet {

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
            out.println("<title>Servlet updateProfileController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet updateProfileController at " + request.getContextPath() + "</h1>");
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
                request.getRequestDispatcher("updateProfile.jsp").forward(request, response);
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
        String btnEdit = request.getParameter("btnEdit");
        String btnCancel = request.getParameter("btnCancel");
        String email = request.getParameter("usermail");
        String phone = request.getParameter("phone");
        String idcitizen = request.getParameter("idcitizen");
        String fname = request.getParameter("fullname");
        String img = request.getParameter("image");
        String uid1 = request.getParameter("userid_edit");
        String confirm = request.getParameter("confirm");
        UserAuthorization user = (UserAuthorization) request.getSession().getAttribute("userAuthorization");
        String pass = user.getUser().getUserPassword();
        if (btnEdit != null) {
            try {
                UserService dao = new UserService();
                User u = dao.getById(uid1);
                request.setAttribute("user", u);
                //validate
                Validate v = new Validate();
                if(!confirm.equals(pass)){
                    request.setAttribute("messpass", "Password not true!");
                    request.getRequestDispatcher("updateProfile.jsp").forward(request, response);
                }else {
                    if (v.isValidEmail(email)) {
                    u.setUserEmail(email);
                } else {
                    request.setAttribute("mess1", "Invalid Email");
                    request.getRequestDispatcher("updateProfile.jsp").forward(request, response);
                }
                if (v.isValidPhone(phone)) {
                    u.setPerPhone(phone);
                } else {
                    request.setAttribute("mess2", "Invalid NumberPhone");
                    request.getRequestDispatcher("updateProfile.jsp").forward(request, response);
                }
                if (v.isValidId(idcitizen)) {
                    u.setIdCitizen(idcitizen);
                } else {
                    request.setAttribute("mess3", "Invalid Id Citizen");
                    request.getRequestDispatcher("updateProfile.jsp").forward(request, response);
                }
                }
                

                //set data                                             
                u.setImgWall(fname);
                u.setImgAvatar("img/"+img);
                dao.updateWithId(uid1, u);
                response.sendRedirect("userprofile");
            } catch (Exception e) {
            }
        }
        if (btnCancel != null) {
            response.sendRedirect("userprofile");
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
