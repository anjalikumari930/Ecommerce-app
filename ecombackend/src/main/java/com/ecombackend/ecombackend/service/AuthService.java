package com.ecombackend.ecombackend.service;

import com.ecombackend.ecombackend.dto.*;
import com.ecombackend.ecombackend.entity.User;
import com.ecombackend.ecombackend.repository.UserRepository;
import com.ecombackend.ecombackend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    /**
     * Registers a new user.
     */
    public ResponseEntity<?> register(RegisterRequest request) {
        // Check if user with the same email already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already exists.");
        }

        // Create a new user and encode the password
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(User.UserRole.USER);

        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully.");
    }

    /**
     * Logs in the user and generates a JWT token.
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

        // Load UserDetails using the UserDetailsService
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());

        // Generate JWT token using UserDetails
        // String token = jwtUtil.generateToken(userDetails);

        // Return token in response body
        JwtResponse jwtResponse = new JwtResponse(user.getId(), user.getEmail(), user.getRole().name());

        return ResponseEntity.ok(jwtResponse);

    }

    /**
     * Handles forgot password functionality.
     */
    public ResponseEntity<?> forgotPassword(ForgotPasswordRequest request) {
        // Implement logic to handle forgot password
        // For example, send a reset password email with a unique token
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
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

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
