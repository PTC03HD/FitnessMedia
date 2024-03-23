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
import lombok.ToString;

/**
 *
 * @author phamt
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReviewPerGym {
    private Gym gym;
    private List<GymReview> gymReview;
    private long AverageVote;
    
    public boolean isReviewed(Long userId) {
        return gymReview.stream().anyMatch(review -> review.getMakerId().equals(userId));
    }
}
