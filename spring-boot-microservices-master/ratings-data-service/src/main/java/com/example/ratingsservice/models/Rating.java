package com.example.ratingsservice.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Ratings")
@Data
public class Rating {
    @EmbeddedId
    private RatingId ratingId;
    private int rating;
    public Rating(RatingId ratingId, int rating) {
        this.ratingId = ratingId;
        this.rating = rating;
    }
    public Rating() {

    }
}

