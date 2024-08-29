package com.example.capstone3ee.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Resume {
    @Id
    private Integer resumeId;

    @Column(columnDefinition = "varchar(50) not null")
    private String content;

    @ElementCollection
    private List<String> skills;

    @ElementCollection
    private List<String> projects;

    @ElementCollection
    private List<String> certification;

    @ElementCollection
    private List<String> award;

    @Column(columnDefinition = "varchar(50) not null")
    private String education;

    private int resumeRating;


    // ------------------------------------------ Relations -------------------
    @OneToOne
    @MapsId
    @JsonIgnore
    private User user; // must user exist to have resume




}
