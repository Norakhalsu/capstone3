package com.example.capstone3ee.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Rating {

    @Id
    private Integer ratingId;

    @NotEmpty(message = "Comment cannot be null or empty")
    @Column(columnDefinition = "varchar(255) not null")
    private String comment;

    @NotNull(message = "Rating cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer ratings;


//    @NotNull(message = "Rating cannot be null")
//    @Column(columnDefinition = "int not null")
//    private Integer getRating;



    // --------------------------------- Relations ---------------------
    @OneToOne
    @MapsId
    @JsonIgnore
    private Request request; // request must exist to have rating


//    @ManyToOne
//    @JsonIgnore
//    private User user; // one user have many ratings




    //    @ManyToOne
//    private Expert expert;

}