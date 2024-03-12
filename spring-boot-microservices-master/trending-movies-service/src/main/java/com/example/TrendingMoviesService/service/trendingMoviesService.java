package com.example.TrendingMoviesService.service;


import com.example.TrendingMoviesService.Repositories.TrendingMoviesRepository;
import com.example.TrendingMoviesService.grpcApi.*;
import com.example.TrendingMoviesService.models.Movie;
import io.grpc.stub.StreamObserver;
//import net.devh.boot.grpc.client.inject.GrpcClient;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class trendingMoviesService extends TrendingToRatingDataGrpc.TrendingToRatingDataImplBase {
//    @Override
//    public void getTrendingMovies(Empty request, StreamObserver<StringList> responseObserver) {
//        super.getTrendingMovies(request, responseObserver);
//    }
//    public void getTrendingMovies(Empty request, StreamObserver<StringList> responseObserver) {
//        // Get the top 10 movies by rating
//        List<Movie> topMovies = trendingMoviesRepository.findTop10ByOrderByRatingDesc();
//
//        // Convert Movie objects to strings
//        List<String> movieStrings = topMovies.stream()
//                .map(Movie::toString)
//                .collect(Collectors.toList());
//
//        // Build StringList response
//        StringList stringListResponse = StringList.newBuilder()
//                .addAllMovies(movieStrings)
//                .build();
//
//        // Send the response to the client
//        responseObserver.onNext(stringListResponse);
//        responseObserver.onCompleted();
//    }
//    @GrpcClient("MovieInfo")
//    TrendingToMovieInfoGrpc.TrendingToMovieInfoBlockingStub MovieInfoStub;
//    @GrpcClient("Rating")
////    TrendingToRatingDataGrpc.TrendingToRatingDataBlockingStub RatingStub;
//    public MovieList getMovieListNamesFromIDs(MovieIdList movieIdList){
//        return MovieInfoStub.getMovieInfo(movieIdList);
//    }
//    public MovieIdList getMovieIds(){
//        return RatingStub.getTop10(Empty.newBuilder().build());
//    }


    @Autowired
    private TrendingMoviesRepository trendingMoviesRepository;

    @Override
    public void getTrendingMovies(Empty request, StreamObserver<StringList> responseObserver) {

        // Get the top 10 movies by rating
        List<Movie> topMovies = trendingMoviesRepository.findTop10ByOrderByRatingDesc();

        // Convert Movie objects to strings
        List<String> movieStrings = topMovies.stream()
                .map(Movie::toString)
                .collect(Collectors.toList());

        // Build StringList response
        StringList stringListResponse = StringList.newBuilder()
                .addAllMovies(movieStrings)
                .build();

        // Send the response to the client
        responseObserver.onNext(stringListResponse);
        responseObserver.onCompleted();
    }
}
