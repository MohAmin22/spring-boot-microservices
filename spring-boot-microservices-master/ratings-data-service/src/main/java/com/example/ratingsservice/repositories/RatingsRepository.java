package com.example.ratingsservice.repositories;

import com.example.ratingsservice.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingsRepository extends JpaRepository<Rating, String> {
    List<Rating> findByUserId(String userId);

    @Query("SELECT r.movieId " +
            "FROM Rating r " +
            "GROUP BY r.movieId " +
            "ORDER BY AVG(r.rating) DESC " +
            "LIMIT 10")
    List<String> findTop10MovieIdsByAverageRating();
}
