package com.practice.spring.microservice.user;

import com.practice.spring.microservice.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDaoService service;

    //retrieve all users -> get/users
    @GetMapping(path = "/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    //retrieve one user -> get/user/{id}
    @GetMapping(path = "/users/{id}")
    public User retrieveAUser(@PathVariable int id) {
        User user = service.findOne(id);

        if(user == null){
            throw new UserNotFoundException(id);
        }
        return user;
    }

    //create a user ->post

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user){
        User savedUser = service.save(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(uri).build();

    }
}
