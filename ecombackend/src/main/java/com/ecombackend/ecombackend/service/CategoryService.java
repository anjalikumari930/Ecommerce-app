package com.ecombackend.ecombackend.service;

import com.ecombackend.ecombackend.dto.CategoryRequest;
import com.ecombackend.ecombackend.entity.Category;
import com.ecombackend.ecombackend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Create a new category
    public ResponseEntity<?> createCategory(CategoryRequest request) {
        if (categoryRepository.existsBySlug(request.getSlug())) {
            return ResponseEntity.badRequest().body("Category with the same slug already exists.");
        }

        Category category = new Category();
        category.setName(request.getName());
        category.setSlug(request.getSlug());
        category.setDescription(request.getDescription());

        categoryRepository.save(category);

        return ResponseEntity.ok("Category created successfully.");
    }

    // Update an existing category
    public ResponseEntity<?> updateCategory(String id, CategoryRequest request) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);

        if (!categoryOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Category not found.");
        }

        Category category = categoryOptional.get();
        category.setName(request.getName());
        category.setSlug(request.getSlug());
        category.setDescription(request.getDescription());

        categoryRepository.save(category);

        return ResponseEntity.ok("Category updated successfully.");
    }

    // Get all categories
    public ResponseEntity<?> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        return ResponseEntity.ok(categories);
    }

    // Get a single category by slug
    public ResponseEntity<?> getCategoryBySlug(String slug) {
        Optional<Category> categoryOptional = categoryRepository.findBySlug(slug);

        if (!categoryOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Category not found.");
        }

        return ResponseEntity.ok(categoryOptional.get());
    }

    // Delete a category by ID
    public ResponseEntity<?> deleteCategory(String id) {
        if (!categoryRepository.existsById(id)) {
            return ResponseEntity.badRequest().body("Category not found.");
        }

        categoryRepository.deleteById(id);
        return ResponseEntity.ok("Category deleted successfully.");
    }
}
