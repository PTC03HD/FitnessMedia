/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.DAO;

import com.gym.entity.UserRole;
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
public class UserRoleDAO implements RowMapper<UserRole> {

    @Override
    public UserRole mapRow(ResultSet rs) throws SQLException {
        return UserRole.builder()
                .userRoleId(rs.getLong("user_role_id"))
                .userId(rs.getLong("user_id"))
                .roleId(rs.getLong("role_id"))
                .build();
    }

    @Override
    public boolean addNew(UserRole t) throws SQLException, ClassNotFoundException {
        String sql = """
                     insert into user_role(user_id,role_id) values(?,?)
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, t.getUserId());
            ps.setObject(2, t.getRoleId());
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public List<UserRole> getAll() throws SQLException, ClassNotFoundException {
        List<UserRole> list = new ArrayList<>();
        String sql = """
                     select * from user_role
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
    public UserRole getById(String id) throws SQLException, ClassNotFoundException {
        String sql = """
                     select * from user_role where user_role_id = ?
                     """;
        UserRole userRole = UserRole.builder().userRoleId(0L).build();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, parseInt(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                userRole = mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return userRole;
    }

    @Override
    public boolean updateById(String id, UserRole t) throws SQLException, ClassNotFoundException {
        String sql = """
                     update user_role
                     set
                     user_id = ?,
                     role_id = ? where user_role_id = ?
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, t.getUserId());
            ps.setObject(2, t.getRoleId());
            ps.setObject(3, t.getUserRoleId());
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
                     delete from user_role where user_role_id = ?
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
