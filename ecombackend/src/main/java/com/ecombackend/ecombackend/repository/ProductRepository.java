package com.ecombackend.ecombackend.repository;

import com.ecombackend.ecombackend.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findByCategorySlug(String categorySlug);

    // Find products by category's ID
    List<Product> findByCategoryId(String categoryId);

    // Find a product by its slug
    Optional<Product> findBySlug(String slug);

    // Search products by name containing keyword
    List<Product> findByNameContaining(String keyword);
}
