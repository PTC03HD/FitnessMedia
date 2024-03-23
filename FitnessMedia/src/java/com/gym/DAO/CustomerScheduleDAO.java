/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.DAO;

import com.gym.entity.CustomerSchedule;
import java.util.ArrayList;
import java.util.List;
import static com.gym.jdbc.connection.SQLServerConnection.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ba Tung
 */
public class CustomerScheduleDAO {

    public List<CustomerSchedule> TimeSchedule(String makerId) {
        List<CustomerSchedule> list = new ArrayList<>();
        String sql = """
                     select a.schedule_id, a.schedule_date, b.slot_id,b.slot_order, b.start_time, b.end_time, c.gym_id, c.gym_name, d.booking_id, d.maker_id, e.[user_id], e.[img_wall]
                     from schedule a join slot b on b.slot_id = a.slot_id
                     join gym c on c.gym_id = a.gym_id 
                     join booking d on d.schedule_id = a.schedule_id
                     join [user] e on e.[user_id] = d.[maker_id]
                     Where d.maker_id = ?
                     """;
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,makerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CustomerSchedule p = new CustomerSchedule();
                p.setSchedule_id(rs.getInt("schedule_id"));
                p.setSchedule_date(rs.getDate("schedule_date"));
                p.setSlot_id(rs.getInt("slot_id"));
                p.setSlot_order(rs.getInt("slot_order"));
                p.setStart_time(rs.getInt("start_time"));
                p.setEnd_time(rs.getInt("end_time"));
                p.setGym_id(rs.getInt("gym_id"));
                p.setGym_name(rs.getString("gym_name"));
                p.setBooking_id(rs.getInt("booking_id"));
                p.setMaker_id(rs.getInt("maker_id"));
                p.setUser_id(rs.getInt("user_id"));
                p.setImgWall(rs.getString("img_wall"));
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
