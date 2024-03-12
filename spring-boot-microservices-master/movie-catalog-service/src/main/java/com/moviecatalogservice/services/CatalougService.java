package com.moviecatalogservice.services;

import com.example.TrendingMoviesService.grpcApi.CatalogToTrendingServiceGrpc;
import com.example.TrendingMoviesService.grpcApi.Empty;
import com.example.TrendingMoviesService.grpcApi.MovieList;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalougService {
    @GrpcClient("Top10")
    CatalogToTrendingServiceGrpc.CatalogToTrendingServiceBlockingStub stub;
    public MovieList getTop10(){
        return stub.getTrendingMovies(Empty.newBuilder().build());
    }
}
