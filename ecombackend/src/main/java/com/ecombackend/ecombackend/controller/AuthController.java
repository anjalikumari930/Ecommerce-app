package com.ecombackend.ecombackend.controller;

import com.ecombackend.ecombackend.service.AuthService;
import com.ecombackend.ecombackend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.ecombackend.ecombackend.dto.LoginRequest;
import com.ecombackend.ecombackend.dto.RegisterRequest;
import com.ecombackend.ecombackend.dto.ForgotPasswordRequest;
import com.ecombackend.ecombackend.dto.UpdateProfileRequest;
import com.ecombackend.ecombackend.dto.OrderStatusRequest;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        return authService.forgotPassword(request);
    }

    @GetMapping("/test")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("Test route accessed");
    }

    @GetMapping("/user-auth")
    public ResponseEntity<?> userAuth() {
        return ResponseEntity.ok("User authenticated");
    }

    @GetMapping("/admin-auth")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> adminAuth() {
        return ResponseEntity.ok("Admin authenticated");
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(@RequestBody UpdateProfileRequest request) {
        return authService.updateProfile(request);
    }

    @GetMapping("/orders")
    public ResponseEntity<?> getOrders() {
        return authService.getOrders();
    }

    @GetMapping("/all-orders")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllOrders() {
        return authService.getAllOrders();
    }

    @PutMapping("/cancel-order/{orderId}")
    public ResponseEntity<?> cancelOrder(@PathVariable Long orderId) {
        return authService.cancelOrder(orderId);
    }

    @PutMapping("/order-status/{orderId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> orderStatus(@PathVariable Long orderId, @RequestBody OrderStatusRequest request) {
        return authService.updateOrderStatus(orderId, request);
    }
}
