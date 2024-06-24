package com.vnpt.managementresource_backend.service.imple;

import com.vnpt.managementresource_backend.database.MogoFunc;
import com.vnpt.managementresource_backend.database.User;
import com.vnpt.managementresource_backend.models.mapper.Mapper;
import com.vnpt.managementresource_backend.payload.request.UserRequest;
import com.vnpt.managementresource_backend.respository.UserRespo;
import com.vnpt.managementresource_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserImp implements UserService {
    @Autowired
    UserRespo userRespo;
    @Autowired
    MogoFunc mogoFunc;
    @Override
    public User addUser(UserRequest request) {
        User newUser = new User();
        newUser.setId(mogoFunc.generateSequence("users_sequence"));
        Mapper.userMapper(newUser,request);
        userRespo.save(newUser);
        return newUser;
    }

    @Override
    public void removeUser(long id) {
        userRespo.deleteById(id);

    }

    @Override
    public User updateUser(User user) {
        Optional<User> existingUser = userRespo.findById(user.getId());
        if (existingUser.isPresent()) {

            return userRespo.save(user);
        }
        return null;
    }

    @Override
    public Optional<User> getUserById(long id) {
        return userRespo.findById(id);
    }

    @Override
    public List<User> getAllUser() {
        return userRespo.findAll();
    }

    @Override
    public List<User> findByIdIn(List<Long> ids) {
        return userRespo.findByIdIn(ids);
    }

    @Override
    public Optional<User> updateUnitUser(long idUser, long idUnit) {

        Optional<User> userOptional = userRespo.findById(idUser);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
//            user.setUnitId(idUnit);
            userRespo.save(user);
            return Optional.of(user);
        }
        return Optional.empty();
    }

    @Override
    public List<User> getAllUserByUnitId(long id) {
        return userRespo.findByUnitId(id);
    }

    @Override
    public boolean isEmailExist(String email) {
        return userRespo.existsByEmail(email);
    }





}