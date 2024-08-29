package com.example.capstone3ee.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor @Entity
public class Groups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer groupId;

    @NotEmpty(message = "Name of group is required")
    private String name;

    @Column(columnDefinition = "varchar(30) unique  not null")
    @NotEmpty(message = "Group type of group is required")
    private String groupType;


    @NotEmpty(message = "Description of group is required")
    private String description;

    private int teamPerformance;

    @ElementCollection
    private List<String> projects;

    private boolean applicable;

    @ElementCollection
    private List<String> requiredSkills;


    // ------------------------------ Relations -----------------
        @JsonIgnore
        @ManyToOne
        @JoinColumn(name = "leader_id")
        private User leader;  // Change from String to User

        @ManyToMany
        @JoinTable(
                name = "group_members",
                joinColumns = @JoinColumn(name = "groupId"),
                inverseJoinColumns = @JoinColumn(name = "userId") )
        private List<User> members;

    @ManyToMany
    @JsonIgnore
    private Set<User> users;

    @ManyToMany(mappedBy ="groups")
    private Set<Events> events;

     @ManyToMany(mappedBy = "groups")
    private Set<Tasks> tasks;



//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "leader_id")
//    private User leader;  // Change from String to User

}
