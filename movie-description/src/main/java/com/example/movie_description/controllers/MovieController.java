package com.example.movie_description.controllers;

import com.example.movie_description.models.Movie;
import com.example.movie_description.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.Client;


import org.apache.http.HttpException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;

    @PostMapping
    public Movie addMovie(@RequestBody Movie movie) {
        try {
            Client client = new Client();
            String prompt = "Write a brief description for the movie titled: " + movie.getTitle() + ".";

            GenerateContentResponse response = client.models.generateContent("gemini-2.0-flash-001", prompt, null);
            String aiDescription = response.text();

            if (aiDescription == null || aiDescription.isEmpty()) {
                aiDescription = "Description not generated.";
            }
            movie.setDescription(aiDescription);

        } catch (HttpException | IOException e) {
            e.printStackTrace();
            movie.setDescription("Description for: " + movie.getTitle());
        }
        return movieRepository.save(movie);

    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

}
