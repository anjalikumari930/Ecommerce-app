package com.ecombackend.ecombackend.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecurityUtil {

    private static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Hash the password using BCrypt
    public static String hashPassword(String password) {
        try {
            return passwordEncoder.encode(password);
        } catch (Exception e) {
            logger.error("Error while hashing password: ", e);
            throw new RuntimeException("Failed to hash password");
        }
    }

    // Compare raw password with the hashed password
    public static boolean comparePassword(String rawPassword, String hashedPassword) {
        try {
            return passwordEncoder.matches(rawPassword, hashedPassword);
        } catch (Exception e) {
            logger.error("Error while comparing passwords: ", e);
            return false;
        }
    }
}
