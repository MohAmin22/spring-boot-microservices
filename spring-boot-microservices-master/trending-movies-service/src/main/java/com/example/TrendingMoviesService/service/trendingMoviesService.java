package com.example.TrendingMoviesService.service;


import com.example.TrendingMoviesService.grpcApi.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class trendingMoviesService extends CatalogToTrendingServiceGrpc.CatalogToTrendingServiceImplBase{
    @GrpcClient("MovieInfo")
    TrendingToMovieInfoGrpc.TrendingToMovieInfoBlockingStub MovieInfoStub;
    @GrpcClient("Rating")
    TrendingToRatingDataGrpc.TrendingToRatingDataBlockingStub RatingStub;
    public MovieList getMovieListNamesFromIDs(MovieIdList movieIdList){
        return MovieInfoStub.getMovieInfo(movieIdList);
    }
    public MovieIdList getMovieIds(){
        return RatingStub.getTop10(Empty.newBuilder().build());
    }

    @Override
    public void getTrendingMovies(Empty request, StreamObserver<MovieList> responseObserver) {

    }
}
