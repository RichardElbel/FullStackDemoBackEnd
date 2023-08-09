package com.ricky.fullstackbackend.controller;

import com.ricky.fullstackbackend.exception.UserNotFoundException;
import com.ricky.fullstackbackend.model.User;
import com.ricky.fullstackbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    @CrossOrigin
    User newUser(@RequestBody User newUser){
        return userRepository.save(newUser);
    }

    @GetMapping("/users")
    @CrossOrigin("http://localhost:3000")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
        User getUserById(@PathVariable Long id){
            return userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));

        }

        @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser,@PathVariable Long id){

        return userRepository.findById(id)
                .map(user->{
                    user.setUsername(newUser.getUsername());
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);
                }).orElseThrow(()-> new UserNotFoundException(id));
        }
}

