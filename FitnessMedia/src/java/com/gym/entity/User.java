/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author admin
 */
@Builder
@Getter
@Setter
@ToString
public class User {

    private Long userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String perPhone;
    private Date createdDate;
    private String idCitizen;
    private String bankName;
    private String bankCode;
    private String nameInBank;
    private String imgCardFront;
    private String imgCardBack;
    private String imgWall;
    private String imgAvatar;
    private String activeCode;
    private boolean deleted;
    
}
