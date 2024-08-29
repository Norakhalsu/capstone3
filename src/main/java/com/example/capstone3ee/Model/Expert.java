package com.example.capstone3ee.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Expert {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer expertId;

//    @NotNull(message = "User ID cannot be null")
//   // @Column(columnDefinition = "varchar(8) unique not null")
//    private Integer userId;


    @NotEmpty(message = "Full Name  cannot be null or empty")
    @Column(columnDefinition = "varchar(65)  not null ")
    private String fullName;

    @NotEmpty(message = "Gender cannot be null or empty")
    private String gender;

    //@Column(columnDefinition = "int not null check(age>=20)")
    @Min(20)
    private  int age;

    @NotEmpty(message = "Major cannot be null or empty")
    @Column(columnDefinition = "varchar(20)  not null ")
    private String major;

    @NotNull(message = "Years of Experience..? ")
    @Column(columnDefinition = "int not null")
    private int yearsOfExperience;

    @NotEmpty(message = "Expertise cannot be null or empty")
    @Column(columnDefinition = "varchar(65)  not null ")
    private String expertiseFailed;

    @NotEmpty(message = "Qualifications cannot be null or empty")
    @Column(columnDefinition = "varchar(35)  not null ")
    private String qualificationDegree;

    @ElementCollection
    @NotEmpty(message = "skills of expert is require ")
    private List<String> skills;

    private Integer ratings;
    private String availability;
    private String status;


// -------------------------------------------- Relations -----------------------------

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "expert") // one expert have many requests
    private Set<Request> requests ;





}
