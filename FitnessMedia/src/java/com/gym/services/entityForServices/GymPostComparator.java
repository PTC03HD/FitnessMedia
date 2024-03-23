/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.services.entityForServices;

import com.gym.entity.GymPost;
import java.util.Comparator;

/**
 *
 * @author dodat
 */
public class GymPostComparator implements Comparator<GymPost> {

    @Override
    public int compare(GymPost post1, GymPost post2) {
        long time1 = post1.getCreatedDate().getTime();
        long time2 = post2.getCreatedDate().getTime();

        if (time1 > time2) {
            return 1;
        } else if (time1 < time2) {
            return -1;
        } else {
            return 0;
        }
    }

}
