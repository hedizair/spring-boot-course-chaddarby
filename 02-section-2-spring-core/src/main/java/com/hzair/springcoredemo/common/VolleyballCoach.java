package com.hzair.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
// @Primary // * This annotation is used to indicate that this bean should be given preference when multiple beans of the same type are present (multiple coach implementations here).
            // * In this case, if there are multiple implementations of the Coach interface, the VolleyballCoach will be injected by default unless a specific bean is specified using @Qualifier.
public class VolleyballCoach implements Coach {

    public VolleyballCoach() {
        System.out.println(">> Inside constructor :" + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice your smash skills for 1 hour :-) ";
    }

}