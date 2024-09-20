package com.ecombackend.ecombackend.repository;

import com.ecombackend.ecombackend.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    // Find user by email
    Optional<User> findByEmail(String email);

    // Check if user exists by email
    boolean existsByEmail(String email);

}
