package com.vnpt.managementresource_backend.service.imple;

import com.vnpt.managementresource_backend.database.Customer;
import com.vnpt.managementresource_backend.database.MogoFunc;
import com.vnpt.managementresource_backend.database.User;
import com.vnpt.managementresource_backend.models.mapper.Mapper;
import com.vnpt.managementresource_backend.payload.request.AddCustomerRequest;
import com.vnpt.managementresource_backend.respository.CustomerRespo;
import com.vnpt.managementresource_backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomerImp implements CustomerService {

    @Autowired
    CustomerRespo customerRespo;
    @Autowired
    MogoFunc mogoFunc;
    @Override
    public Customer addCustomer(AddCustomerRequest request) {
        Customer newCustomer = new Customer();
        newCustomer.setId(mogoFunc.generateSequence("customer_sequence"));
        Mapper.customerMapper(newCustomer,request);
        return customerRespo.save(newCustomer);
    }

    @Override
    public void removeCustomer(long id) {

    }

    @Override
    public Customer updateCustomer(Customer user) {
        return null;
    }

    @Override
    public Optional<Customer> getCustomerById(long id) {
        return Optional.empty();
    }

    @Override
    public List<Customer> getAllCustomer() {
        return null;
    }
}
