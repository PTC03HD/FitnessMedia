/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.services.coreServices;

import com.gym.DAO.GymReviewDAO;
import com.gym.entity.GymReview;
import com.gym.jdbc.connection.SQLServerConnection;
import static com.gym.jdbc.connection.SQLServerConnection.getConnection;
import com.gym.services.entityForServices.TotalReview;
import com.gym.services.entityForServices.TotalVote;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class GymReviewService extends GymReviewDAO{
    
    public TotalReview TotalReview() {
        String sql = """
                     select Count(review_id) as TotalReview from gym_review 
                     """;
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new TotalReview(rs.getInt(1)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<TotalVote> TotalVote() {
        List<TotalVote> list = new ArrayList<>();
        String sql = """
                     select vote from gym_review 
                     """;
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 TotalVote p = new TotalVote();
                p.setTotal(rs.getInt("vote"));
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<GymReview> getByGymId(String id) throws ClassNotFoundException, SQLException {
        String sql = """
                        select * from gym_review where gym_id = ?
                     """;
        List<GymReview> gymReviews = new ArrayList<>();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, parseInt(id));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                gymReviews.add(mapRow(rs));
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return gymReviews;
    }
}
