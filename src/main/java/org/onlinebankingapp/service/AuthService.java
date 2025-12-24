package org.onlinebankingapp.service;

import org.onlinebankingapp.dto.RegisterRequest;
import org.onlinebankingapp.entity.User;
import org.onlinebankingapp.repository.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterRequest request) {

        if (userRepo.findByUsername(request.getUsername()).isPresent()) {
            throw new IllegalStateException("Username already exists");
        }

        User user = new User(
                request.getUsername(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword())
        );

        userRepo.save(user);
    }
}
