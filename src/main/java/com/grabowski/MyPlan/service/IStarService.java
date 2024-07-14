package com.grabowski.MyPlan.service;

import com.grabowski.MyPlan.model.Star;
import com.grabowski.MyPlan.model.StarCreateResponse;

import java.util.Collection;
import java.util.List;
import java.util.Map;


public interface IStarService {
    List<Star> findClosestStars(List<Star> stars, int size);
    Map<Long, Integer> getNumberOfStarsByDistances(List<Star> stars);
    Collection<Star> getUniqueStars(Collection<Star> stars);

    Star getStarById(Integer id);
    StarCreateResponse addNewStar(Star star);
    Star updateStar(Integer id, Star star);
    void deleteStar(Integer id);
}
