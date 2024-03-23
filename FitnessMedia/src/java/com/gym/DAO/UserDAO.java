/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.DAO;

import com.gym.entity.User;
import com.gym.jdbc.connection.SQLServerConnection;
import com.gym.jdbc.core.RowMapper;
import java.io.Console;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author admin
 */
public class UserDAO implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs) throws SQLException {
        Long longDate = 0L;
        try {
            longDate = Long.parseLong(rs.getString("created_date"));
        } catch (NumberFormatException e) {
        }
        Date createdDate = new Date(longDate);
        return User.builder()
                .userId(rs.getLong("user_id"))
                .userName(rs.getString("user_name"))
                .userPassword(rs.getString("user_password"))
                .userEmail(rs.getString("user_email"))
                .perPhone(rs.getString("per_phone"))
                .createdDate(createdDate)
                .idCitizen(rs.getString("id_citizen"))
                .bankName(rs.getString("bank_name"))
                .bankCode(rs.getString("bank_code"))
                .nameInBank(rs.getString("name_in_bank"))
                .imgCardFront(rs.getString("img_card_front"))
                .imgCardBack(rs.getString("img_card_back"))
                .imgWall(rs.getString("img_wall"))
                .imgAvatar(rs.getString("img_avatar"))
                .activeCode(rs.getString("active_code"))
                .deleted(rs.getBoolean("deleted"))
                .build();
    }

    @Override
    public boolean addNew(User t) throws SQLException, ClassNotFoundException {
        String sql = """
                     insert into [user] (
                     [user_name], 
                     user_password, 
                     user_email, 
                     per_phone, 
                     created_date, 
                     id_citizen, 
                     bank_name, 
                     bank_code, 
                     name_in_bank, 
                     img_card_front, 
                     img_card_back, 
                     img_wall, 
                     img_avatar, 
                     active_code, 
                     deleted)
                     values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, t.getUserName());
            ps.setObject(2, t.getUserPassword());
            ps.setObject(3, t.getUserEmail());
            ps.setObject(4, t.getPerPhone());
            ps.setObject(5, t.getCreatedDate().getTime()+"");
            ps.setObject(6, t.getIdCitizen());
            ps.setObject(7, t.getBankName());
            ps.setObject(8, t.getBankCode());
            ps.setObject(9, t.getNameInBank());
            ps.setObject(10, t.getImgCardFront());
            ps.setObject(11, t.getImgCardBack());
            ps.setObject(12, t.getImgWall());
            ps.setObject(13, t.getImgAvatar());
            ps.setObject(14, t.getActiveCode());
            ps.setObject(15, t.isDeleted());

            check = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public List<User> getAll() throws SQLException, ClassNotFoundException {
        List<User> list = new ArrayList<>();
        String sql = """
                     select * from [user]
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
    public User getById(String id) throws SQLException, ClassNotFoundException {
        String sql = """
                     select * from [user] where [user_id] = ?
                     """;
        User user = User.builder().userId(0L).build();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, parseInt(id));
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

    @Override
    public boolean updateById(String id, User t) throws SQLException, ClassNotFoundException {
        String sql = """
                     update [user]
                     set 
                     user_password = ?,
                     user_email = ?,
                     per_phone = ?,
                     id_citizen = ?,
                     bank_name = ?,
                     bank_code = ?,
                     name_in_bank = ?,
                     img_card_front = ?,
                     img_card_back = ?,
                     img_wall = ?,
                     img_avatar = ?,
                     active_code=?,
                     deleted = ? where [user_id] = ?
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, t.getUserPassword());
            ps.setObject(2, t.getUserEmail());
            ps.setObject(3, t.getPerPhone());
            ps.setObject(4, t.getIdCitizen());
            ps.setObject(5, t.getBankName());
            ps.setObject(6, t.getBankCode());
            ps.setObject(7, t.getNameInBank());
            ps.setObject(8, t.getImgCardFront());
            ps.setObject(9, t.getImgCardBack());
            ps.setObject(10, t.getImgWall());
            ps.setObject(11, t.getImgAvatar());
            ps.setObject(12, t.getActiveCode());
            ps.setObject(13, t.isDeleted());
            ps.setObject(14, parseInt(id));
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
                     delete from [user]
                     where [user_id] = ?
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
