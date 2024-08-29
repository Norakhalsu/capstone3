package com.example.capstone3ee.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor @Entity
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taskId;

    @NotEmpty(message = "Task name is required")
    private String taskName;

    @NotNull(message = "Due date is required")
    @Future(message = "Due date must be in the future")
    private LocalDate dueDate;

    @NotNull(message = "Details are required")
    private String details;

    private int teamPerformanceRating;

    // --------------------------------- relations --------------------------
    @ManyToMany
    @JsonIgnore
    private Set<Groups> groups;

}
