package com.spring.studentmanagement.controllers;

import com.spring.studentmanagement.models.Category;
import com.spring.studentmanagement.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    // GET all categories
    @GetMapping()
    public ResponseEntity<List<Category>> getAllCategories() {
       return ResponseEntity.ok(categoryService.getAllCategories());
    }

    // GET category by ID
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    // POST new category
    @PostMapping()
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.addCategory(category));
    }

    // PUT category
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        return ResponseEntity.ok(categoryService.updateCategory(category, id));
    }

    // DELETE category
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
            categoryService.deleteCategoryById(id);
            return ResponseEntity.ok().build();
    }



}
