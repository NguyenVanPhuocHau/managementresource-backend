package com.vnpt.managementresource_backend.respository;

import com.vnpt.managementresource_backend.database.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRespo extends MongoRepository<Role,Long> {
}
