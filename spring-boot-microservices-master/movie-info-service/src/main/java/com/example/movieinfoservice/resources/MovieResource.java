package com.example.movieinfoservice.resources;

import com.example.movieinfoservice.models.Movie;
import com.example.movieinfoservice.models.MovieSummary;
import com.example.movieinfoservice.repository.MovieSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @Value("${api.key}")
    private String apiKey;

    private RestTemplate restTemplate;

    @Autowired
    private final MovieSummaryRepository movieSummaryRepository;

    public MovieResource(RestTemplate restTemplate, MovieSummaryRepository movieSummaryRepository) {
        this.restTemplate = restTemplate;
        this.movieSummaryRepository = movieSummaryRepository;
    }

    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {

        // Check if the movie info is already in the database
        if (movieSummaryRepository.findById(movieId).isPresent()) {
            MovieSummary movieSummary = movieSummaryRepository.findById(movieId).get();
            return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
        }

        // Get the movie info from TMDB
        final String url = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey;
        MovieSummary movieSummary = restTemplate.getForObject(url, MovieSummary.class);

        // Save the movie info to the database
        movieSummaryRepository.save(movieSummary);

        return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
    }
}
