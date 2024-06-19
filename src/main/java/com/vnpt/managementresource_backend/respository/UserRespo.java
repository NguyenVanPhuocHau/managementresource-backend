package com.vnpt.managementresource_backend.respository;

import com.vnpt.managementresource_backend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface UserRespo extends MongoRepository<User,Long> {
    List<User> findByIdIn(List<Long> ids);

    List<User> findByUnitId(long id);

    boolean existsByEmail(String email);



}
