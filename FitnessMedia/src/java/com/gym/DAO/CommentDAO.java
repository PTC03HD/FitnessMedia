/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.DAO;

import com.gym.entity.Comment;
import com.gym.jdbc.connection.SQLServerConnection;
import com.gym.jdbc.core.RowMapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author admin
 */
public class CommentDAO implements RowMapper<Comment> {

    @Override
    public Comment mapRow(ResultSet rs) throws SQLException {
        Long longDate = 0L;
        try {
            longDate = Long.parseLong(rs.getString("created_date"));
        } catch (NumberFormatException e) {
        }
        Date createdDate = new Date(longDate);
        return Comment.builder()
                .commentId(rs.getLong("comment_id"))
                .userPostId(rs.getLong("user_post_id"))
                .gymPostId(rs.getLong("gym_post_id"))
                .makerId(rs.getLong("maker_id"))
                .commentContent(rs.getString("comment_content"))
                .commentImg(rs.getString("comment_img"))
                .createdDate(createdDate)
                .deleted(rs.getBoolean("deleted"))
                .build();
    }

    @Override
    public boolean addNew(Comment t) throws SQLException, ClassNotFoundException {
        String sql = """
                     insert into comment(
                     user_post_id,
                     gym_post_id,
                     maker_id,
                     comment_content,
                     comment_img,
                     created_date,
                     deleted) values(?,?,?,?,?,?,?)
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, t.getUserPostId());
            ps.setObject(2, t.getGymPostId());
            ps.setObject(3, t.getMakerId());
            ps.setObject(4, t.getCommentContent());
            ps.setObject(5, t.getCommentImg());
            ps.setObject(6, t.getCreatedDate().getTime());
            ps.setObject(7, t.isDeleted());
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public List<Comment> getAll() throws SQLException, ClassNotFoundException {
        List<Comment> list = new ArrayList<>();
        String sql = """
                     select * from comment
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Comment getById(String id) throws SQLException, ClassNotFoundException {
        String sql = """
                     select * from comment where commnent_id = ?
                     """;
        Comment comment = Comment.builder().commentId(0L).build();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                comment = mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return comment;
    }

    @Override
    public boolean updateById(String id, Comment t) throws SQLException, ClassNotFoundException {
        String sql = """
                     update comment
                     set
                     comment_content = ?,
                     comment_img = ?,
                     created_date=?,
                     deleted = ? where comment_id = ?
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, t.getCommentContent());
            ps.setObject(2, t.getCommentImg());
            ps.setObject(3, t.getCreatedDate().getTime());
            ps.setObject(4, t.isDeleted());
            ps.setObject(5, parseInt(id));
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public boolean deleteById(String id) throws SQLException, ClassNotFoundException {
        String sql = """
                     update comment
                     set deleted = 1
                     where comment_id = ?
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);){
            ps.setObject(1, parseInt(id));
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return check > 0;
    }

}
