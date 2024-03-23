/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.DAO;

import com.gym.entity.MaintainFee;
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
public class MaintainFeeDAO implements RowMapper<MaintainFee> {

    @Override
    public MaintainFee mapRow(ResultSet rs) throws SQLException {
        Long sDate = 0L;
        try {
            sDate = Long.parseLong(rs.getString("start_date"));
        } catch (NumberFormatException e) {
        }
        Date startDate = new Date(sDate);
        Long eDate = 0L;
        try {
            eDate = Long.parseLong(rs.getString("end_date"));
        } catch (NumberFormatException e) {
        }
        Date endDate = new Date(eDate);
        return MaintainFee.builder()
                .maintainFeeId(rs.getLong("maintain_fee_id"))
                .ownerId(rs.getLong("owner_id"))
                .totalMoney(rs.getDouble("total_money"))
                .startDate(startDate)
                .endDate(endDate)
                .billCode(rs.getString("bill_code"))
                .qrCode(rs.getString("qr_code"))
                .paid(rs.getBoolean("paid"))
                .deleted(rs.getBoolean("deleted"))
                .build();
    }

    @Override
    public boolean addNew(MaintainFee t) throws SQLException, ClassNotFoundException {
        String sql = """
                     insert into maintain_fee(
                     owner_id,
                     total_money,
                     start_date,
                     end_date,
                     bill_code,
                     qr_code,
                     paid,
                     deleted) values(?,?,?,?,?,?,?,?)
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, t.getOwnerId());
            ps.setObject(2, t.getTotalMoney());
            ps.setObject(3, t.getStartDate().getTime());
            ps.setObject(4, t.getEndDate().getTime());
            ps.setObject(5, t.getBillCode());
            ps.setObject(6, t.getQrCode());
            ps.setObject(7, t.isPaid());
            ps.setObject(8, t.isDeleted());
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public List<MaintainFee> getAll() throws SQLException, ClassNotFoundException {
        List<MaintainFee> list = new ArrayList<>();
        String sql = """
                     select * from maintain_fee
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
    public MaintainFee getById(String id) throws SQLException, ClassNotFoundException {
        String sql = """
                     select * from maintain_fee where maintain_fee_id = ?
                     """;
        MaintainFee maintainFee = MaintainFee.builder().maintainFeeId(0L).build();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, parseInt(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                maintainFee = mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return maintainFee;
    }

    @Override
    public boolean updateById(String id, MaintainFee t) throws SQLException, ClassNotFoundException {
        String sql = """
                     update maintain_fee
                     set
                     total_money = ?,
                     start_date = ?,
                     end_date = ?,
                     bill_code = ?,
                     qr_code = ?,
                     paid = ?,
                     deleted = ? where maintain_fee_id = ?
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, t.getTotalMoney());
            ps.setObject(2, t.getStartDate());
            ps.setObject(3, t.getEndDate());
            ps.setObject(4, t.getBillCode());
            ps.setObject(5, t.getQrCode());
            ps.setObject(6, t.isPaid());
            ps.setObject(7, t.isDeleted());
            ps.setObject(8, parseInt(id));
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
                     delete from maintain_fee where maintain_fee_id = ?
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
