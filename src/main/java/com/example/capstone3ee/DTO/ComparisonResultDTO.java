package com.example.capstone3ee.DTO;

import lombok.Data;

import java.util.List;

@Data
public class ComparisonResultDTO {

    private Integer userId1;
    private Integer userId2;
    private List<String> skills1;
    private List<String> skills2;
    private List<String> projects1;
    private List<String> projects2;
    private List<String> certifications1;
    private List<String> certifications2;
    private List<String> awards1;
    private List<String> awards2;
    private String education1;
    private String education2;
    private String comparisonSummary;
}
