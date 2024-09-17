package com.ecombackend.ecombackend.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // Constructor initializes the BCryptPasswordEncoder
    public PasswordUtil() {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    // Method to hash a plain-text password
    public String hashPassword(String password) {
        try {
            return bCryptPasswordEncoder.encode(password);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Consider logging this error and handling it properly in your application
        }
    }

    // Method to compare a raw password with a hashed password
    public boolean comparePassword(String password, String hashedPassword) {
        return bCryptPasswordEncoder.matches(password, hashedPassword);
    }
}
