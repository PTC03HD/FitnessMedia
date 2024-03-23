/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.DAO;

import com.gym.entity.Bill;
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
public class BillDAO implements RowMapper<Bill> {

    @Override
    public Bill mapRow(ResultSet rs) throws SQLException {
        return Bill.builder()
                .billId(rs.getLong("bill_id"))
                .billCode(rs.getString("bill_code"))
                .totalMoney(rs.getDouble("total_money"))
                .content(rs.getString("content"))
                .qrCode(rs.getString("qr_code"))
                .paid(rs.getBoolean("paid"))
                .deleted(rs.getBoolean("deleted"))
                .build();
    }

    @Override
    public boolean addNew(Bill t) throws SQLException, ClassNotFoundException {
        String sql = """
                     insert into bill(
                     bill_code,
                     total_money,
                     content,
                     qr_code,
                     paid,
                     deleted) values(?,?,?,?,?,?)
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, t.getBillCode());
            ps.setObject(2, t.getTotalMoney());
            ps.setObject(3, t.getContent());
            ps.setObject(4, t.getQrCode());
            ps.setObject(5, t.isPaid());
            ps.setObject(6, t.isDeleted());
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public List<Bill> getAll() throws SQLException, ClassNotFoundException {
        List<Bill> list = new ArrayList<>();
        String sql = """
                     select * from bill
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
    public Bill getById(String id) throws SQLException, ClassNotFoundException {
        String sql = """
                     select * from bill where bill_id = ?
                     """;
        Bill bill = Bill.builder().billId(0L).build();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);){
            ps.setObject(1, parseInt(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                bill = mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return bill;
    }

    @Override
    public boolean updateById(String id, Bill t) throws SQLException, ClassNotFoundException {
        String sql = """
                     update bill
                     set
                     bill_code = ?,
                     total_money = ?,
                     content = ?,
                     qr_code = ?,
                     paid = ?,
                     deleted = ? where bill_id = ?
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);){
            ps.setObject(1, t.getBillCode());
            ps.setObject(2, t.getTotalMoney());
            ps.setObject(3, t.getContent());
            ps.setObject(4, t.getQrCode());
            ps.setObject(5, t.isPaid());
            ps.setObject(6, t.isDeleted());
            ps.setObject(7, parseInt(id));
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
                     delete from bill where bill_id = ?
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
