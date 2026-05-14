package com.spring.studentmanagement.services;

import com.spring.studentmanagement.exceptions.CategoryAlreadyExistsException;
import com.spring.studentmanagement.exceptions.CategoryNotFoundException;
import com.spring.studentmanagement.models.Category;
import com.spring.studentmanagement.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) {
        if (categoryRepository.existsByName(category.getName())) {
            throw new CategoryAlreadyExistsException("Category with name " + category.getName() + " already exists");
        }
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long id) {
       return categoryRepository.findById(id).orElseThrow( () -> new CategoryNotFoundException("Category with id " + id + " not found"));
    }

    @Override
    public Category updateCategory(Category category, Long id) {

        return categoryRepository.findById(id).map(existingCategory -> {


                    if (!existingCategory.getName().equals(category.getName())
                            && categoryRepository.existsByName(category.getName())) {
                        throw new CategoryAlreadyExistsException(
                                "Category with name " + category.getName() + " already exists");
                    }

                    existingCategory.setName(category.getName());
                    existingCategory.setDescription(category.getDescription());
                    existingCategory.setIconUrl(category.getIconUrl());
                    return categoryRepository.save(existingCategory);
                })
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Category with id " + id + " not found"));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteCategoryById(Long id) {
       if (!categoryRepository.existsById(id)) {
           throw new CategoryNotFoundException("Category with id " + id + " not found");
       }
       categoryRepository.deleteById(id);



    }
}
