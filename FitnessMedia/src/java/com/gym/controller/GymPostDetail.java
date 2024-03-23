package com.gym.controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import com.gym.entity.Comment;
import com.gym.entity.Gym;
import com.gym.entity.GymPost;
import com.gym.services.coreServices.CommentService;
import com.gym.services.coreServices.GymPostService;
import com.gym.services.coreServices.GymService;
import com.gym.services.customizeServices.UserAuthorizationService;
import com.gym.services.customizeServices.UserCommentService;
import com.gym.services.entityForServices.UserAuthorization;
import com.gym.services.entityForServices.UserComment;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author dodat
 */
public class GymPostDetail extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        response.setContentType("text/html;charset=UTF-8");
        UserAuthorization userAuthorization = null;
        try {
            userAuthorization = (UserAuthorization) request.getSession().getAttribute("userAuthorization");
        } catch (Exception e) {

        }
        //edit button update and delete
        GymService gymservice=new GymService();
        Gym gymforbutton=Gym.builder().build();
        try {
         gymforbutton= gymservice.getGymByManagerId(userAuthorization.getUser().getUserId().toString());
        } catch (Exception e) {
        }
        request.setAttribute("gymforbutton", gymforbutton.getGymId());
        //end edit button update and delete
        if (userAuthorization == null) {
            request.setAttribute("user", null);
        } else {
            request.setAttribute("user", userAuthorization.getUser());
        }
        GymPostService gympostservice = new GymPostService();
        List<GymPost> recentnewslist = new ArrayList<>();
        List<Gym> gymlist = new ArrayList<>();
        GymPost gym = null;
        try {
            recentnewslist = gympostservice.getRecentNews();

        } catch (Exception e) {
        }
        request.setAttribute("recentnewslist", recentnewslist);
        //lay gympost
        String gympostid = request.getParameter("id");
        try {
            gym = gympostservice.getById(gympostid);
        } catch (Exception e) {
        }
        request.setAttribute("gympost", gym);
        //lay gymlist
        
        try {
            gymlist = gymservice.getAll();
        } catch (Exception e) {
        }
        request.setAttribute("gymlist", gymlist);
        //delete comment
        String commentid=null;
        try {
        commentid=request.getParameter("commentid");
        } catch (Exception e) {
        }
        if(commentid!=null){
            CommentService commentservice = new CommentService();
            try {
            commentservice.deleteById(commentid);
            request.setAttribute("message", " delete success");
            } catch (Exception e) {
                request.setAttribute("message", " delete failed");
            }
        }
        
        //Comment of gympost
        List<UserComment> usercommentlist = new ArrayList<>();
        try {
            UserCommentService usercommentservice = new UserCommentService();
            usercommentlist = usercommentservice.getUserCommentList(gympostid);
        } catch (Exception e) {
        }
        request.setAttribute("gympostid", gympostid);
        request.setAttribute("usercommentlist", usercommentlist);
        request.setAttribute("size", usercommentlist.size());
        request.getRequestDispatcher("/blogdetails.jsp").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        GymPostService gympostservice = new GymPostService();
        List<GymPost> recentnewslist = new ArrayList<>();
        List<Gym> gymlist = new ArrayList<>();
        GymPost gym = null;
        try {
            recentnewslist = gympostservice.getRecentNews();

        } catch (Exception e) {
        }
        request.setAttribute("recentnewslist", recentnewslist);
        //lay gympost
        String gympostid = request.getParameter("id");
        try {
            gym = gympostservice.getById(gympostid);
        } catch (Exception e) {
        }
        request.setAttribute("gympost", gym);
        //lay gymlist
        GymService gymservice = new GymService();
        try {
            gymlist = gymservice.getAll();
        } catch (Exception e) {
        }
        request.setAttribute("gymlist", gymlist);
        //edit button update and delete
         UserAuthorization userAuthorization = (UserAuthorization) request.getSession().getAttribute("userAuthorization");
        Gym gymforbutton=Gym.builder().build();
        try {
         gymforbutton= gymservice.getGymByManagerId(userAuthorization.getUser().getUserId().toString());
        } catch (Exception e) {
        }
        request.setAttribute("gymforbutton", gymforbutton.getGymId());
        //end edit button update and delete
        if (userAuthorization == null) {
            request.setAttribute("user", null);
        } else {
            request.setAttribute("user", userAuthorization.getUser());
        }
        //Add and update new Comment

        
        if (userAuthorization == null) {
            request.getRequestDispatcher("/Login.jsp").forward(request, response);
        } else {
            String commentid = null;
            try {
                 commentid = request.getParameter("commentid");
            } catch (Exception e) {
            }

            if (commentid == null||commentid.equals("")) {
                //add comment
                Comment c = new Comment();
                c.setCommentContent(request.getParameter("content"));
                c.setCommentImg("");
                c.setCreatedDate(new Date());
                c.setUserPostId(null);
                c.setDeleted(false);
                c.setGymPostId(Long.valueOf(gympostid));
                c.setMakerId(userAuthorization.getUser().getUserId());
                CommentService commentservice = new CommentService();
                try {
                    commentservice.addNew(c);
                    request.setAttribute("message", " add success");
                } catch (Exception e) {
                    request.setAttribute("message", "add failed");
                }
            } else {
                //update comment
                
                Comment c = new Comment();
                c.setCommentId(Long.valueOf(commentid));
                c.setCommentContent(request.getParameter("content"));
                c.setCommentImg("");
                c.setCreatedDate(new Date());
                c.setUserPostId(null);
                c.setDeleted(false);
                c.setGymPostId(Long.valueOf(gympostid));
                c.setMakerId(userAuthorization.getUser().getUserId());
                CommentService commentservice = new CommentService();
                try {
                    commentservice.updateById(commentid, c);
                    request.setAttribute("message", " update success");
                } catch (Exception e) {
                    request.setAttribute("message", "update failed");
                }
            }

            //delete comment
            //Comment of gympost
            List<UserComment> usercommentlist = new ArrayList<>();
            try {
                UserCommentService usercommentservice = new UserCommentService();
                usercommentlist = usercommentservice.getUserCommentList(gympostid);
            } catch (Exception e) {
            }
            request.setAttribute("usercommentlist", usercommentlist);
            request.setAttribute("size", usercommentlist.size());

            request.getRequestDispatcher("/blogdetails.jsp").forward(request, response);
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
