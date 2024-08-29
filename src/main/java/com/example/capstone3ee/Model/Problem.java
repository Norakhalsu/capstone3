package com.example.capstone3ee.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String question;

    private String problem;

    private String response; //  expert response


    @Column(columnDefinition = "varchar(20) default 'ACTIVE'")
    private String status = "ACTIVE"; // Default status is 'ACTIVE'

    @ManyToOne
    @JsonIgnore
    private Assessment assessment;

    @ManyToOne
    @JsonIgnore
    private Expert expert;
}
