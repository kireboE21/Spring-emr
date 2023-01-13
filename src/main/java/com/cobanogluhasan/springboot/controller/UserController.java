package com.cobanogluhasan.springboot.controller;


import com.cobanogluhasan.springboot.exception.ResourceNotFoundException;
import com.cobanogluhasan.springboot.controller.model.User;
import com.cobanogluhasan.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping( "/users")
public class UserController  {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //get all users
    @GetMapping("")
    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    //get user by id
    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return this.userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User not found with id: " + userId));
    }

    //create user
    @PostMapping("")
    public User createUser(@RequestBody User user) {
        return this.userRepository.save(user);
    }

    //update user
    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User user) throws NoSuchAlgorithmException {
        User existingUser = this.userRepository.findById(userId).orElseThrow(()->
                new ResourceNotFoundException("User not found with id: " + userId));

        existingUser.setEmail(user.getEmail());
        existingUser.setAddress(user.getAddress());
        existingUser.setGsm(user.getGsm());
        existingUser.setTckn(user.getTckn());
        existingUser.setPassword(user.getPassword());

        return this.userRepository.save(existingUser);
    }

    //delete user by id
    @DeleteMapping("/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable Long userId) {
        //get existing user from database
        User existingUser = this.userRepository.findById(userId).orElseThrow(()->
                new ResourceNotFoundException("User not found with id: " + userId));

        this.userRepository.delete(existingUser);
        return ResponseEntity.ok().build();
    }

}
