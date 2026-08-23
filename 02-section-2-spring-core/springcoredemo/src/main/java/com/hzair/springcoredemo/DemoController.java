package com.hzair.springcoredemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    // Define private field for the dependency
    private Coach myCoach;

    // Define a constructor for dependency injection
    @Autowired // Optional if only one constructor is present, we keep it here for clarity
    public DemoController(Coach theCoach) {
        myCoach = theCoach; 
    }

    // Expose a new endpoint for "daily workout"
    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }
}
