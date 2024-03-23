/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.services.coreServices;

import com.gym.DAO.BillDAO;
import com.gym.entity.Bill;
import com.gym.jdbc.connection.SQLServerConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author admin
 */
public class BillService extends BillDAO{
    public Bill getLastBill() throws SQLException, ClassNotFoundException{
        String sql = """
                        SELECT TOP 1 * FROM Bill ORDER BY bill_id DESC;
                     """;
        Bill b = Bill.builder().build();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                b = mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return b;
    }
    
}
