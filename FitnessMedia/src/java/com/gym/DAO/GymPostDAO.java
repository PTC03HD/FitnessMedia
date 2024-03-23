/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.DAO;

import com.gym.entity.GymPost;
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
public class GymPostDAO implements RowMapper<GymPost>{

    @Override
    public GymPost mapRow(ResultSet rs) throws SQLException {
        Long longDate = 0L;
        try {
            longDate = Long.parseLong(rs.getString("created_date"));
        } catch (NumberFormatException e) {
        }
        Date createdDate = new Date(longDate);
        return GymPost.builder()
                .gymPostId(rs.getLong("gym_post_id"))
                .gymId(rs.getLong("gym_id"))
                .gymPostTile(rs.getString("gym_post_tile"))
                .gymPostStatus(rs.getString("gym_post_status"))
                .gymPostImg(rs.getString("gym_post_img"))
                .createdDate(createdDate)
                .deleted(rs.getBoolean("deleted"))
                .build();
    }

    @Override
    public boolean addNew(GymPost t) throws SQLException, ClassNotFoundException {
        String sql = """
                     insert into gym_post(
                     gym_id,
                     gym_post_tile,
                     gym_post_status,
                     gym_post_img,
                     created_date,
                     deleted) values(?,?,?,?,?,?)
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);){
            ps.setObject(1, t.getGymId());
            ps.setObject(2, t.getGymPostTile());
            ps.setObject(3, t.getGymPostStatus());
            ps.setObject(4, t.getGymPostImg());
            ps.setObject(5, t.getCreatedDate().getTime());
            ps.setObject(6, t.isDeleted());
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public List<GymPost> getAll() throws SQLException, ClassNotFoundException {
        List<GymPost> list = new ArrayList<>();
        String sql = """
                     select * from gym_post 
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
    public GymPost getById(String id) throws SQLException, ClassNotFoundException {
        String sql = """
                     select * from gym_post where gym_post_id = ?
                     """;
        GymPost gymPost = GymPost.builder().gymPostId(0L).build();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);){
            ps.setString(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {                
                gymPost = mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return gymPost;
    }

    @Override
    public boolean updateById(String id, GymPost t) throws SQLException, ClassNotFoundException {
        String sql = """
                     update gym_post
                     set
                     gym_post_tile = ?,
                     gym_post_status = ?,
                     gym_post_img = ?,
                     created_date=?,
                     deleted = ? where gym_post_id = ?
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);){
            ps.setObject(1, t.getGymPostTile());
            ps.setObject(2, t.getGymPostStatus());
            ps.setObject(3, t.getGymPostImg());
            ps.setObject(4, t.getCreatedDate().getTime());
            ps.setObject(5, t.isDeleted());
            ps.setObject(6, parseInt(id));
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
                     update gym_post set deleted=1 where gym_post_id = ? 
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
