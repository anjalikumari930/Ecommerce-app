package com.ecombackend.ecombackend.service;

import com.ecombackend.ecombackend.dto.*;
import com.ecombackend.ecombackend.entity.User;
import com.ecombackend.ecombackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    /**
     * Registers a new user.
     */
    public ResponseEntity<String> register(RegisterRequest request) {
        // Check if user with the same email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already registered.");
        }

        // Create a new user and encode the password
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        user.setAnswer(request.getAnswer());

        // Role is automatically set to 0 by default in the User entity

        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully.");
    }

    /**
     * Logs in the user and returns basic user info.
     */
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // Check if user exists by email
        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password.");
        }

        User user = optionalUser.get();

        // Check if password matches
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password.");
        }

        // Return basic user information after successful login
        UserResponse userResponse = new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getPhone(),
                user.getAddress(), String.valueOf(user.getRole()));

        return ResponseEntity.ok(userResponse);
    }

    /**
     * Handles forgot password functionality.
     */
    public ResponseEntity<?> forgotPassword(ForgotPasswordRequest request) {
        // Implement logic to handle forgot password
        return ResponseEntity.ok("Forgot password feature is under construction.");
    }

    /**
     * Updates user profile.
     */
    public ResponseEntity<?> updateProfile(UpdateProfileRequest request) {
        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
        if (optionalUser.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found.");
        }

        User user = optionalUser.get();
        // Update user profile details
        user.setName(request.getName());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());

        userRepository.save(user);

        return ResponseEntity.ok("Profile updated successfully.");
    }

    /**
     * Fetches orders for the logged-in user.
     */
    public ResponseEntity<?> getOrders() {
        // Implement logic to fetch user-specific orders
        return ResponseEntity.ok("Fetching orders for the user is under construction.");
    }

    /**
     * Fetches all orders (admin functionality).
     */
    public ResponseEntity<?> getAllOrders() {
        // Implement logic to fetch all orders (for admin users)
        return ResponseEntity.ok("Fetching all orders is under construction.");
    }

    /**
     * Cancels a specific order by ID.
     */
    public ResponseEntity<?> cancelOrder(Long orderId) {
        // Implement logic to cancel the order by ID
        return ResponseEntity.ok("Canceling the order is under construction.");
    }

    /**
     * Updates the status of a specific order.
     */
    public ResponseEntity<?> updateOrderStatus(Long orderId, OrderStatusRequest request) {
        // Implement logic to update the order status (e.g., 'shipped', 'delivered',
        // etc.)
        return ResponseEntity.ok("Updating order status is under construction.");
    }
}
