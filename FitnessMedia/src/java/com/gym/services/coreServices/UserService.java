/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.services.coreServices;


import com.gym.DAO.UserDAO;

import com.gym.entity.User;
import com.gym.jdbc.connection.SQLServerConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



/**
 *
 * @author admin
 */
public class UserService extends UserDAO {

    public User getActiveUserByUserNameAndPassword(String userName, String userPassword) throws SQLException, ClassNotFoundException {
        String sql = """
                     select * from [user] where user_name = ? and user_password = ? and deleted = 'False'
                     """;
        User user = User.builder().userId(0L).build();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, userName);
            ps.setObject(2, userPassword);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User getUserByUserName(String userName) throws SQLException, ClassNotFoundException {
        String sql = """
                     select * from [user] where [user_name] = ?
                     """;
        User user = User.builder().userId(0L).build();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, userName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }
    
    public User getUserByUserId(Long userid){
        String sql = """
                     select * from [user] where [user_id] = ?
                     """;
        User user = User.builder().userId(0L).build();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);){
            ps.setObject(1, userid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }


    public boolean checkExistUserName(String userName) throws SQLException, ClassNotFoundException {
        String sql = """
                     select * from [user] where [user_name] = ?
                     """;
        User user = User.builder().userId(0L).build();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, userName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (user.getUserId().equals(0L)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkExistUserEmail(String userEmail) {
        String sql = """
                     select * from [user] where user_email = ?
                     """;
        User user = User.builder().userId(0L).build();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, userEmail);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (user.getUserId().equals(0L)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkExistPerPhone(String perPhone) {
        String sql = """
                     select * from [user] where per_phone = ?
                     """;
        User user = User.builder().userId(0L).build();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, perPhone);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (user.getUserId().equals(0L)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean DeleteWithId(String id, User t) throws SQLException, ClassNotFoundException {
        String sql = """
                     update [user]
                     set 
                     deleted = ? where [user_id] = ?
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, t.isDeleted());
            ps.setObject(2, parseInt(id));
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean updateWithId(String id, User t) throws SQLException, ClassNotFoundException {
        String sql = """
                     update [user]
                                           set 
                                           user_email = ?,
                                           per_phone = ?,
                                           id_citizen = ?,
                                           img_wall = ?,
                                           img_avatar = ?
                                            where [user_id] = ?
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setObject(1, t.getUserEmail());
            ps.setObject(2, t.getPerPhone());
            ps.setObject(3, t.getIdCitizen());
            ps.setObject(4, t.getImgWall());
            ps.setObject(5, t.getImgAvatar());
            ps.setObject(6, parseInt(id));
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public HashMap<Long, User> getUserMap() {
        UserDAO d = new UserDAO();
        HashMap<Long, User> result = new HashMap<>();
        List<User> ls = new ArrayList<>();
        try {
             ls = d.getAll();
        } catch (Exception e) {
        }
        for (User u : ls) {
            result.put(u.getUserId(), u);
        }
        return result;
    }

}
