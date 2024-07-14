package com.grabowski.MyPlan.repository;

import com.grabowski.MyPlan.exceptions.NotFoundException;
import com.grabowski.MyPlan.model.Star;
import com.grabowski.MyPlan.model.StarCreateResponse;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryStarRepository{
    private Integer nextId = 1;
    private Map<Integer, Star> storedStars = new HashMap<>();

    public Star getStarById(Integer starId){
        Star star = this.storedStars.get(starId);
        if(star == null){
            throw new NotFoundException("Entity with id: " + starId + " not found");
        }
        return star;
    }
    public StarCreateResponse addNewStar(Star star){
        this.storedStars.put(nextId, star);
        StarCreateResponse starCreateResponse = new StarCreateResponse(nextId, star.getName(), star.getDistance());
        nextId += 1;
        return starCreateResponse;
    }

    public Star updateStar(Integer id, Star star){
        if (star == null || id < 1) {
            throw new IllegalArgumentException("Bad parameters");
        }

        if (!storedStars.containsKey(id)) {
            throw new NotFoundException("Entity with id: " + id + " not found");
        }
        this.storedStars.put(id, star);
        return star;
    }

    public void deleteStar(Integer id){
        if (id < 1) {
            throw new IllegalArgumentException("Id is lower than 1");
        }

        if (!storedStars.containsKey(id)) {
            throw new NotFoundException("Entity with id: " + id + " not found");
        }
        this.storedStars.remove(id);
    }
}
