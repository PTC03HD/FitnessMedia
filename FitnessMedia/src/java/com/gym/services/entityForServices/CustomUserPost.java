/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.services.entityForServices;

import com.gym.entity.Comment;
import com.gym.entity.Emotion;
import com.gym.entity.User;
import com.gym.entity.UserPost;
import java.util.List;
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
@AllArgsConstructor
@NoArgsConstructor
public class CustomUserPost {

    private User user;
    private UserPost userPost;
    private List<CustomComment> listCustomComments;
    private List<Emotion> listEmotions;

    public int numberOfComments() {
        return listCustomComments.size();
    }

    public int numberOfEmotion() {
        return listEmotions.size();
    }

    public int numberOfLikes() {
        int s = 0;
        for (Emotion emotion : listEmotions) {
            if (!emotion.isDislike()) {
                s++;
            }
        }
        return s;
    }

    public int numberOfDislikes() {
        int s = 0;
        for (Emotion emotion : listEmotions) {
            if (emotion.isDislike()) {
                s++;
            }
        }
        return s;
    }
    
    public boolean checkUserLike(Long userId){
        for (Emotion emotion : listEmotions) {
            if(emotion.getMakerId().equals(userId)&&!emotion.isDislike()){
                return true;
            }
        }
        return false;
    }
    
    public boolean checkUserDisLike(Long userId){
        for (Emotion emotion : listEmotions) {
            if(emotion.getMakerId().equals(userId)&&emotion.isDislike()){
                return true;
            }
        }
        return false;
    }
    
    public int countLines(String input) {
        int numOfLine = input.length()/110+3;
        return numOfLine;
    }
}
