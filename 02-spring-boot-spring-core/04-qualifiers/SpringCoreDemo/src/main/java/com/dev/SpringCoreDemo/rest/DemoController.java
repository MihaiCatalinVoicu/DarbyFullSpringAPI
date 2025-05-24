package com.dev.SpringCoreDemo.rest;

import com.dev.SpringCoreDemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private Coach myCoach;

    @Autowired
    public DemoController(@Qualifier("baseballCoach") Coach coach) {
        this.myCoach = coach;
    }

    @GetMapping("/dailyWorkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }
}
