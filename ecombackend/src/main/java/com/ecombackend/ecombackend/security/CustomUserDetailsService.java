package com.ecombackend.ecombackend.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Implement your user lookup logic here
        // e.g., User user = userRepository.findByUsername(username);
        // return new
        // org.springframework.security.core.userdetails.User(user.getUsername(),
        // user.getPassword(), new ArrayList<>());
        return null; // Replace with actual implementation
    }
}
