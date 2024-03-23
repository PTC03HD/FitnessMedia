/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.DAO;

import com.gym.entity.Role;
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
public class RoleDAO implements RowMapper<Role>{

    @Override
    public Role mapRow(ResultSet rs) throws SQLException {
        return Role.builder()
                .roleId(rs.getLong("role_id"))
                .roleName(rs.getString("role_name"))
                .build();
    }

    @Override
    public boolean addNew(Role t) throws SQLException, ClassNotFoundException {
        String sql = """
                     insert into role(role_name) values(?)
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);){
            ps.setObject(1, t.getRoleName());
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public List<Role> getAll() throws SQLException, ClassNotFoundException {
        List<Role> list = new ArrayList<>();
        String sql = """
                     select * from role
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
    public Role getById(String id) throws SQLException, ClassNotFoundException {
        Role role = Role.builder()
                .roleId(0L)
                .build();
        String sql = """
                     select * from role where role_id = ?
                     """;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);){
            ps.setObject(1, parseInt(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {                
                role = mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return role;
    }

    @Override
    public boolean updateById(String id, Role t) throws SQLException, ClassNotFoundException {
        String sql = """
                     update role set
                     role_name = ? where role_id = ?
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);){
            ps.setObject(1, t.getRoleName());
            ps.setObject(2, parseInt(id));
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
                     delete from role where role_id = ?
                     """;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);){
            ps.setObject(1, parseInt(id));
        } catch (Exception e) {
        }
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
