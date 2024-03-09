package com.example.movieinfoservice.repository;

import com.example.movieinfoservice.models.MovieSummary;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MovieSummaryRepository  extends MongoRepository<MovieSummary, String> {

    Optional<MovieSummary> findById(String id);
}
