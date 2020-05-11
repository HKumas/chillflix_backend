package com.example.chillflix.repository;

import com.example.chillflix.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findAllByTitleStartingWithIgnoreCase(String title);
    List<Movie> findAllByYear(Integer year);
    Movie findOneByTitleIgnoreCase(String title);
    List<Movie> findAllByCategoryId(Long id);
    List<Movie> findByOrderByCategoryId();
}
