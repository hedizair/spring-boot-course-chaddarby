package com.hzair.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements Coach {
    
    public TennisCoach() {
        System.out.println(">> Inside constructor :" + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice your backhand volley for 30 minutes!!!!! :-) ";
    }
    
}
