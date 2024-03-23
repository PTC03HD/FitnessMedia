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
public class Gym {
    
    private Long gymId;
    private Long ownerId;
    private String gymName;
    private Double gymCost;
    private String gymNation;
    private String gymProvince;
    private String gymDistrict;
    private String gymAddressLink;
    private Date createdDate;
    private Long managerId;
    private String gymWall;
    private String gymAvatar;
    private boolean closed;
}
