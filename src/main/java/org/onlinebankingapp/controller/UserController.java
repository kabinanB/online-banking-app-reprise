package org.onlinebankingapp.controller;

import org.onlinebankingapp.dto.PasswordChangeRequest;
import org.onlinebankingapp.entity.User;
import org.onlinebankingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Self-signup endpoint
    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody User user) {
        // Assign default role USER
        User newUser = userService.createUserWithRole(user, "USER");
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    // Get current logged-in user info
    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser() {
        User current = userService.getCurrentAuthenticatedUser();
        return ResponseEntity.ok(current);
    }

    // Update profile for current user
    @PutMapping("/me")
    public ResponseEntity<User> updateCurrentUser(@RequestBody User user) {
        User updated = userService.updateCurrentAuthenticatedUser(user);
        return ResponseEntity.ok(updated);
    }

    // Change password
    @PostMapping("/me/password")
    public ResponseEntity<Void> changePassword(@RequestBody PasswordChangeRequest request) {
        userService.changePassword(request.getOldPassword(), request.getNewPassword());
        return ResponseEntity.ok().build();
    }
}

