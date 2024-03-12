package com.example.TrendingMoviesService.Repositories;

import com.example.TrendingMoviesService.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@RequestMapping
public interface TrendingMoviesRepository extends JpaRepository<Movie, Long> {
    @Query("SELECT m FROM Movie m ORDER BY m.Rating DESC")
    List<Movie> findTop10ByOrderByRatingDesc();
}
