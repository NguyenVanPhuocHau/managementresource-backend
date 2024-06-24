package com.vnpt.managementresource_backend.respository;

import com.vnpt.managementresource_backend.database.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public interface UserRespo extends MongoRepository<User,Long> {
    List<User> findByIdIn(List<Long> ids);

    List<User> findByUnitId(long id);

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);



}
