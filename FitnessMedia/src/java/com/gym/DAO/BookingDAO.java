/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.DAO;

import com.gym.entity.Booking;
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
public class BookingDAO implements RowMapper<Booking> {

    @Override
    public Booking mapRow(ResultSet rs) throws SQLException {
        Long longDate = 0L;
        try {
            longDate = Long.parseLong(rs.getString("created_date"));
        } catch (NumberFormatException e) {
        }
        Date createdDate = new Date(longDate);
        return Booking.builder()
                .bookingId(rs.getLong("booking_id"))
                .makerId(rs.getLong("maker_id"))
                .scheduleId(rs.getLong("schedule_id"))
                .createdDate(createdDate)
                .deleted(rs.getBoolean("deleted"))
                .experienced(rs.getBoolean("experienced"))
                .build();
    }

    @Override
    public boolean addNew(Booking t) throws SQLException, ClassNotFoundException {
        String sql = """
                     insert into booking(maker_id,
                     schedule_id,
                     created_date,
                     deleted,
                     experienced) values(?,?,?,?,?)
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, t.getMakerId());
            ps.setObject(2, t.getScheduleId());
            ps.setObject(3, t.getCreatedDate().getTime());
            ps.setObject(4, t.isDeleted());
            ps.setObject(5, t.isExperienced());
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public List<Booking> getAll() throws SQLException, ClassNotFoundException {
        List<Booking> list = new ArrayList<>();
        String sql = """
                     select * from booking
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
    public Booking getById(String id) throws SQLException, ClassNotFoundException {
        String sql = """
                     select * from where booking_id = ?
                     """;
        Booking booking = Booking.builder().bookingId(0L).build();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, parseInt(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                booking = mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return booking;
    }

    @Override
    public boolean updateById(String id, Booking t) throws SQLException, ClassNotFoundException {
        String sql = """
                     update booking
                     set
                     maker_id = ?,
                     schedule_id = ?,
                     created_date = ?,
                     deleted = ?,
                     experienced = ? where booking_id = ?
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, t.getMakerId());
            ps.setObject(2, t.getScheduleId());
            ps.setObject(3, t.getCreatedDate().getTime());
            ps.setObject(4, t.isDeleted());
            ps.setObject(5, t.isExperienced());
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
                     delete from booking where booking_id = ?;
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
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
