package com.vnpt.managementresource_backend.respository;

import com.vnpt.managementresource_backend.model.Unit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface UnitRespo extends MongoRepository<Unit,Long> {
}
