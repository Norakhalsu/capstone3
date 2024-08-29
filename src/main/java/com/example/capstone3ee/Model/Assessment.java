package com.example.capstone3ee.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor @Entity
public class Assessment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer assessmentId;
   // private Integer userId;

    @NotEmpty(message = "Assessment Type must be not empty")
    private String assessmentType;

    @NotNull(message = "Score Assessment not null")
    private int score;

    @ElementCollection
    private List<String> questions;

    @ElementCollection
    private List<String> answer;

    @NotNull(message = " date not null ")
    private LocalDate dateTaken;

   private String status;

  // -------------------------- Relations ----------------------------
    @ManyToOne
    @JsonIgnore
    private User user; // 1 user many assessment

    @ManyToOne
    @JsonIgnore
    private Expert expert; // 1 expert many assessments

}
