// src/main/java/com/example/capstone3ee/Service/RatingService.java
package com.example.capstone3ee.Service;

import com.example.capstone3ee.Api.ApiException;
import com.example.capstone3ee.DTO.RatingDTO;
import com.example.capstone3ee.Model.Rating;
import com.example.capstone3ee.Model.Request;
import com.example.capstone3ee.Repository.RatingRepository;
import com.example.capstone3ee.Repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;
    private final RequestRepository requestRepository;

    // GET
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }


    public void addRating(RatingDTO ratingDTO) {
        Request request = requestRepository.findRequestByReqId(ratingDTO.getRequestId());
        if (request == null) {
            throw new ApiException("Request not found");
        }
        Rating rating = new Rating(null,ratingDTO.getComment(),ratingDTO.getRatings(),request);
        ratingRepository.save(rating);
    }



    public Rating updateRating(RatingDTO ratingDTO) {
        Rating rating = ratingRepository.findRatingByRatingId(ratingDTO.getRequestId());
        if (rating == null) {
            throw new ApiException("Rating not found");
        }
        rating.setRatings(ratingDTO.getRatings());
        rating.setComment(ratingDTO.getComment());
        // rating.setExpert(updatedRating.getExpert());

        return ratingRepository.save(rating);
    }

    public void deleteRating(Integer id) {
        ratingRepository.deleteById(id);
    }
}