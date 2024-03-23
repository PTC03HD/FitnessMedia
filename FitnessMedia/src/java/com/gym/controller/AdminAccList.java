/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.gym.controller;

import com.gym.DAO.AccountDAO;
import com.gym.entity.AccountList;
import com.gym.entity.Role;
import com.gym.entity.TotalAccount;
import com.gym.services.coreServices.RoleService;
import com.gym.services.entityForServices.UserAuthorization;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author ba Tung
 */
public class AdminAccList extends HttpServlet {

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
            out.println("<title>Servlet AdminAccList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminAccList at " + request.getContextPath() + "</h1>");
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
        AccountDAO dao = new AccountDAO();
        Validate v = new Validate();
        RoleService role = new RoleService();
        try {
            List<Role> r = role.getRole();
            request.setAttribute("role", r);
        } catch (Exception e) {
        }
        
        //pagination
        int index = 1, size = 5;
        try {
            index = Integer.parseInt(request.getParameter("index"));
            size = Integer.parseInt(request.getParameter("size"));
        } catch (Exception e) {
        }

        List<AccountList> list = dao.getAccountPage(index, size);
        TotalAccount total = dao.TotalAccount();

        request.setAttribute("size", size);
        request.setAttribute("index", index);
        request.setAttribute("endP", dao.getTotalPage(size == 0 ? 5 : size, dao.getAllPagination()));
        request.setAttribute("acc", list);
        request.setAttribute("toa", total);
        try {
            if (index < 0 || size < 0) {
                response.sendRedirect("ErrorPage");
            } else if (size > total.getTotal()) {
                response.sendRedirect("ErrorPage");
            } else {
                request.getRequestDispatcher("AccountListAdmin.jsp").forward(request, response);
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
        AccountDAO dao = new AccountDAO();
        String txtSearch = request.getParameter("txtSearch");
        String btnSearch = request.getParameter("btnSearch");
        String btnFilter = request.getParameter("btnFilter");
        String role = request.getParameter("role");
        String status = request.getParameter("status");

        if (btnSearch != null) {
            List<AccountList> list = dao.SearchByUserName(txtSearch);
            request.setAttribute("acc", list);
        }
        if (btnFilter != null) {
            if (role != null) {
                List<AccountList> list = dao.FilterByRole(role);
                request.setAttribute("acc", list);
            }
            if (status != null) {
                List<AccountList> list = dao.FilterByStatus(status);
                request.setAttribute("acc", list);
            }

        }

        request.getRequestDispatcher("AccountListAdmin.jsp").forward(request, response);
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
