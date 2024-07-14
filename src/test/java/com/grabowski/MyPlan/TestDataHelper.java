package com.grabowski.MyPlan;

import com.grabowski.MyPlan.model.Star;

import java.util.Arrays;
import java.util.List;

public class TestDataHelper {
    public static List<Star> getStars(){
        return Arrays.asList(
                new Star("Alpha Centauri", 4L),
                new Star("Beta", 5L),
                new Star("Gamma", 7L),
                new Star("Sirius", 8L),
                new Star("Beta", 8L)
        );
    }

}
