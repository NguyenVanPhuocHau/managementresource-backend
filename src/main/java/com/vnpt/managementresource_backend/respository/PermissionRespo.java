package com.vnpt.managementresource_backend.respository;

import com.vnpt.managementresource_backend.database.Permission;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PermissionRespo extends MongoRepository<Permission,Long> {
}
