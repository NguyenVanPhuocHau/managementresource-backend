package com.vnpt.managementresource_backend.service.imp;

import com.vnpt.managementresource_backend.model.DatabaseSequence;
import com.vnpt.managementresource_backend.model.User;
import com.vnpt.managementresource_backend.model.mapper.Mapper;
import com.vnpt.managementresource_backend.payload.UpdateUserRequest;
import com.vnpt.managementresource_backend.payload.UserRequest;
import com.vnpt.managementresource_backend.respository.UserRespo;
import com.vnpt.managementresource_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Component
public class UserImp implements UserService {
    @Autowired
    UserRespo userRespo;
    @Autowired
    MongoOperations mongoOperations;
    @Override
    public void addUser(UserRequest request) {
        User newUser = new User();
        newUser.setId(generateSequence("users_sequence"));
        Mapper.Usermapper(newUser,request);
        userRespo.save(newUser);
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

    public long generateSequence(String seqName) {
        DatabaseSequence counter = mongoOperations.findAndModify(
                Query.query(where("_id").is(seqName)),
                new Update().inc("seq",1),
                FindAndModifyOptions.options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
}
