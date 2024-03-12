package com.example.ratingsservice.service;

import com.example.ratingsservice.models.Rating;
import com.example.ratingsservice.repositories.RatingsRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class RatingService {
    @Autowired
    private RatingsRepository ratingsRepository;
    public List<Rating> getRatingsOfUser(String userId) {
        return ratingsRepository.findByUserId(userId);
    }

}
