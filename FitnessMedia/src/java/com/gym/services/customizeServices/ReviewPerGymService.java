/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.services.customizeServices;

import com.gym.entity.*;
import com.gym.jdbc.core.RowMapper;
import com.gym.services.coreServices.*;
import com.gym.services.entityForServices.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author phamt
 */
public class ReviewPerGymService{
    private GymReviewService gymReviewService;
    private GymService gymService;

    public ReviewPerGymService(GymReviewService gymReviewService, GymService gymService) {
        this.gymReviewService = gymReviewService;
        this.gymService = gymService;
    }
    
    public ReviewPerGym getReviewPerGymByGymId(String id) throws SQLException, ClassNotFoundException {
        Gym gym = gymService.getById(id)==null?Gym.builder().build():gymService.getById(id);
        List<GymReview> gymReviews = gymReviewService.getByGymId(id);
        long AverageVote = 5;
        if(gymReviews != null){
            long totalVote = gymReviews.stream().mapToLong(GymReview::getVote).sum();
            long totalUser = gymReviews.size();
            if(totalUser != 0) {
                AverageVote = (totalVote / totalUser);
            }
        }
        ReviewPerGym reivewPerGym = ReviewPerGym.builder()
                .gym(gym)
                .gymReview(gymReviews)
                .AverageVote(AverageVote)
                .build();
        return reivewPerGym;
    }
    
    public List<ReviewPerGym> getAll() throws SQLException, ClassNotFoundException{
        List<ReviewPerGym> ls = new ArrayList<>();
        List<Gym> gymList = gymService.getAll();
        for (Gym g : gymList) {
            ls.add(getReviewPerGymByGymId(g.getGymId() + ""));
        }
        return ls;
    }
    
    
    public HashMap<Long, ReviewPerGym> getAllByOrder(int con) throws SQLException, ClassNotFoundException{
        HashMap<Long, ReviewPerGym> result = new HashMap<>();
        List<ReviewPerGym> ls = getAll();
       
        for(ReviewPerGym g : ls) {
//            g.getGymReview().sort(((g1, g2) -> g1.getCreatedDate().compareTo(g2.getCreatedDate())));
            switch (con) {
                case 1:
                    g.getGymReview().sort((g1, g2) -> g1.getCreatedDate().compareTo( g2.getCreatedDate()));
                    break;
                case 2:
                    g.getGymReview().sort((g1, g2) -> g2.getCreatedDate().compareTo( g1.getCreatedDate()));
                    break;
                case 3:
                    g.getGymReview().sort((g1, g2) -> g1.getVote().compareTo( g2.getVote()));
                    break;
                case 4:
                    g.getGymReview().sort((g1, g2) -> g2.getVote().compareTo( g1.getVote()));
                    break;
                default:
                    break;
            }
            result.put(g.getGym().getGymId(), g);
        }
        return result;
    }
}
