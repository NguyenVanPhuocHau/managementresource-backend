package com.vnpt.managementresource_backend.model.mapper;

import com.vnpt.managementresource_backend.model.User;
import com.vnpt.managementresource_backend.payload.UserRequest;

public class Mapper {
    public static  void Usermapper(User user, UserRequest userRequest){
        user.setFullName(userRequest.getFullName());
        user.setEmail(userRequest.getEmail());
        user.setRole(user.getRole());
    }
}
