/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.DAO;

import com.gym.entity.GymReview;
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
public class GymReviewDAO implements RowMapper<GymReview> {

    @Override
    public GymReview mapRow(ResultSet rs) throws SQLException {
        Long longDate = 0L;
        try {
            longDate = Long.parseLong(rs.getString("created_date"));
        } catch (NumberFormatException e) {
        }
        Date createdDate = new Date(longDate);
        return GymReview.builder()
                .reviewId(rs.getLong("review_id"))
                .makerId(rs.getLong("maker_id"))
                .gymId(rs.getLong("gym_id"))
                .reviewContent(rs.getString("review_content"))
                .vote(rs.getLong("vote"))
                .createdDate(createdDate)
                .hide(rs.getBoolean("hide"))
                .build();
    }

    @Override
    public boolean addNew(GymReview t) throws SQLException, ClassNotFoundException {
        String sql = """
                     insert into gym_review(
                     maker_id,
                     gym_id,
                     review_content,
                     vote,
                     created_date,
                     hide) 
                     values(?,?,?,?,?,?)
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, t.getMakerId());
            ps.setObject(2, t.getGymId());
            ps.setObject(3, t.getReviewContent());
            ps.setObject(4, t.getVote());
            ps.setObject(5, t.getCreatedDate().getTime());
            ps.setObject(6, t.isHide());
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public List<GymReview> getAll() throws SQLException, ClassNotFoundException {
        List<GymReview> list = new ArrayList<>();
        String sql = """
                     select * from gym_review
                     """;
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
    public GymReview getById(String id) throws SQLException, ClassNotFoundException {
        String sql = """
                     select * from gym_review where gym_id = ?
                     """;
        GymReview gymReview = GymReview.builder().gymId(0L).build();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, parseInt(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                gymReview = mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return gymReview;
    }

    @Override
    public boolean updateById(String id, GymReview t) throws SQLException, ClassNotFoundException {
        String sql = """
                     update gym_review 
                     set
                     review_content = ?,
                     vote = ?,
                     hide = ? where review_id = ?
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);){
            ps.setObject(1, t.getReviewContent());
            ps.setObject(2, t.getVote());
            ps.setObject(3, parseInt(id));
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
                     delete from gym_review where gym_review_id = ?
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
