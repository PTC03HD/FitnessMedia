/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.services.coreServices;

import com.gym.DAO.GymDAO;
import com.gym.entity.Gym;
import com.gym.jdbc.connection.SQLServerConnection;
import com.gym.jdbc.core.RowMapper;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.tomcat.dbcp.dbcp2.SQLExceptionList;

/**
 *
 * @author admin
 */
public class GymService extends GymDAO {

    public List<Gym> getAll(int index, int pageSize, String keyword, int order) throws ClassNotFoundException, SQLException {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT * FROM gym ");
        sqlBuilder.append("WHERE gym_name LIKE ? ");
        sqlBuilder.append("ORDER BY ");
        switch (order) {
            case 1:
                sqlBuilder.append("gym_cost asc ");
                break;
            case 2:
                sqlBuilder.append("gym_cost desc ");
                break;
            default:
                sqlBuilder.append("gym_id ");
        }
        sqlBuilder.append("OFFSET ? ROWS FETCH NEXT ? ROWS ONLY; ");

        String sql = sqlBuilder.toString();
        System.out.println(sql);
        List<Gym> ls = new ArrayList<>();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, "%" + keyword + "%");
            ps.setObject(2, (index - 1) * pageSize);
            ps.setObject(3, pageSize);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ls.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }

    public List<Gym> getAllPagination(String keyword, int order) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT * FROM gym ");
        sqlBuilder.append("WHERE gym_name LIKE ? ");
        sqlBuilder.append("ORDER BY ");
        switch (order) {
            case 1:
                sqlBuilder.append("gym_cost asc ");
                break;
            case 2:
                sqlBuilder.append("gym_cost desc ");
                break;
            default:
                sqlBuilder.append("gym_id ");
        }

        String sql = sqlBuilder.toString();
        List<Gym> ls = new ArrayList<>();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, "%" + keyword + "%");
//            ps.setObject(2, "gym_id");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ls.add(mapRow(rs));
            }
        } catch (Exception e) {
        }
        return ls;
    }

    public int getTotalPage(int pageSize, List<Gym> ls) {
        GymDAO gd = new GymDAO();
        int count = 0;
        try {
            if (pageSize != 0) {
                count = ls.size() / pageSize;
            }
            if (gd.getAll().size() % pageSize != 0) {
                count++;
            }
        } catch (Exception e) {
        }
        return count;
    }

    public HashMap<Long, Gym> getGymMap() {
        GymDAO gd = new GymDAO();
        HashMap<Long, Gym> map = new HashMap();
        try {
            List<Gym> g = gd.getAll();
            for (Gym gym : g) {
                map.put(gym.getGymId(), gym);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    public Gym getGymByManagerId(String managerId) throws ClassNotFoundException, SQLException {
        String sql = """
                     select * from gym where mananger_id = ?
                     """;
        Gym gym = Gym.builder().build();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);){
            ps.setObject(1, parseInt(managerId));
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                gym = mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return gym;
    }
}
