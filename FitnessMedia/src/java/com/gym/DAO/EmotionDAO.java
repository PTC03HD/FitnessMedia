/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.DAO;

import com.gym.entity.Emotion;
import com.gym.jdbc.connection.SQLServerConnection;
import com.gym.jdbc.core.RowMapper;
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
public class EmotionDAO implements RowMapper<Emotion>{

    @Override
    public Emotion mapRow(ResultSet rs) throws SQLException {
        return Emotion.builder()
                .emotionId(rs.getLong("emotion_id"))
                .userPostId(rs.getLong("user_post_id"))
                .gymPostId(rs.getLong("gym_post_id"))
                .makerId(rs.getLong("maker_id"))
                .isDislike(rs.getBoolean("is_dislike"))
                .deleted(rs.getBoolean("deleted"))
                .build();
    }

    @Override
    public boolean addNew(Emotion t) throws SQLException, ClassNotFoundException {
        String sql = """
                     insert into emotion(
                     user_post_id,
                     gym_post_id,
                     maker_id,
                     is_dislike,
                     deleted) values(?,?,?,?,?)
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);){
            ps.setObject(1, t.getUserPostId());
            ps.setObject(2, t.getGymPostId());
            ps.setObject(3, t.getMakerId());
            ps.setObject(4, t.isDislike());
            ps.setObject(5, t.isDeleted());
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public List<Emotion> getAll() throws SQLException, ClassNotFoundException {
        List<Emotion> list = new ArrayList<>();
        String sql = """
                     select * from emotion
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
    public Emotion getById(String id) throws SQLException, ClassNotFoundException {
        String sql = """
                     select * from emotion where emotion_id = ?
                     """;
        Emotion emotion = Emotion.builder().emotionId(0L).build();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);){
            ps.setObject(1, parseInt(id));
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                emotion = mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return emotion;
    }

    @Override
    public boolean updateById(String id, Emotion t) throws SQLException, ClassNotFoundException {
        String sql = """
                     update emotion
                     set
                     is_dislike = ?,
                     deleted = ? where emotion_id = ?
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);){
            ps.setObject(1, t.isDislike());
            ps.setObject(2, t.isDeleted());
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
                     delete from emotion where emotion_id = ?
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
