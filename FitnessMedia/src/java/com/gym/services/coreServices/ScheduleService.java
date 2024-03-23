/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.services.coreServices;

import com.gym.DAO.ScheduleDAO;
import com.gym.entity.Schedule;
import com.gym.jdbc.connection.SQLServerConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.time.Year;

/**
 *
 * @author admin
 */
public class ScheduleService extends ScheduleDAO {

    public List<Schedule> getAllByGymId(String gymId) throws SQLException, ClassNotFoundException {
        List<Schedule> result = new ArrayList<>();
        String sql = """
                        select * from schedule where gym_id = ?
                        and schedule_date between ? and ?;
                     """;
        String year = Year.now().toString();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, gymId);
            ps.setObject(2, year + "-01-01");
            ps.setObject(3, year + "-12-31");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Schedule> getGym(String gymId) throws SQLException, ClassNotFoundException {
        List<Schedule> list = new ArrayList<>();
        String sql = """
                     select * from schedule where gym_id = ?
                     """;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, parseInt(gymId));
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
