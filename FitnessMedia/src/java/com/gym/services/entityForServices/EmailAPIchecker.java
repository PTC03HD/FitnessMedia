/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.services.entityForServices;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
public class EmailAPIchecker {
    private String email;
    private String autocorrect;
    private String deliverability;
    private String quality_score;
    private Is_valid_format is_valid_format;
    private Is_free_email is_free_email;
    private Is_disposable_email is_disposable_email;
    private Is_role_email is_role_email;
    private Is_catchall_email is_catchall_email;
    private Is_mx_found is_mx_found;
    private Is_smtp_valid is_smtp_valid;
}
