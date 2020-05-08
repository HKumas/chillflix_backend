package com.example.chillflix.repository;

import com.example.chillflix.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByNameIgnoreCase(String name);
    Category findOneByNameIgnoreCase(String name);
}
