/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.DAO;

import com.gym.entity.homeDetail;
import com.gym.entity.homeDetail2;
import com.gym.jdbc.connection.SQLServerConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ba Tung
 */
public class homePageDAO extends SQLServerConnection {

    public List<homeDetail> getAllGym() {
        List<homeDetail> list = new ArrayList<>();
        String sql = "select top 6 * from gym order by gym_id desc";
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                homeDetail p = new homeDetail();
                p.setGym_id(rs.getInt("gym_id"));
                p.setOwner_id(rs.getInt("owner_id"));
                p.setGym_name(rs.getString("gym_name"));
                p.setGym_cost(rs.getDouble("gym_cost"));
                p.setGym_nation(rs.getString("gym_nation"));
                p.setGym_province(rs.getString("gym_province"));
                p.setGym_wall(rs.getString("gym_wall"));
                p.setGym_avatar(rs.getString("gym_avatar"));
                p.setClosed(rs.getBoolean("closed"));
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public homeDetail2 getPopOwner() {
        String sql = """
                     SELECT u.img_avatar, u.img_wall, u.per_phone, COUNT(g.gym_id) AS totalRoom
                     FROM [user] u
                     JOIN gym g ON u.[user_id] = g.owner_id
                     GROUP BY u.[user_id], u.img_avatar, u.img_wall, u.per_phone
                     ORDER BY totalRoom DESC
                     """;
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new homeDetail2(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    

}
