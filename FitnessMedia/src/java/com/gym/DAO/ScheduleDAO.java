/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.DAO;

import com.gym.entity.Schedule;
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
public class ScheduleDAO implements RowMapper<Schedule>{

    @Override
    public Schedule mapRow(ResultSet rs) throws SQLException {
        return Schedule.builder()
                .scheduleId(rs.getLong("schedule_id"))
                .gymId(rs.getLong("gym_id"))
                .slotId(rs.getLong("slot_id"))
                .scheduleDate(rs.getDate("schedule_date"))
                .maxNumber(rs.getLong("max_number"))
                .build();
    }

    @Override
    public boolean addNew(Schedule t) throws SQLException, ClassNotFoundException {
        String sql  = """
                      insert into schedule(gym_id,
                      slot_id,
                      schedule_date,
                      max_number) values(?,?,?,?)
                      """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);){
            ps.setObject(1, t.getGymId());
            ps.setObject(2, t.getSlotId());
            ps.setObject(3, t.getScheduleDate());
            ps.setObject(4, t.getMaxNumber());
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public List<Schedule> getAll() throws SQLException, ClassNotFoundException {
        List<Schedule> list = new ArrayList<>();
        String sql = """
                     select * from schedule
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
    public Schedule getById(String id) throws SQLException, ClassNotFoundException {
        String sql = """
                     select * from schedule where schedule_id = ?
                     """;
        Schedule schedule = Schedule.builder().scheduleId(0L).build();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);){
            ps.setObject(1, parseInt(id));
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                schedule = mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return schedule;
    }

    @Override
    public boolean updateById(String id, Schedule t) throws SQLException, ClassNotFoundException {
        String sql = """
                     update schedule 
                     set
                     gym_id = ?,
                     slot_id = ?,
                     schedule_date = ?,
                     max_number = ? where schedule_id = ?
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);){
            ps.setObject(1, t.getGymId());
            ps.setObject(2, t.getSlotId());
            ps.setObject(3, t.getScheduleDate());
            ps.setObject(4, t.getMaxNumber());
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
                     delete from schedule where schedule_id = ?
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
