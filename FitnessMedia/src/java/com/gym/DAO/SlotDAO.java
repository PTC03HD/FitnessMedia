/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.DAO;

import com.gym.entity.Slot;
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
public class SlotDAO implements RowMapper<Slot>{

    @Override
    public Slot mapRow(ResultSet rs) throws SQLException {
        return Slot.builder()
                .slotId(rs.getLong("slot_id"))
                .slotOrder(rs.getLong("slot_order"))
                .startTime(rs.getLong("start_time"))
                .endTime(rs.getLong("end_time"))
                .build();
    }

    @Override
    public boolean addNew(Slot t) throws SQLException, ClassNotFoundException {
        String sql = """
                     insert into slot(slot_order,
                     start_time,
                     end_time) values(?,?,?)
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);){
            ps.setObject(1, t.getSlotOrder());
            ps.setObject(2, t.getStartTime());
            ps.setObject(3, t.getEndTime());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public List<Slot> getAll() throws SQLException, ClassNotFoundException {
        List<Slot> list = new ArrayList<>();
        String sql = """
                     select * from slot
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
    public Slot getById(String id) throws SQLException, ClassNotFoundException {
        String sql = """
                     select * from slot where slot_id = ?
                     """;
        Slot slot = Slot.builder().slotId(0L).build();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);){
            ps.setObject(1, parseInt(id));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                slot = mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return slot;
    }

    @Override
    public boolean updateById(String id, Slot t) throws SQLException, ClassNotFoundException {
        String sql = """
                     update slot set
                     slot_order = ?,
                     start_time = ?,
                     end_time = ? where slot_id = ?
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);){
            ps.setObject(1, t.getSlotOrder());
            ps.setObject(2, t.getStartTime());
            ps.setObject(3, t.getEndTime());
            ps.setObject(4, t.getSlotId());
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
                     delete from slot where slot id = ?
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
