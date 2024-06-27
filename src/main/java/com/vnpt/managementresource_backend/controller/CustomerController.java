package com.vnpt.managementresource_backend.controller;

import com.vnpt.managementresource_backend.database.Customer;
import com.vnpt.managementresource_backend.database.User;
import com.vnpt.managementresource_backend.payload.request.AddCustomerRequest;
import com.vnpt.managementresource_backend.payload.request.UpdateCustomerRequest;
import com.vnpt.managementresource_backend.payload.request.UpdateUserRequest;
import com.vnpt.managementresource_backend.payload.request.UserRequest;
import com.vnpt.managementresource_backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping( "api/v1/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("")
    public List<Customer> getAllCustomer(){
        return customerService.getAllCustomer();
    }

    @PostMapping("addCustomer")
    public ResponseEntity<?> createCustomer(@RequestBody AddCustomerRequest request) {
        try {

            if (customerService.isEmailExist(request.getEmail())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists");
            }
            Customer createdCustomer = customerService.addCustomer(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create user: " + e.getMessage());
        }
    }
    @DeleteMapping("deleteCustomer/{id}")
    public void deleteUser(@PathVariable long id){
        customerService.removeCustomer(id);
    }

    @PutMapping("updateCustomer")
    public ResponseEntity<Customer> updateUser( @RequestBody UpdateCustomerRequest updateCustomerRequest){
        Optional<Customer> existingUser = customerService.getCustomerById(updateCustomerRequest.getId());
        if (existingUser.isPresent()) {

            Customer updatedUser = customerService.updateCustomer(updateCustomerRequest);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("get/{id}")
    public Optional<Customer> getAllUser(@PathVariable long id){
        return customerService.getCustomerById(id);
    }
}
