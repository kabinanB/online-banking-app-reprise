package org.onlinebankingapp.controller;


import org.onlinebankingapp.entity.Transaction;
import org.onlinebankingapp.entity.User;
import org.onlinebankingapp.repository.AccountRepo;
import org.onlinebankingapp.repository.TransactionRepo;
import org.onlinebankingapp.repository.UserRepo;
import org.onlinebankingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> allUsers(){

        List<User> users = userService.getAllUsers();

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable("id") Long id){
        Optional<User> user = userService.getUser(id);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping("/users/add")
    public ResponseEntity<Optional<User>> addNewUser(@RequestBody User user){
        Optional<User> newUser = userService.createUser(user);

        return new ResponseEntity<Optional<User>>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Optional<User>> updateUser(@PathVariable("id") Long id, @RequestBody User user){
        Optional<User> updatedUser = userService.createUser(user);

        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") Long id) {
        Boolean deletedUser = userService.deleteUser(id);

        return new ResponseEntity<Boolean>(deletedUser, HttpStatus.NO_CONTENT);


    }








}
