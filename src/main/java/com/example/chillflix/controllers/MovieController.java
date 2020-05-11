package com.example.chillflix.controllers;

import com.example.chillflix.models.Category;
import com.example.chillflix.models.Movie;
import com.example.chillflix.repository.CategoryRepository;
import com.example.chillflix.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/movies")//end point
public class MovieController {
    @Autowired//connect to database
    private MovieRepository movieRepository;
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<Movie> getAllMoviesOrderByTitle(){
        return this.movieRepository.findByOrderByCategoryId();
    }

    @GetMapping("/{id}")
    public Movie getOneMovie(@PathVariable Long id){
        return this.movieRepository.getOne(id);
    }

    @GetMapping("/search/")
    public List<Movie> getMoviesByTitleOrYear (@RequestParam("title") String title){
        return movieRepository.findAllByTitleStartingWithIgnoreCase(title);
    }
    @GetMapping("/search/{year}")
    public List<Movie> getMoviesByTitleOrYear (@PathVariable Integer year){
        return movieRepository.findAllByYear(year);
    }

    @GetMapping("/category")
    public List<Movie> getMoviesByCategory(@RequestParam("id") Long id){

        return movieRepository.findAllByCategoryId(id);
    }

    @PostMapping
    public Movie addMovie(@RequestBody Movie movie) {
        Movie existingMovie = movieRepository.findOneByTitleIgnoreCase(movie.getTitle());
        if(existingMovie != null) {
            return existingMovie;
        }
        return movieRepository.save(movie);
    }
    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public void updateMovie(@PathVariable("id") Long id,  @RequestBody Movie movie){
        movie.setId(id);
        movieRepository.save(movie);
    }

    @PutMapping("/rate/{id}")
    public Movie rateMovie(@PathVariable("id") Long id,  @RequestBody Movie movie, @RequestParam("currentRate") Double currentRate){
        Double newRating = (movie.getRating() * movie.getRaterTotal() + currentRate) / (movie.getRaterTotal()+1);
        newRating = (int)(Math.round(newRating * 10))/10.0;
        movie.setRating(newRating);
        movie.setRaterTotal(movie.getRaterTotal() + 1);
        movie.setId(id);
       return movieRepository.save(movie);
    }
}
