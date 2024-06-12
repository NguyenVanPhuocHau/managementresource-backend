package com.vnpt.managementresource_backend.Respository;

import com.vnpt.managementresource_backend.Database.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserPepo extends MongoRepository<User,Integer> {

}
