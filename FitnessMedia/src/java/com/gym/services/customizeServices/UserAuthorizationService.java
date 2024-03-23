/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.services.customizeServices;

import com.gym.entity.Role;
import com.gym.entity.User;
import com.gym.entity.UserRole;
import com.gym.services.coreServices.RoleService;
import com.gym.services.coreServices.UserRoleService;
import com.gym.services.coreServices.UserService;
import com.gym.services.entityForServices.UserAuthorization;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class UserAuthorizationService {

    private UserService userService;
    private UserRoleService userRoleService;
    private RoleService roleService;

    public UserAuthorizationService() {
        this.userService = new UserService();
        this.userRoleService = new UserRoleService();
        this.roleService = new RoleService();
    }

    public UserAuthorization getUserAuthorizationByUserId(String userId) throws SQLException, ClassNotFoundException{
        User user = User.builder().userId(0L).build();
        user = userService.getById(userId);
        List<UserRole> listUserRoles = new ArrayList<>();
        List<Role> listRoles = new ArrayList<>();
        listUserRoles = userRoleService.getListUserRolesByUserId(userId);
        listRoles = roleService.getListRolesByListUserRoles(listUserRoles);
        UserAuthorization userAuthorization = UserAuthorization.builder()
                .user(user)
                .listRoles(listRoles)
                .build();
        return userAuthorization;
    }

//    public Integer parseInt(String n) {
//        try {
//            Integer i = Integer.parseInt(n);
//            return i;
//        } catch (NumberFormatException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    public Double parseDouble(String n) {
//        try {
//            Double i = Double.parseDouble(n);
//            return i;
//        } catch (NumberFormatException e) {
//            e.printStackTrace();
//        }
//        return 0.0;
//    }
}