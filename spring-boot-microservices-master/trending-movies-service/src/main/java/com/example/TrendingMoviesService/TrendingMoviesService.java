package com.example.TrendingMoviesService;

import com.example.TrendingMoviesService.service.trendingMoviesService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@EnableEurekaClient
//@EnableCircuitBreaker
@ComponentScan(basePackages = {"com.example.TrendingMoviesService.service"})
@Import(trendingMoviesService.class)
public class TrendingMoviesService {

//    private final int TIMEOUT = 3000;   // 3 seconds
//    @Bean
//    public RestTemplate getRestTemplate() {
//        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
//        clientHttpRequestFactory.setConnectTimeout(TIMEOUT);   // Set the timeout to 3 seconds
//        return new RestTemplate();
//    }
    public static void main(String[] args) {
        SpringApplication.run(TrendingMoviesService.class, args);
    }
}
