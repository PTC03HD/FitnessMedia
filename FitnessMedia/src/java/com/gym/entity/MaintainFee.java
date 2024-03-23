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
public class MaintainFee {

    private Long maintainFeeId;
    private Long ownerId;
    private Double totalMoney;
    private Date startDate;
    private Date endDate;
    private String billCode;
    private String qrCode;
    private boolean paid;
    private boolean deleted;

}
