// src/main/java/com/example/capstone3ee/Controller/RatingController.java
package com.example.capstone3ee.Controller;

import com.example.capstone3ee.DTO.RatingDTO;
import com.example.capstone3ee.Model.Rating;
import com.example.capstone3ee.Service.RatingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rating")
@RequiredArgsConstructor
public class RatingController {
    private final RatingService ratingService;

    // GET all ratings
    @GetMapping("/get")
    public ResponseEntity getAllRatings() {
        List<Rating> ratings = ratingService.getAllRatings();
        return  ResponseEntity.status(200).body(ratings);
    }

    // ADD a new rating
    @PostMapping("/add")
    public ResponseEntity addRating(@Valid @RequestBody RatingDTO ratingDTO) {
        ratingService.addRating(ratingDTO);
        return  ResponseEntity.status(201).body(" rating's request added successfully");
    }

    // UPDATE an existing rating
    @PutMapping("/update")
    public ResponseEntity updateRating(@Valid @RequestBody RatingDTO ratingDTO) {
        Rating rating = ratingService.updateRating(ratingDTO);
        return ResponseEntity.status(201).body(" rating's request updated successfully");
    }

    // DELETE a rating
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteRating(@PathVariable Integer id) {
        ratingService.deleteRating(id);
        return ResponseEntity.status(204).body("rating deleted successfully");
    }


}