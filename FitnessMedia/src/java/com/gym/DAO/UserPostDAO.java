/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.DAO;

import com.gym.entity.UserPost;
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
public class UserPostDAO implements RowMapper<UserPost>{

    @Override
    public UserPost mapRow(ResultSet rs) throws SQLException {
        Long longDate = 0L;
        try {
            longDate = Long.parseLong(rs.getString("created_date"));
        } catch (NumberFormatException e) {
        }
        Date createdDate = new Date(longDate);
        return UserPost.builder()
                .userPostId(rs.getLong("user_post_id"))
                .posterId(rs.getLong("poster_id"))
                .userPostImg(rs.getString("user_post_img"))
                .userPostStatus(rs.getString("user_post_status"))
                .createdDate(createdDate)
                .deleted(rs.getBoolean("deleted"))
                .build();
    }

    @Override
    public boolean addNew(UserPost t) throws SQLException, ClassNotFoundException {
        String sql = """
                     insert into user_post(
                     poster_id,
                     user_post_img,
                     user_post_status,
                     created_date,
                     deleted) values(?,?,?,?,?)
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);){
            ps.setObject(1, t.getPosterId());
            ps.setObject(2, t.getUserPostImg());
            ps.setObject(3, t.getUserPostStatus());
            ps.setObject(4, t.getCreatedDate().getTime());
            ps.setObject(5, t.isDeleted());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  check > 0;
    }

    @Override
    public List<UserPost> getAll() throws SQLException, ClassNotFoundException {
        List<UserPost> list = new ArrayList<>();
        String sql = """
                     select * from user_post
                     """;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);){
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
    public UserPost getById(String id) throws SQLException, ClassNotFoundException {
        String sql = """
                     select * from user_post where user_post_id = ?
                     """;
        UserPost userPost = UserPost.builder().userPostId(0L).build();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);){
            ps.setObject(1, parseInt(id));
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                userPost = mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return userPost;
    }

    @Override
    public boolean updateById(String id, UserPost t) throws SQLException, ClassNotFoundException {
        String sql = """
                     update user_post
                     set
                     user_post_img = ?,
                     user_post_status = ?,
                     deleted = ? where user_post_id = ?
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);){
            ps.setObject(1, t.getUserPostImg());
            ps.setObject(2, t.getUserPostStatus());
            ps.setObject(3, t.isDeleted());
            ps.setObject(1, t.getUserPostId());
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
                     delete from user_post where user_post_id = ?
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
