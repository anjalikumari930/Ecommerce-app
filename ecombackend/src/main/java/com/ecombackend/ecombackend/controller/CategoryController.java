package com.ecombackend.ecombackend.controller;

import com.ecombackend.ecombackend.dto.CategoryRequest;
import com.ecombackend.ecombackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Endpoint to create a new category (Admin only)
    @PostMapping("/create-category")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createCategory(@RequestBody CategoryRequest request) {
        return categoryService.createCategory(request);
    }

    // Endpoint to update an existing category by ID (Admin only)
    @PutMapping("/update-category/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateCategory(@PathVariable String id, @RequestBody CategoryRequest request) {
        return categoryService.updateCategory(id, request);
    }

    // Endpoint to fetch all categories (Open to all users)
    @GetMapping("/get-categories")
    public ResponseEntity<?> getCategories() {
        return categoryService.getCategories();
    }

    // Endpoint to fetch a single category by its slug (Open to all users)
    @GetMapping("/single-category/{slug}")
    public ResponseEntity<?> getSingleCategory(@PathVariable String slug) {
        return categoryService.getCategoryBySlug(slug);
    }

    // Endpoint to delete a category by ID (Admin only)
    @DeleteMapping("/delete-category/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteCategory(@PathVariable String id) {
        return categoryService.deleteCategory(id);
    }
}
