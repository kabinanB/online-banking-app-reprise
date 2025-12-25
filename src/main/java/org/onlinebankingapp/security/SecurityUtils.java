package org.onlinebankingapp.security;

import org.onlinebankingapp.entity.User;
import org.onlinebankingapp.repository.UserRepo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

    private static UserRepo userRepo;

    public SecurityUtils(UserRepo userRepo) {
        SecurityUtils.userRepo = userRepo;
    }

    public static User getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("No authenticated user found");
        }

        String username = authentication.getName();

        return userRepo.findByUsername(username)
                .orElseThrow(() ->
                        new IllegalStateException("User not found: " + username)
                );
    }
}
