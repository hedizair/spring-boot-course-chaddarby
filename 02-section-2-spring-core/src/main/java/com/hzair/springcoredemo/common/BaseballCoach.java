package com.hzair.springcoredemo.common;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) // * This annotation is used to indicate that a new instance of the bean should be created each time it is requested, rather than using a single shared instance (singleton scope).
public class BaseballCoach implements Coach {

    public BaseballCoach() {
        System.out.println(">> Inside constructor :" + getClass().getSimpleName());
    }

    // Define the init method
    @PostConstruct
    public void doMyStartStuff() {
        System.out.println(">> Inside method doMyStartStuff() : " + getClass().getSimpleName());
    }

    // Define the destroy method
 


    @Override
    public String getDailyWorkout() {
        return "Practice your baseball skills for 30 minutes !!!!! :-) ";
    }
}
