package com.example.capstone3ee.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data @AllArgsConstructor
public class ResumeDTO {

    @NotNull(message = " User Id is Required ")
    private Integer userId;

    @NotEmpty(message = "Content must be not empty")
    private String content;
    private List<String> skills;
    private List<String> projects;
    private List<String> certification;
    private List<String> award;
    private int resumeRating;

    @NotEmpty(message = "Eduction must be not empty")
    private String education;
}
