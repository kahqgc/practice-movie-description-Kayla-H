package com.example.movie_description;

import java.io.IOException;
import org.apache.http.HttpException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MovieDescriptionApplication {

	public static void main(String[] args) throws IOException, HttpException {
		SpringApplication.run(MovieDescriptionApplication.class, args);
	}
}

