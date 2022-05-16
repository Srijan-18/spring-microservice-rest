package com.practice.spring.microservice.user.controller;

import com.practice.spring.microservice.exception.UserNotFoundException;
import com.practice.spring.microservice.user.dao.User;
import com.practice.spring.microservice.user.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
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
    //retrieve one user -> get/user/{id} (HATEOAS)
    @GetMapping(path = "/users/hateoas/{id}")
    public EntityModel<User> retrieveAUserHATEOAS(@PathVariable int id) {
        User user = service.findOne(id);

        if(user == null){
            throw new UserNotFoundException(id);
        }
        EntityModel<User> userEntityModel = EntityModel.of(user);
        WebMvcLinkBuilder allUsersLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        WebMvcLinkBuilder deleteUserLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).deleteUser(0));
        userEntityModel.add(allUsersLink.withRel("to-get-all-users"));
        userEntityModel.add(deleteUserLink.withRel("to-delete-user-with-id<0>"));

        return userEntityModel;
    }

    //create a user ->post

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user){
        User savedUser = service.save(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(uri).build();

    }

    //delete a user -> delete

    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity deleteUser(@PathVariable int id) {
        User user = service.deleteById(id);
        if(user == null){
            throw new UserNotFoundException(id);
        }
        return ResponseEntity.ok().build();
    }
}
