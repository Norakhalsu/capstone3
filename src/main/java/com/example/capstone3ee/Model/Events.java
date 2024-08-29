package com.example.capstone3ee.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.Group;

import java.time.LocalDate;
import java.util.Set;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor @Entity
public class Events {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "organizer is required ")
    @Column(columnDefinition = "varchar(30) not null")
    private String organizer;

    @NotEmpty(message = "name is required ")
    @Size(min=3,max = 50,message = "name length must be 3-50 characters")
    @Column(columnDefinition = "varchar(50) not null")
    private String name;

    @Column(columnDefinition = "varchar(300) not null")
    @NotEmpty(message = "description is required ")
    private String description;

    @NotEmpty(message = "location is required ")
    @Column(columnDefinition = "varchar(50) not null")
    private String location;

    @NotNull(message = "Start date cannot be null")
    @Column(columnDefinition = "datetime not null")
    private LocalDate startDate;

    @NotNull(message = "End date cannot be null")
    @Column(columnDefinition = "datetime not null")
    private LocalDate endDate;

    @Column(columnDefinition = "int ")
    private int eventsRating;


    // --------------------------------- Relations --------------------------------------------
    @ManyToMany
    @JsonIgnore
    private Set<Groups> groups;

}
