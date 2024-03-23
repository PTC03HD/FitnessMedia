/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.entity;

import lombok.*;

/**
 *
 * @author admin
 */
@Builder
@Getter
@Setter
@ToString
public class Bill {

    private Long billId;
    private String billCode;
    private Double totalMoney;
    private String content;
    private String qrCode;
    private boolean paid;
    private boolean deleted;
}
