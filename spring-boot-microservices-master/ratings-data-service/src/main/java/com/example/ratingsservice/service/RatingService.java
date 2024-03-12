package com.example.ratingsservice.service;

import com.example.TrendingMoviesService.grpcApi.Empty;
import com.example.TrendingMoviesService.grpcApi.MovieIdList;
import com.example.TrendingMoviesService.grpcApi.TrendingToRatingDataGrpc;
import com.example.ratingsservice.models.Rating;
import com.example.ratingsservice.repositories.RatingsRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class RatingService extends TrendingToRatingDataGrpc.TrendingToRatingDataImplBase {
    @Autowired
    private RatingsRepository ratingsRepository;
    public List<Rating> getRatingsOfUser(String userId) {
        return ratingsRepository.findByUserId(userId);
    }

    @Override
    public void getTop10(Empty request, StreamObserver<MovieIdList> responseObserver) {
        // Get the top 10 movie IDs from the repository
        List<String> movieIdsAsStrings = ratingsRepository.findTop10MovieIdsByAverageRating();

        // Convert movie IDs from String to int
        List<Integer> movieIds = movieIdsAsStrings.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        // Create a MovieIdList response
        MovieIdList.Builder movieIdListBuilder = MovieIdList.newBuilder();
        for (Integer movieId : movieIds) {
            movieIdListBuilder.addMovieId(movieId);
        }

        // Build and send the response to the client
        responseObserver.onNext(movieIdListBuilder.build());
        responseObserver.onCompleted();
    }

}
