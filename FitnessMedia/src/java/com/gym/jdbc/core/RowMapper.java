/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.gym.jdbc.core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author admin
 */
public interface RowMapper<T> {

    T mapRow(ResultSet rs) throws SQLException;

    boolean addNew(T t) throws SQLException, ClassNotFoundException;

    List<T> getAll() throws SQLException, ClassNotFoundException;

    T getById(String id) throws SQLException, ClassNotFoundException;

    boolean updateById(String id, T t) throws SQLException, ClassNotFoundException;

    boolean deleteById(String id) throws SQLException, ClassNotFoundException;

    default Integer parseInt(String n) {
        try {
            Integer i = Integer.parseInt(n);
            return i;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    default Double parseDouble(String n) {
        try {
            Double i = Double.parseDouble(n);
            return i;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}
