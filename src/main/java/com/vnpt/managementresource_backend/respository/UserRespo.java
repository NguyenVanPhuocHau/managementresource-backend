package com.vnpt.managementresource_backend.respository;

import com.vnpt.managementresource_backend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;


@Component
public interface UserRespo extends MongoRepository<User,Long> {

}