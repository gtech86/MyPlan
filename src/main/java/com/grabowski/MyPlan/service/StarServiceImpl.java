package com.grabowski.MyPlan.service;

import com.grabowski.MyPlan.model.Star;
import com.grabowski.MyPlan.model.StarCreateResponse;
import com.grabowski.MyPlan.repository.InMemoryStarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StarServiceImpl implements IStarService {

    private final InMemoryStarRepository starRepository;

    @Override
    public Star getStarById(Integer id) {
        return starRepository.getStarById(id);
    }

    @Override
    public StarCreateResponse addNewStar(Star star) {
        return starRepository.addNewStar(star);
    }

    @Override
    public Star updateStar(Integer id, Star star) {
        return starRepository.updateStar(id, star);
    }

    @Override
    public void deleteStar(Integer id) {
        starRepository.deleteStar(id);
    }
    @Override
    public List<Star> findClosestStars(List<Star> stars, int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Size cannot be negative.");
        }

        if (size > stars.size()) {
            throw new IllegalArgumentException("Size cannot be greater than the number of stars in the list.");
        }
        stars.sort(Comparator.comparingDouble(Star::getDistance));

        return stars.subList(0, size);
    }

    @Override
    public Map<Long, Integer> getNumberOfStarsByDistances(List<Star> stars) {
        if (stars == null) {
            throw new IllegalArgumentException("Stars list cannot be null.");
        }

        Map<Long, Integer> distanceMap = new TreeMap<>();

        for (Star star : stars) {
            Long distance = star.getDistance();
            distanceMap.put(distance, distanceMap.getOrDefault(distance, 0) + 1);
        }

        return distanceMap;
    }
    @Override
    public Collection<Star> getUniqueStars(Collection<Star> stars) {
        if (stars == null) {
            throw new IllegalArgumentException("Stars list cannot be null.");
        }
        return stars.stream().distinct().collect(Collectors.toList());
    }
}
