package com.example.ratingsservice.models;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class RatingId implements Serializable {
    private String userId;
    private String movieId;

    public RatingId(String userId, String movieId) {
        this.userId = userId;
        this.movieId = movieId;
    }

    public RatingId() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RatingId ratingId = (RatingId) o;
        return userId.equals(ratingId.userId) && movieId.equals(ratingId.movieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, movieId);
    }
}
