package com.example.capstone3ee.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.capstone3ee.Model.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
    Rating findRatingByRatingId(Integer id);
}
