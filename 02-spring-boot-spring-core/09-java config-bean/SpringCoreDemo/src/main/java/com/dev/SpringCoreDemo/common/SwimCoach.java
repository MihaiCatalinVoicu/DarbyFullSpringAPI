package com.dev.SpringCoreDemo.common;

public class SwimCoach implements Coach{

    public SwimCoach() {
        System.out.println("In constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Swim 100m as a warm up";
    }
}
