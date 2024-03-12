package com.example.TrendingMoviesService.service;


import com.example.TrendingMoviesService.Repositories.TrendingMoviesRepository;
import com.example.TrendingMoviesService.grpcApi.*;
import com.example.TrendingMoviesService.models.Movie;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class trendingMoviesService extends TrendingToRatingDataGrpc.TrendingToRatingDataImplBase {

    @Autowired
    private TrendingMoviesRepository trendingMoviesRepository;

    @Override
    public void getTrendingMovies(Empty request, StreamObserver<StringList> responseObserver) {
        List<Movie> topMovies = trendingMoviesRepository.findTop10ByOrderByRatingDesc();


        ObjectMapper objectMapper = new ObjectMapper();
        List<String> movieStrings = topMovies.stream()
                .map(
                        movie -> {
                            try {
                                return objectMapper.writeValueAsString(movie).toString();
                            } catch (Exception e) {
                                return "";
                            }
                        }

                )
                .collect(Collectors.toList());
        StringList stringListResponse = StringList.newBuilder()
                .addAllMovies(movieStrings)
                .build();
        responseObserver.onNext(stringListResponse);
        responseObserver.onCompleted();
    }
}
