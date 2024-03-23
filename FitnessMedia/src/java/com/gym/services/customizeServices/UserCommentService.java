/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.services.customizeServices;

import com.gym.entity.Comment;
import com.gym.entity.User;
import com.gym.services.coreServices.CommentService;
import com.gym.services.coreServices.UserService;
import com.gym.services.entityForServices.UserComment;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dodat
 */
public class UserCommentService {

    private UserService userService;
    private CommentService commentService;

    public UserCommentService() {
        this.commentService = new CommentService();
        this.userService = new UserService();
    }

    public List<UserComment> getUserCommentList(String gympostid) throws SQLException, ClassNotFoundException {
        List<UserComment> usecommentlist = new ArrayList<>();
        List<Comment> commentlist = new ArrayList<>();
        try {
            commentlist = commentService.getCommentByGympostid(gympostid);
        } catch (Exception e) {
        }
        

        for (Comment comment : commentlist) {
            UserComment usercomment = new UserComment();
            usercomment.setComment(comment);
            User user = null;
            try {
                user = userService.getUserByUserId(comment.getMakerId());
            } catch (Exception e) {
            }
            usercomment.setUser(user);
            
            usecommentlist.add(usercomment);
        }
        
        return usecommentlist;
    }
}
