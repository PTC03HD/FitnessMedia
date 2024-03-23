/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.services.coreServices;

import com.gym.DAO.UserRoleDAO;
import com.gym.entity.UserRole;
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
public class UserRoleService extends UserRoleDAO{
    public List<UserRole> getListUserRolesByUserId(String userId) throws SQLException, ClassNotFoundException{
        List<UserRole> listUserRoles = new ArrayList<>();
        String sql = """
                     select * from user_role where user_id = ?
                     """;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);){
            ps.setObject(1, parseInt(userId));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                listUserRoles.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listUserRoles;
    }
    
    public UserRole getRoleById(String id) throws SQLException, ClassNotFoundException {
        String sql = """
                     select * from user_role where user_id = ?
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
}
