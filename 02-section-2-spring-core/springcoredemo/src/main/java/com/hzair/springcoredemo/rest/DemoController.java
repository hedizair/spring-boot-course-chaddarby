package com.hzair.springcoredemo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hzair.springcoredemo.common.Coach;

@RestController
public class DemoController {

    // Define private field for the dependency
    private Coach myCoach;

    // Define a constructor for dependency injection
    // ! Constructor Injection  (preferred way to do dependency injection in Spring)
    // @Autowired // Optional if only one constructor is present, we keep it here for clarity
    // public DemoController(Coach theCoach) {
    //     myCoach = theCoach; 
    // }

    // ! Setter Injection (the method can be named anything, but it's common to use "set" + the name of the dependency)
    @Autowired
    public void setCoach(Coach theCoach) {
        myCoach = theCoach;
    }

    // Expose a new endpoint for "daily workout"
    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }
}
