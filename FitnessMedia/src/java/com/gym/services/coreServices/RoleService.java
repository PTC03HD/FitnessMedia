/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.services.coreServices;

import com.gym.DAO.RoleDAO;
import com.gym.entity.Role;
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
public class RoleService extends RoleDAO{
    public List<Role> getListRolesByListUserRoles(List<UserRole> listUserRoles) throws SQLException, ClassNotFoundException{
        List<Role> listRoles = new ArrayList<>();
        for (UserRole userRole  : listUserRoles) {
            Role role = Role.builder().build();
            role = getById(userRole.getRoleId()+"");
            listRoles.add(role);
        }
        return listRoles;
    }
    
    public List<Role> getRole() throws SQLException, ClassNotFoundException {
        List<Role> list = new ArrayList<>();
        String sql = """
                     select * from role where role_id >1
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
}
