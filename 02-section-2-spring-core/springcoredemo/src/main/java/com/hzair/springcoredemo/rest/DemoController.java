package com.hzair.springcoredemo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hzair.springcoredemo.common.Coach;

@RestController
public class DemoController {

    private Coach myCoach;
    // private Coach anotherCoach;

    // ! Constructor Injection (preferred way to do dependency injection in Spring)
    @Autowired 
    public DemoController(@Qualifier("aquatic") Coach theCoach
                /*@Qualifier("baseballCoach") Coach theCoach
                ,@Qualifier("baseballCoach") Coach theAnotherCoach*/) { // * @Qualifier("baseballCoach") is used to specify which implementation of Coach to inject if there are multiple beans of type Coach
                                                                          // * Instead, we can use @Primary on one of the Coach implementations to make it the  default bean to inject, so we don't have to use @Qualifier here
        System.out.println(">> Inside constructor :" + getClass().getSimpleName());
        myCoach = theCoach; 
        // anotherCoach = theAnotherCoach;
    }

    // ! Setter Injection (the method can be named anything, but it's common to use "set" + the name of the dependency)
    // @Autowired
    // public void setCoach(Coach theCoach) {
    // myCoach = theCoach;
    // }

    // Expose a new endpoint for "daily workout"
    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }
    // Expose a new endpoint for "checking if the two Coach beans are the same instance"
    // @GetMapping("/check")
    // public String check() {
    //     return "Comparing beans : myCoach == anotherCoach : " + (myCoach == anotherCoach);
    // }
}
