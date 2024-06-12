package com.vnpt.managementresource_backend.Controller;

import com.vnpt.managementresource_backend.Database.User;
import com.vnpt.managementresource_backend.Respository.UserPepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserPepo userPepo;
    @PostMapping("/addUser")
    public void addUser(@RequestBody User user){
        userPepo.save(user);
    }
}
