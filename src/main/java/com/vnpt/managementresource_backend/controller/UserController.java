package com.vnpt.managementresource_backend.controller;

import com.vnpt.managementresource_backend.model.User;
import com.vnpt.managementresource_backend.payload.UserRequest;
import com.vnpt.managementresource_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping( "api/v1/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }
    @PostMapping("addUser")
    public void addUser(@RequestBody UserRequest request){
        userService.addUser(request);
    }
    @DeleteMapping("deleteUser/{id}")
    public void deleteUser(@PathVariable long id){
        userService.removeUser(id);
    }

    @PutMapping("updateUser")
    public ResponseEntity<User> updateUser( @RequestBody User user){
        Optional<User> existingUser = userService.getUserById(user.getId());
        if (existingUser.isPresent()) {
            User updatedUser = userService.updateUser(user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public Optional<User> getAllUser(@PathVariable long id){
        return userService.getUserById(id);
    }

}
