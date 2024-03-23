/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.services.entityForServices;
import com.gym.entity.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author ba Tung
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ScheduleCustomer {
    List<CustomerSchedule> schedule;
    List<Slot> slot;

}
