package com.ecombackend.ecombackend.service;

import com.ecombackend.ecombackend.dto.BraintreePaymentRequest;
import com.ecombackend.ecombackend.dto.ProductFilterRequest;
import com.ecombackend.ecombackend.entity.Product;
import com.ecombackend.ecombackend.repository.ProductRepository;
import com.ecombackend.ecombackend.util.BraintreeUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BraintreeUtil braintreeUtil;

    @Autowired
    private ObjectMapper objectMapper;

    // Create a new product
    public ResponseEntity<?> createProduct(MultipartFile file, String productJson) {
        try {
            Product product = parseProductFromJson(productJson);
            Product savedProduct = productRepository.save(product);

            if (file != null && !file.isEmpty()) {
                saveProductImage(file, savedProduct.getId());
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing file");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating product");
        }
    }

    public ResponseEntity<?> getProducts() {
        return getProducts(0); // Default to the first page
    }

    // Update an existing product
    public ResponseEntity<?> updateProduct(String pid, MultipartFile file, String productJson) {
        try {
            Optional<Product> existingProductOpt = productRepository.findById(pid);
            if (existingProductOpt.isPresent()) {
                Product existingProduct = existingProductOpt.get();
                Product updatedProduct = parseProductFromJson(productJson);

                // Update fields
                existingProduct.setName(updatedProduct.getName());
                existingProduct.setDescription(updatedProduct.getDescription());
                existingProduct.setPrice(updatedProduct.getPrice());
                existingProduct.setQuantity(updatedProduct.getQuantity());
                existingProduct.setCategory(updatedProduct.getCategory());
                existingProduct.setShipping(updatedProduct.getShipping());

                Product savedProduct = productRepository.save(existingProduct);

                if (file != null && !file.isEmpty()) {
                    saveProductImage(file, savedProduct.getId());
                }

                return ResponseEntity.ok(savedProduct);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing file");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating product");
        }
    }

    // Get all products
    public ResponseEntity<?> getProducts(int page) {
        Pageable pageable = PageRequest.of(page, 10); // 10 products per page
        Page<Product> productPage = productRepository.findAll(pageable);
        return ResponseEntity.ok(productPage);
    }

    // Get product by slug
    public ResponseEntity<?> getProductBySlug(String slug) {
        Optional<Product> productOpt = productRepository.findBySlug(slug);
        if (productOpt.isPresent()) {
            return ResponseEntity.ok(productOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
    }

    // Get product photo by product ID
    public ResponseEntity<?> getProductPhoto(String pid) {
        Optional<Product> productOpt = productRepository.findById(pid);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            return ResponseEntity.ok(product.getPhoto());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
    }

    // Delete a product by ID
    public ResponseEntity<?> deleteProduct(String pid) {
        if (productRepository.existsById(pid)) {
            productRepository.deleteById(pid);
            return ResponseEntity.ok("Product deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
    }

    // Filter products based on criteria
    public ResponseEntity<?> filterProducts(ProductFilterRequest request) {
        // Implement filtering logic (e.g., using Specification or QueryDSL)
        List<Product> filteredProducts = productRepository.findAll(); // Placeholder, replace with actual filtering
                                                                      // logic
        return ResponseEntity.ok(filteredProducts);
    }

    // Get product count
    public ResponseEntity<?> getProductCount() {
        long count = productRepository.count();
        return ResponseEntity.ok(count);
    }

    // Search products by keyword
    public ResponseEntity<?> searchProduct(String keyword) {
        // Implement search logic
        List<Product> products = productRepository.findAll(); // Placeholder, replace with actual search logic
        return ResponseEntity.ok(products);
    }

    // Get related products by product ID and category ID
    public ResponseEntity<?> getRelatedProduct(String pid, String cid) {
        // Implement logic to find related products
        List<Product> relatedProducts = productRepository.findByCategoryId(cid); // Placeholder, replace with actual
                                                                                 // logic
        return ResponseEntity.ok(relatedProducts);
    }

    // Get products by category slug
    public ResponseEntity<?> getProductByCategory(String slug) {
        List<Product> products = productRepository.findByCategorySlug(slug); // Ensure this method exists in
                                                                             // ProductRepository
        return ResponseEntity.ok(products);
    }

    // Generate Braintree token for payments
    public ResponseEntity<?> getBraintreeToken() {
        String token = braintreeUtil.generateToken();
        return ResponseEntity.ok(token);
    }

    // Process Braintree payment
    public ResponseEntity<?> braintreePayment(BraintreePaymentRequest request) {
        try {
            String result = braintreeUtil.processPayment(request);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Payment processing failed");
        }
    }

    // Helper method to parse product JSON into a Product object
    private Product parseProductFromJson(String productJson) throws IOException {
        return objectMapper.readValue(productJson, Product.class);
    }

    // Helper method to save product image
    private void saveProductImage(MultipartFile file, String productId) throws IOException {
        String uploadDir = "uploads/products/" + productId;
        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs();
        }

        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        File destinationFile = new File(uploadDirFile, fileName);
        file.transferTo(destinationFile);

        // Optionally, save the image path in the product record (e.g.,
        // product.setPhoto(fileName))
    }
}
