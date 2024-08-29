package com.example.capstone3ee.DTO;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class RatingDTO {

   @NotNull(message = "request id is required ")
    private int requestId;

   // private int userId;

    @NotEmpty(message = "Comment cannot be null or empty")
    @Size(min=3 , max = 100,message = "Comment length must be 3-100 characters")
    private String comment;

    @NotNull(message = "Rating cannot be null")
    private Integer ratings;
}
