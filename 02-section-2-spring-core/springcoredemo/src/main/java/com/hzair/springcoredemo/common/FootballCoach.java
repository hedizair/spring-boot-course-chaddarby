package com.hzair.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
public class FootballCoach implements Coach {

    @Override
    public String getDailyWorkout() {
        return "Practice your free kicks for 30 minutes!!!!! :-) ";
    }
    
}
