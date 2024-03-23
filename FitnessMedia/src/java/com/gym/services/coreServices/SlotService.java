/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.services.coreServices;

import com.gym.DAO.SlotDAO;
import com.gym.entity.Slot;
import com.gym.jdbc.connection.SQLServerConnection;
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
public class SlotService extends SlotDAO{
    public List<Slot> getSlotAll() throws SQLException, ClassNotFoundException {
        List<Slot> list = new ArrayList<>();
        String sql = """
                     select * from slot where slot_order >6 and slot_order <24
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
    
    public Slot getSlot(String slotId) throws SQLException, ClassNotFoundException {
        String sql = """
                     select * from slot where slot_id=?
                     """;
        Slot slot = Slot.builder().slotId(0L).build();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);){
            ps.setObject(1, parseInt(slotId));
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
}
