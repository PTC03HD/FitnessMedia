/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.services.coreServices;

import com.gym.DAO.BookingDAO;
import com.gym.entity.Booking;
import com.gym.jdbc.connection.SQLServerConnection;
import jakarta.servlet.jsp.jstl.sql.Result;
import java.sql.SQLException;
import org.apache.tomcat.dbcp.dbcp2.SQLExceptionList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author admin
 */
public class BookingService extends BookingDAO {

    public List<Booking> getByMakerId(String id) throws SQLException, ClassNotFoundException {
        String sql = """
                        select * from booking where maker_id = ?
                     """;
        List<Booking> ls = new ArrayList<>();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ls.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ls;
    }

    public int getBookNumberOfASchedule(Long scheduleId, List<Booking> bookingList) {
        int count = 0;
        for(Booking s : bookingList) {
            if(s.getScheduleId().equals(scheduleId) && !s.isDeleted()) {
                count++;
            }
        }
        return count;
    }
    
    public List<Booking> getNewestBook(String top) throws SQLException, ClassNotFoundException {
        String sql = "SELECT TOP "+top+" * FROM booking ORDER BY booking_id DESC";
        List<Booking> ls = new ArrayList<>();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ls.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ls;
    }
    
//    public static void main(String args[]) {
//        BookingService bookingService = new BookingService();
//        try {
//            System.out.println(bookingService.getNewestBook("5"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
