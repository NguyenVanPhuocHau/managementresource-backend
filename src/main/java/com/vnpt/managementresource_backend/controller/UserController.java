package com.vnpt.managementresource_backend.controller;

import com.vnpt.managementresource_backend.database.User;
import com.vnpt.managementresource_backend.payload.request.ChangeUnitOfUnitRequest;
import com.vnpt.managementresource_backend.payload.request.UserRequest;
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
//    @PostMapping("addUser")
//    public User addUser(@RequestBody UserRequest request){
//               return userService.addUser(request);
//    }

    @PostMapping("addUser")
    public ResponseEntity<?> createUser(@RequestBody UserRequest request) {
        try {

            if (userService.isEmailExist(request.getEmail())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists");
            }
            User createdUser = userService.addUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create user: " + e.getMessage());
        }
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

    @PostMapping("/getByIds")
    public List<User> getUsersByIds(@RequestBody List<Long> ids) {
        return userService.findByIdIn(ids);
    }
    @GetMapping("/units/{id}")
    public List<User> getUsersByIds(@PathVariable long id) {
        return userService.getAllUserByUnitId(id);
    }

    @PutMapping("/changeUnit")
    public ResponseEntity<User> updateUserUnitId(@RequestBody ChangeUnitOfUnitRequest changeUnitOfUnitRequest) {
        Optional<User> updatedUser = userService.updateUnitUser(changeUnitOfUnitRequest.getIdUser(), changeUnitOfUnitRequest.getIdUnit());
        if (updatedUser.isPresent()) {
            return ResponseEntity.ok(updatedUser.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/check-email")
    public ResponseEntity<String> checkEmailExists(@RequestParam String email) {
        if (userService.isEmailExist(email)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email đã được đăng ký");
        }
        return ResponseEntity.ok("Email có thể sử dụng");
    }
}
