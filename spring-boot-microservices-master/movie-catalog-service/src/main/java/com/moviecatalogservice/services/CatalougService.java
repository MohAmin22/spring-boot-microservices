package com.moviecatalogservice.services;

import com.example.TrendingMoviesService.grpcApi.*;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatalougService {
    @GrpcClient("TrendingToRatingData")
    TrendingToRatingDataGrpc.TrendingToRatingDataBlockingStub stub;
    public List<String> getTop10() {
        // Making a gRPC call to get the trending movies
        StringList stringList = stub.getTrendingMovies(Empty.getDefaultInstance());
        return stringList.getMoviesList();
    }
}
