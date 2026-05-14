package com.spring.studentmanagement.services;

import com.spring.studentmanagement.models.Category;

import java.util.List;

public interface CategoryService {

    Category addCategory(Category category);

    Category getCategoryById(Long id);

    Category updateCategory(Category category, Long id);

    List<Category> getAllCategories();

    void deleteCategoryById(Long id);
}
