package com.example.chillflix.controllers;

import com.example.chillflix.models.Category;
import com.example.chillflix.models.Movie;
import com.example.chillflix.repository.CategoryRepository;
import com.example.chillflix.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/categories")//end point
public class CategoryController {
    @Autowired//connect to database
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<Category> getCategoriesOrderedByName(){
        return this.categoryRepository.findByOrderById();
    }

    @GetMapping("/{id}")
    public Category getCategory (@PathVariable Long id){
        return categoryRepository.getOne(id);
    }

    @PostMapping
    public Category addCategory(@RequestBody Category category) {
        Category existingCategory = categoryRepository.findOneByNameIgnoreCase(category.getName());
        if(existingCategory != null) {
            return existingCategory;
        }
        return categoryRepository.save(category);
    }
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public void updateCategory(@PathVariable("id") Long id,  @RequestBody Category category){
        category.setId(id);
        categoryRepository.save(category);
    }
}
