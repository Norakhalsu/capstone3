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
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reqId;


  //  @NotEmpty(message = "Status cannot be null or empty")
  //  @Column(columnDefinition = "varchar(20) not null")
    private String status;

    @NotEmpty(message = "request Description mut be not empty ")
    private String requestDescription;


    // -------------------------------------------- Relations -----------------------------
     @ManyToOne
     @JsonIgnore
     private Expert expert; // one expert have many requests


    @OneToOne(cascade = CascadeType.ALL, mappedBy = "request")// one request have one feedback
    @PrimaryKeyJoinColumn
    private FeedBack feedBack;


    @OneToOne(cascade = CascadeType.ALL, mappedBy = "request")// request must exist to have rating
    @PrimaryKeyJoinColumn
    private Rating rating;

    @ManyToOne
    @JsonIgnore
    private User user; // one user have many request




}
