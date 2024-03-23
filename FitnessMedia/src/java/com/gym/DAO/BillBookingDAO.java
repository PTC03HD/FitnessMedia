/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.DAO;

import com.gym.entity.BillBooking;
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
public class BillBookingDAO implements RowMapper<BillBooking>{

    @Override
    public BillBooking mapRow(ResultSet rs) throws SQLException {
        Long longDate = 0L;
        try {
            longDate = Long.parseLong(rs.getString("end_date"));
        } catch (NumberFormatException e) {
        }
        Date endDate = new Date(longDate);
        return BillBooking.builder()
                .billBookingId(rs.getLong("bill_booking_id"))
                .billId(rs.getLong("bill_id"))
                .bookingId(rs.getLong("booking_id"))
                .price(rs.getDouble("price"))
                .endDate(endDate)
                .deleted(rs.getBoolean("deleted"))
                .build();
    }

    @Override
    public boolean addNew(BillBooking t) throws SQLException, ClassNotFoundException {
        String sql = """
                     insert into bill_booking(
                     bill_id,
                     booking_id,
                     price,
                     end_date,
                     deleted) values(?,?,?,?,?)
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);){
            ps.setObject(1, t.getBillId());
            ps.setObject(2, t.getBookingId());
            ps.setObject(3, t.getPrice());
            ps.setObject(4, t.getEndDate());
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
    public List<BillBooking> getAll() throws SQLException, ClassNotFoundException {
        List<BillBooking> list = new ArrayList<>();
        String sql = """
                     select * from bill_booking
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
    public BillBooking getById(String id) throws SQLException, ClassNotFoundException {
        String sql = """
                     select * from bill_booking where bill_booking_id = ?
                     """;
        BillBooking billBooking = BillBooking.builder().billBookingId(0L).build();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);){
            ps.setObject(1, parseInt(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {                
                billBooking = mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return billBooking;
    }

    @Override
    public boolean updateById(String id, BillBooking t) throws SQLException, ClassNotFoundException {
        String sql = """
                     update bill_booking
                     set
                     bill_id = ?,
                     booking_id = ?,
                     price = ?,
                     end_date = ?,
                     deleted = ? where bill_booking_id= ?
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);){
            ps.setObject(1, t.getBillId());
            ps.setObject(2, t.getBookingId());
            ps.setObject(3, t.getPrice());
            ps.setObject(4, t.getEndDate());
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
                     delete from bill_booking where bill_booking_id = ?
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
