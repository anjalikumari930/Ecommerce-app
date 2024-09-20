package com.ecombackend.ecombackend.controller;

import com.ecombackend.ecombackend.dto.BraintreePaymentRequest;
import com.ecombackend.ecombackend.dto.ProductFilterRequest;
import com.ecombackend.ecombackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Retrieve all products with pagination

    // Create a new product (Admin only)
    @PostMapping("/create-product")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createProduct(@RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam("product") String product) {
        return productService.createProduct(file, product);
    }

    // Update an existing product (Admin only)
    @PutMapping("/update-product/{pid}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateProduct(@PathVariable String pid,
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam("product") String product) {
        return productService.updateProduct(pid, file, product);
    }

    // Get product by slug
    @GetMapping("/get-product/{slug}")
    public ResponseEntity<?> getSingleProduct(@PathVariable String slug) {
        return productService.getProductBySlug(slug);
    }

    // Retrieve product image by product ID
    @GetMapping("/product-photo/{pid}")
    public ResponseEntity<?> getProductPhoto(@PathVariable String pid) {
        return productService.getProductPhoto(pid);
    }

    // Delete a product by ID (Admin only)
    @DeleteMapping("/delete-product/{pid}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteProduct(@PathVariable String pid) {
        return productService.deleteProduct(pid);
    }

    // Filter products based on criteria
    @PostMapping("/product-filters")
    public ResponseEntity<?> filterProducts(@RequestBody ProductFilterRequest request) {
        return productService.filterProducts(request);
    }

    // Get total product count
    @GetMapping("/product-count")
    public ResponseEntity<?> getProductCount() {
        return productService.getProductCount();
    }

    // Get paginated list of products
    @GetMapping("/product-list")
    public ResponseEntity<?> getAllProducts(
            @RequestParam(defaultValue = "0") int page, // Default page number is 0
            @RequestParam(defaultValue = "10") int size // Default size is 10 products per page
    ) {
        return productService.getAllProducts(page, size);
    }

    // Search for products by keyword
    @GetMapping("/search/{keyword}")
    public ResponseEntity<?> searchProduct(@PathVariable String keyword) {
        return productService.searchProduct(keyword);
    }

    // Get related products by product ID and category ID
    @GetMapping("/related-product/{pid}/{cid}")
    public ResponseEntity<?> getRelatedProduct(@PathVariable String pid, @PathVariable String cid) {
        return productService.getRelatedProduct(pid, cid);
    }

    // Get products by category slug
    @GetMapping("/product-category/{slug}")
    public ResponseEntity<?> getProductByCategory(@PathVariable String slug) {
        return productService.getProductByCategory(slug);
    }

    // Get Braintree token for payment
    @GetMapping("/braintree/token")
    public ResponseEntity<?> getBraintreeToken() {
        return productService.getBraintreeToken();
    }

    // Process Braintree payment
    @PostMapping("/braintree/payment")
    public ResponseEntity<?> braintreePayment(@RequestBody BraintreePaymentRequest request) {
        return productService.braintreePayment(request);
    }
}
