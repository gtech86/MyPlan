package com.grabowski.MyPlan.rest;

import com.grabowski.MyPlan.exceptions.NotFoundException;
import com.grabowski.MyPlan.model.Star;
import com.grabowski.MyPlan.model.StarCreateResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.grabowski.MyPlan.service.IStarService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/star")
@Slf4j
@RequiredArgsConstructor
public class StarController {
    public final IStarService starService;

    /**
     * Get a specific star by ID.
     *
     * @param id The ID of the star to retrieve.
     * @return A ResponseEntity containing the retrieved Star object.
     * @throws NotFoundException if the star with the specified ID does not exist.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Star> getStarById(@PathVariable Integer id) {
        Star star = starService.getStarById(id);
        return ResponseEntity.ok(star);
    }

    /**
     * Add a new star.
     *
     * @param star The Star object to be added.
     * @return A ResponseEntity containing the added Star object.
     * @throws IllegalArgumentException if the star object is invalid.
     */
    @PostMapping
    public ResponseEntity<StarCreateResponse> addNewStar(@RequestBody Star star) {
        return ResponseEntity.ok(starService.addNewStar(star));
    }

    /**
     * Update an existing star.
     *
     * @param id   The ID of the star to update.
     * @param star The updated Star object.
     * @return A ResponseEntity containing the updated Star object.
     * @throws NotFoundException if the star with the specified ID does not exist.
     * @throws IllegalArgumentException if the star object is invalid.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Star> updateStar(@PathVariable Integer id, @RequestBody Star star) {
        starService.updateStar(id, star);
        return ResponseEntity.ok(star);
    }

    /**
     * Delete a star.
     *
     * @param id The ID of the star to delete.
     * @return A ResponseEntity with HTTP status 200 (OK).
     * @throws NotFoundException if the star with the specified ID does not exist.
     * @throws IllegalArgumentException if the ID is invalid.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStar(@PathVariable Integer id) {
        starService.deleteStar(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/closest")
    public ResponseEntity<List<Star>> findClosestStars(@RequestBody List<Star> stars, @RequestParam int size) {
        List<Star> closestStars = starService.findClosestStars(stars, size);
        return new ResponseEntity<>(closestStars, HttpStatus.OK);
    }

    @PostMapping("/distances")
    public ResponseEntity<Map<Long, Integer>> getNumberOfStarsByDistances(@RequestBody List<Star> stars) {
        Map<Long, Integer> starDistances = starService.getNumberOfStarsByDistances(stars);
        return new ResponseEntity<>(starDistances, HttpStatus.OK);
    }

    @PostMapping("/unique")
    public ResponseEntity<Collection<Star>> getUniqueStars(@RequestBody Collection<Star> stars) {
        Collection<Star> uniqueStars = starService.getUniqueStars(stars);
        return new ResponseEntity<>(uniqueStars, HttpStatus.OK);
    }
}
