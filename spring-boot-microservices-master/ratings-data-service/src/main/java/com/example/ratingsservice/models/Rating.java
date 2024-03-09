package com.example.ratingsservice.models;

import lombok.Data;
import org.hibernate.validator.constraints.ISBN;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Ratings")
@Data
public class Rating {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
    private String id;
    private String userId;
    private String movieId;
    private int rating;

}
