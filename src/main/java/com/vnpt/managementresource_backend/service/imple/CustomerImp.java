package com.vnpt.managementresource_backend.service.imple;

import com.vnpt.managementresource_backend.database.Customer;
import com.vnpt.managementresource_backend.database.MogoFunc;
import com.vnpt.managementresource_backend.database.User;
import com.vnpt.managementresource_backend.models.mapper.Mapper;
import com.vnpt.managementresource_backend.payload.request.AddCustomerRequest;
import com.vnpt.managementresource_backend.payload.request.UpdateCustomerRequest;
import com.vnpt.managementresource_backend.payload.request.UpdateUserRequest;
import com.vnpt.managementresource_backend.respository.CustomerRespo;
import com.vnpt.managementresource_backend.respository.UserRespo;
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
    UserRespo userRespo;
    @Autowired
    MogoFunc mogoFunc;
    @Override
    public Customer addCustomer(AddCustomerRequest request) {
        Customer newCustomer = new Customer();
        newCustomer.setId(mogoFunc.generateSequence("customers_sequence"));
        Mapper.customerMapper(newCustomer,request);
        User user = userRespo.findById(request.getUserId()).get();
        newCustomer.setUser(user);
        user.getListCustomer().add(newCustomer);
        userRespo.save(user);
        return customerRespo.save(newCustomer);
    }

    @Override
    public void removeCustomer(long id) {
        customerRespo.deleteById(id);
    }

    @Override
    public Customer updateCustomer(UpdateCustomerRequest updateUserRequest) {
        Optional<Customer> existingCustomer = customerRespo.findById(updateUserRequest.getId());
        if (existingCustomer.isPresent()){
            Customer customer = existingCustomer.get();
            customer.setFullName(updateUserRequest.getFullName());
            customer.setEmail(updateUserRequest.getEmail());
            customer.setProblem(updateUserRequest.getProblem());
            return customerRespo.save(customer);
        }
        return null;
    }

    @Override
    public Optional<Customer> getCustomerById(long id) {
        return customerRespo.findById(id);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRespo.findAll();
    }

    @Override
    public boolean isEmailExist(String email) {
        return customerRespo.existsByEmail(email);
    }
}
