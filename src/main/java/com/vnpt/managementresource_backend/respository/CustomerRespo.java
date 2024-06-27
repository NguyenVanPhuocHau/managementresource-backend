package com.vnpt.managementresource_backend.respository;

import com.vnpt.managementresource_backend.database.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface CustomerRespo extends MongoRepository<Customer, Long> {
    boolean existsByEmail(String email);
}
