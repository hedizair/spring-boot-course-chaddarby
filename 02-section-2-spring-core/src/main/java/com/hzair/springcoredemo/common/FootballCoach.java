package com.hzair.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
// @Lazy // * This annotation is used to indicate that the bean should be lazily initialized, meaning it will only be created when it is first requested (ex : dependency injection or manual creation), rather than at application startup. 
public class FootballCoach implements Coach {

    public FootballCoach() {
        System.out.println(">> Inside constructor :" + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice your free kicks for 30 minutes!!!!! :-) ";
    }
    
}
