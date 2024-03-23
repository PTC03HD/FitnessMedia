/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.DAO;

import com.gym.entity.AccountList;
import com.gym.entity.TotalAccount;
import com.gym.jdbc.connection.SQLServerConnection;
import static com.gym.jdbc.connection.SQLServerConnection.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ba Tung
 */
public class AccountDAO {

    public List<AccountList> getAccountList() {
        List<AccountList> list = new ArrayList<>();
        String sql = """
                     Select u.[user_id], u.[user_name], u.[user_email], r.[role_id], r.[role_name], u.[deleted]
                                          from [user] u join [user_role] ur on u.[user_id] = ur.[user_id]
                                          join [role] r on ur.[role_id] = r.[role_id]
                                          where r.[role_id] > 1 
                     """;
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AccountList p = new AccountList();
                p.setUser_id(rs.getInt("user_id"));
                p.setUser_name(rs.getString("user_name"));
                p.setUser_email(rs.getString("user_email"));
                p.setRole_id(rs.getInt("role_id"));
                p.setRole_name(rs.getString("role_name"));
                p.setDeleted(rs.getBoolean("deleted"));
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<AccountList> getAccountPage(int index, int pageSize) {
        List<AccountList> list = new ArrayList<>();
        String sql = """
                     Select u.[user_id], u.[user_name], u.[user_email], r.[role_id], r.[role_name], u.[deleted]
                                          from [user] u join [user_role] ur on u.[user_id] = ur.[user_id]
                                          join [role] r on ur.[role_id] = r.[role_id]
                                          where r.[role_id] > 1 
                                          order by u.[user_id]
                     		          OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;
                     """;
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, (index - 1) * pageSize);
            ps.setObject(2, pageSize);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AccountList p = new AccountList();
                p.setUser_id(rs.getInt("user_id"));
                p.setUser_name(rs.getString("user_name"));
                p.setUser_email(rs.getString("user_email"));
                p.setRole_id(rs.getInt("role_id"));
                p.setRole_name(rs.getString("role_name"));
                p.setDeleted(rs.getBoolean("deleted"));
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<AccountList> getAllPagination() {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("Select u.[user_id], u.[user_name], u.[user_email], r.[role_id], r.[role_name], u.[deleted] ");
        sqlBuilder.append("from [user] u join [user_role] ur on u.[user_id] = ur.[user_id] ");
        sqlBuilder.append("join [role] r on ur.[role_id] = r.[role_id]");
        sqlBuilder.append("where r.[role_id] > 1");
        String sql = sqlBuilder.toString();
        List<AccountList> ls = new ArrayList<>();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AccountList p = new AccountList();
                p.setUser_id(rs.getInt("user_id"));
                p.setUser_name(rs.getString("user_name"));
                p.setUser_email(rs.getString("user_email"));
                p.setRole_id(rs.getInt("role_id"));
                p.setRole_name(rs.getString("role_name"));
                p.setDeleted(rs.getBoolean("deleted"));
                ls.add(p);
            }
        } catch (Exception e) {
        }
        return ls;
    }

    public int getTotalPage(int pageSize, List<AccountList> ls) {
        AccountDAO gd = new AccountDAO();
        int count = 0;
        try {
            if (pageSize != 0) {
                count = ls.size() / pageSize;
            }
            if (gd.getAccountList().size() % pageSize != 0) {
                count++;
            }
        } catch (Exception e) {
        }
        return count;
    }

    public TotalAccount TotalAccount() {
        String sql = """
                     select Count([user_id]) as TotalAccoount from [user]
                     where [user_id] >1 
                     """;
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new TotalAccount(rs.getInt(1)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<AccountList> SearchByUserName(String txt) {
        List<AccountList> list = new ArrayList<>();
        String sql = """
                     Select u.[user_id], u.[user_name], u.[user_email], r.[role_id], r.[role_name], u.[deleted]
                                          from [user] u join [user_role] ur on u.[user_id] = ur.[user_id]
                                          join [role] r on ur.[role_id] = r.[role_id]
                                          where r.[role_id] > 1 and u.[user_name] LIKE ?
                     """;
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,"%"+txt+"%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AccountList p = new AccountList();
                p.setUser_id(rs.getInt("user_id"));
                p.setUser_name(rs.getString("user_name"));
                p.setUser_email(rs.getString("user_email"));
                p.setRole_id(rs.getInt("role_id"));
                p.setRole_name(rs.getString("role_name"));
                p.setDeleted(rs.getBoolean("deleted"));
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<AccountList> FilterByRole(String txt) {
        List<AccountList> list = new ArrayList<>();
        String sql = """
                     Select u.[user_id], u.[user_name], u.[user_email], r.[role_id], r.[role_name], u.[deleted]
                                                               from [user] u join [user_role] ur on u.[user_id] = ur.[user_id]
                                                               join [role] r on ur.[role_id] = r.[role_id]
                                                               where r.[role_id] > 1 and r.[role_name] LIKE ?
                     """;
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,"%"+txt+"%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AccountList p = new AccountList();
                p.setUser_id(rs.getInt("user_id"));
                p.setUser_name(rs.getString("user_name"));
                p.setUser_email(rs.getString("user_email"));
                p.setRole_id(rs.getInt("role_id"));
                p.setRole_name(rs.getString("role_name"));
                p.setDeleted(rs.getBoolean("deleted"));
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<AccountList> FilterByStatus(String txt) {
        List<AccountList> list = new ArrayList<>();
        String sql = """
                     Select u.[user_id], u.[user_name], u.[user_email], r.[role_id], r.[role_name], u.[deleted]
                                                               from [user] u join [user_role] ur on u.[user_id] = ur.[user_id]
                                                               join [role] r on ur.[role_id] = r.[role_id]
                                                               where r.[role_id] > 1 and u.[deleted] = ?
                     """;
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,txt);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AccountList p = new AccountList();
                p.setUser_id(rs.getInt("user_id"));
                p.setUser_name(rs.getString("user_name"));
                p.setUser_email(rs.getString("user_email"));
                p.setRole_id(rs.getInt("role_id"));
                p.setRole_name(rs.getString("role_name"));
                p.setDeleted(rs.getBoolean("deleted"));
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public AccountList GetAccount(String id) {
        String sql = """
                     Select u.[user_id], u.[user_name], u.[user_email], r.[role_id], r.[role_name], u.[deleted]
                                                               from [user] u join [user_role] ur on u.[user_id] = ur.[user_id]
                                                               join [role] r on ur.[role_id] = r.[role_id]
                                                               where u.[user_id] = ?
                     """;
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new AccountList(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getBoolean(6) 
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
}
