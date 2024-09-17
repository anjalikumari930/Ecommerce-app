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

    @PostMapping("/create-product")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createProduct(@RequestParam("file") MultipartFile file,
            @RequestParam("product") String product) {
        return productService.createProduct(file, product);
    }

    @PutMapping("/update-product/{pid}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateProduct(@PathVariable String pid, @RequestParam("file") MultipartFile file,
            @RequestParam("product") String product) {
        return productService.updateProduct(pid, file, product);
    }

    @GetMapping("/get-product")
    public ResponseEntity<?> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/get-product/{slug}")
    public ResponseEntity<?> getSingleProduct(@PathVariable String slug) {
        return productService.getProductBySlug(slug);
    }

    @GetMapping("/product-photo/{pid}")
    public ResponseEntity<?> getProductPhoto(@PathVariable String pid) {
        return productService.getProductPhoto(pid);
    }

    @DeleteMapping("/delete-product/{pid}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteProduct(@PathVariable String pid) {
        return productService.deleteProduct(pid);
    }

    @PostMapping("/product-filters")
    public ResponseEntity<?> filterProducts(@RequestBody ProductFilterRequest request) {
        return productService.filterProducts(request);
    }

    @GetMapping("/product-count")
    public ResponseEntity<?> getProductCount() {
        return productService.getProductCount();
    }

    @GetMapping("/product-list/{page}")
    public ResponseEntity<?> getProducts(@PathVariable int page) {
        return productService.getProducts(page);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<?> searchProduct(@PathVariable String keyword) {
        return productService.searchProduct(keyword);
    }

    @GetMapping("/related-product/{pid}/{cid}")
    public ResponseEntity<?> getRelatedProduct(@PathVariable String pid, @PathVariable String cid) {
        return productService.getRelatedProduct(pid, cid);
    }

    @GetMapping("/product-category/{slug}")
    public ResponseEntity<?> getProductByCategory(@PathVariable String slug) {
        return productService.getProductByCategory(slug);
    }

    @GetMapping("/braintree/token")
    public ResponseEntity<?> getBraintreeToken() {
        return productService.getBraintreeToken();
    }

    @PostMapping("/braintree/payment")
    public ResponseEntity<?> braintreePayment(@RequestBody BraintreePaymentRequest request) {
        return productService.braintreePayment(request);
    }
}
