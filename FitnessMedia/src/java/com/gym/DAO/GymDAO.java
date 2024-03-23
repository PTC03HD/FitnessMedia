/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.DAO;

import com.gym.entity.Gym;
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
public class GymDAO implements RowMapper<Gym> {

    @Override
    public Gym mapRow(ResultSet rs) throws SQLException {
        Long longDate = 0L;
        try {
            longDate = Long.parseLong(rs.getString("created_date"));
        } catch (NumberFormatException e) {
        }
        Date createdDate = new Date(longDate);
        return Gym.builder()
                .gymId(rs.getLong("gym_id"))
                .ownerId(rs.getLong("owner_id"))
                .gymName(rs.getString("gym_name"))
                .gymCost(rs.getDouble("gym_cost"))
                .gymNation(rs.getString("gym_nation"))
                .gymProvince(rs.getString("gym_province"))
                .gymDistrict(rs.getString("gym_district"))
                .gymAddressLink(rs.getString("gym_address_link"))
                .createdDate(createdDate)
                .managerId(rs.getLong("mananger_id"))
                .gymWall(rs.getString("gym_wall"))
                .gymAvatar(rs.getString("gym_avatar"))
                .build();
    }

    @Override
    public boolean addNew(Gym t) throws SQLException, ClassNotFoundException {
        String sql = """
                     insert into gym(
                     owner_id,
                     gym_name,
                     gym_cost,
                     gym_nation,
                     gym_province,
                     gym_district,
                     gym_address_link,
                     created_date,
                     mananger_id,
                     gym_wall,
                     gym_avatar,
                     closed) 
                     values(?,?,?,?,?,?,?,?,?,?,?,?)
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, t.getOwnerId());
            ps.setObject(2, t.getGymName());
            ps.setObject(3, t.getGymCost());
            ps.setObject(4, t.getGymNation());
            ps.setObject(5, t.getGymProvince());
            ps.setObject(6, t.getGymDistrict());
            ps.setObject(7, t.getGymAddressLink());
            ps.setObject(8, t.getCreatedDate().getTime() + "");
            ps.setObject(9, t.getManagerId());
            ps.setObject(10, t.getGymWall());
            ps.setObject(11, t.getGymAvatar());
            ps.setObject(12, t.isClosed());
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public List<Gym> getAll() throws SQLException, ClassNotFoundException {
        List<Gym> list = new ArrayList<>();
        String sql = """
                     select * from gym
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
    public Gym getById(String id) throws SQLException, ClassNotFoundException {
        String sql = """
                     select * from gym where gym_id = ?
                     """;
        Gym gym = Gym.builder()
                .gymId(0L)
                .build();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);){
            ps.setObject(1, parseInt(id));
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                gym = mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return gym;
    }

    @Override
    public boolean updateById(String id, Gym t) throws SQLException, ClassNotFoundException {
        String sql = """
                     update gym
                     set
                     gym_name = ?,
                     gym_cost = ?,
                     gym_nation = ?,
                     gym_province = ?,
                     gym_district = ?,
                     gym_address_link = ?,
                     mananger_id = ?,
                     gym_wall = ?,
                     gym_avatar = ?,
                     closed = ?
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);){
            ps.setObject(1, t.getGymName());
            ps.setObject(2, t.getGymCost());
            ps.setObject(3, t.getGymNation());
            ps.setObject(4, t.getGymProvince());
            ps.setObject(5, t.getGymDistrict());
            ps.setObject(6, t.getGymAddressLink());
            ps.setObject(7, t.getManagerId());
            ps.setObject(8, t.getGymWall());
            ps.setObject(9, t.getGymAvatar());
            ps.setObject(10, t.isClosed());
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
                     delete from gym where gym_id = ?
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
