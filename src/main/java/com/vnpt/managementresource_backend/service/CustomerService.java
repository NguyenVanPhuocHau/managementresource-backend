package com.vnpt.managementresource_backend.service;

import com.vnpt.managementresource_backend.database.Customer;
import com.vnpt.managementresource_backend.database.User;
import com.vnpt.managementresource_backend.payload.request.AddCustomerRequest;
import com.vnpt.managementresource_backend.payload.request.UserRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CustomerService {
    Customer addCustomer(AddCustomerRequest request);
    void removeCustomer(long id);

    Customer updateCustomer(Customer customer);

    Optional<Customer> getCustomerById(long id);

    List<Customer> getAllCustomer();


}
