/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.services.coreServices;

import com.gym.DAO.GymPostDAO;
import com.gym.entity.GymPost;
import com.gym.jdbc.connection.SQLServerConnection;
import com.gym.services.entityForServices.GymPostComparator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author admin
 */
public class GymPostService extends GymPostDAO{
    public boolean deleteActive(String id) throws SQLException, ClassNotFoundException {
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
    public List<GymPost> getRecentNews() throws SQLException, ClassNotFoundException {
        List<GymPost> list = new ArrayList<>();
        String sql = """
                     select top 5 * from gym_post
                     where deleted=0
                     order by gym_post.created_date desc
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
        Collections.sort(list, new GymPostComparator());
        return list;
    }
    public List<GymPost> getGympostfromto(int pageSize,int index) throws SQLException, ClassNotFoundException {
        List<GymPost> list = new ArrayList<>();
        String sql = """
                     SELECT *
                     FROM (
                         SELECT *, ROW_NUMBER() OVER (ORDER BY created_date desc) AS RowNum
                         FROM gym_post where deleted=0
                     ) AS RowConstrainedResult
                     WHERE RowNum BETWEEN ?*?-(?-1) and ?*?;
                     """;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);){
            ps.setInt(1, index);
            ps.setInt(2, pageSize);
            ps.setInt(3, pageSize);
            ps.setInt(4, index);
            ps.setInt(5, pageSize);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Collections.sort(list, new GymPostComparator());
        return list;
    }
    public List<GymPost> getAllActive() throws SQLException, ClassNotFoundException {
        List<GymPost> list = new ArrayList<>();
        String sql = """
                     select * from gym_post where deleted=0
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
    public List<GymPost> getGymPostByCategory() throws SQLException, ClassNotFoundException {
        List<GymPost> list = new ArrayList<>();
        String sql = """
                     select top 5 * from gym_post where deleted=0
                     order by gym_post.created_date desc
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
        Collections.sort(list, new GymPostComparator());
        return list;
    }
    public List<GymPost> getGymPostByGym(int pageSize,int index,int gymid) throws SQLException, ClassNotFoundException {
        List<GymPost> list = new ArrayList<>();
        String sql = """
                     SELECT *
                     FROM (
                         SELECT *, ROW_NUMBER() OVER (ORDER BY gym_post_id) AS RowNum
                         FROM gym_post where gym_post.gym_id= ? and gym_post.deleted=0
                     ) AS RowConstrainedResult
                     WHERE RowNum BETWEEN ?*?-(?-1) and ?*?;
                     """;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);){
            ps.setInt(1, gymid);
            ps.setInt(2, index);
            ps.setInt(3, pageSize);
            ps.setInt(4, pageSize);
            ps.setInt(5, index);
            ps.setInt(6, pageSize);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Collections.sort(list, new GymPostComparator());
        return list;
    }
    public int countGymPostByGym(int gymid) throws SQLException, ClassNotFoundException {
        String sql="""
                   SELECT COUNT(*)
                   FROM (
                       SELECT *
                       FROM gym_post
                       WHERE gym_post.gym_id = ? and gym_post.deleted=0
                   ) AS RowConstrainedResult;
                   """;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);){
            ps.setInt(1, gymid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public List<GymPost> getNewP() throws SQLException, ClassNotFoundException {
        List<GymPost> list = new ArrayList<>();
        String sql = """
                     select top 3 * from gym_post order by gym_post_id desc
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

}
