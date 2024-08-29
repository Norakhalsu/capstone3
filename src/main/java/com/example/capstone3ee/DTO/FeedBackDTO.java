package com.example.capstone3ee.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor
@NoArgsConstructor
public class FeedBackDTO {

    @NotNull(message = " Request Id is Required ")
    private Integer requestId;

    @NotEmpty(message = "Feedback id required")
    @Size(min = 3 , max = 300,message = "Feedback Text length must be 3-3-- characters")
    private String feedbackText;

}
