package com.vnpt.managementresource_backend.service;

import com.vnpt.managementresource_backend.database.User;
import com.vnpt.managementresource_backend.payload.request.UserRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    User addUser(UserRequest request);

    void removeUser(long id);

    User updateUser(User user);

    Optional<User> getUserById(long id);

    List<User> getAllUser();

    List<User> findByIdIn(List<Long> ids);

    Optional<User> updateUnitUser(long idUser, long idUnit);

    List<User> getAllUserByUnitId(long id);

    boolean isEmailExist(String email);


}
