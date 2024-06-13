package com.vnpt.managementresource_backend.service;

import com.vnpt.managementresource_backend.model.User;
import com.vnpt.managementresource_backend.payload.UserRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    void addUser(UserRequest request);
    void removeUser(long id);

    User updateUser(long id, UserRequest userRequest);

    Optional<User> getUserById(long id);

    List<User> getAllUser();
}
