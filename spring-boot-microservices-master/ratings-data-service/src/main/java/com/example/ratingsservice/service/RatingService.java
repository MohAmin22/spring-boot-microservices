package com.example.ratingsservice.service;

import com.example.ratingsservice.models.Rating;
import com.example.ratingsservice.models.RatingId;
import com.example.ratingsservice.repositories.RatingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {
    @Autowired
    private RatingsRepository ratingsRepository;
    public List<Rating> getRatingsOfUser(String userId) {
        return ratingsRepository.findByRatingIdUserId(userId);
    }
}
