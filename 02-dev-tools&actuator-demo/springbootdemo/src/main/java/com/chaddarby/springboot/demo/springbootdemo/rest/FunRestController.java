package com.chaddarby.springboot.demo.springbootdemo.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {
    @Value("${coach.name}")
    private String coachName;
    @Value("${coach.age}")
    private String coachAge;
    @Value("${team.name}")
    private String teamName;

    // expose "/" that return "Hello World"

    @GetMapping("/")
    public String sayHello() {
        return "Hello World" + " from " + coachName + " of the " + teamName + " ! His age is " + coachAge
                + " years old.";
    }

    // expose a new endpoint for "workout"
    @GetMapping("/workout")
    public String getWorkout() {
        return "Run a hard 5k!";
    }

    // expose a new endpoint for "fortune"
    @GetMapping("/fortune")
    public String getDailyFortune() {
        return "Today is your lucky day!";
    }

}
