/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.gym.controller;

import com.gym.entity.Emotion;
import com.gym.services.coreServices.EmotionService;
import com.gym.services.coreServices.UserPostService;
import com.gym.services.entityForServices.UserAuthorization;
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
public class LikeUserPost extends HttpServlet {

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
            out.println("<title>Servlet LikeUserPost</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LikeUserPost at " + request.getContextPath() + "</h1>");
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
        String userPostId = request.getParameter("userPostId").trim();
        String scrollX = request.getParameter("scrollX");
        String scrollY = request.getParameter("scrollY");
        if (scrollX == null || scrollY == null || scrollX.equals("") || scrollY.equals("")) {
            scrollX = "0";
            scrollY = "0";
        }
        if (userAuthorization == null) {
            response.sendRedirect("CustomUserPostPage?userPostId=" + userPostId);
        } else {

            String userMakerId = request.getParameter("userMakerId").trim();
            String makeLike = request.getParameter("makeLike").trim();
            if (userMakerId == null || userMakerId == null || makeLike == null || userPostId.equals("") || userMakerId.equals("") || makeLike.equals("")) {
                response.sendRedirect("ErrorPage");
            } else {
                UserPostService userPostService = new UserPostService();
                EmotionService emotionService = new EmotionService();

                Emotion emotion = Emotion.builder().build();
                try {
                    emotion = emotionService.getEmotionByUserPostIdAndMakerId(userPostId, userMakerId);
                    if (!makeLike.equals("true") && !makeLike.equals("false")) {
                        response.sendRedirect("ErrorPage");
                    } else {
                        if (emotion.getEmotionId().equals(0L)) {
                            emotion = Emotion.builder()
                                    .userPostId(emotionService.parseInt(userPostId) + 0L)
                                    .makerId(emotionService.parseInt(userMakerId) + 0L)
                                    .isDislike(makeLike.equals("false"))
                                    .gymPostId(null)
                                    .deleted(false)
                                    .build();
                            emotionService.addNew(emotion);
                            response.sendRedirect("CustomUserPostPage?userPostId=" + userPostId+"&scrollY="+scrollY+"&scrollX="+scrollX);
                        } else {
                            if (!makeLike.equals(emotion.isDislike() + "")) {
                                emotion.setDeleted(!emotion.isDeleted());
                            } else {
                                emotion.setDislike(!emotion.isDislike());
                                emotion.setDeleted(false);
                            }
                            emotionService.updateById(emotion.getEmotionId() + "", emotion);
                            response.sendRedirect("CustomUserPostPage?userPostId=" + userPostId+"&scrollY="+scrollY+"&scrollX="+scrollX);
                        }
                    }

                } catch (Exception e) {
                    response.sendRedirect("ErrorPage");
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
        processRequest(request, response);
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
