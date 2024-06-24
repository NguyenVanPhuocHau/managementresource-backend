package com.vnpt.managementresource_backend.models.mapper;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.vnpt.managementresource_backend.database.Customer;
import com.vnpt.managementresource_backend.database.Unit;
import com.vnpt.managementresource_backend.database.User;
import com.vnpt.managementresource_backend.payload.request.AddCustomerRequest;
import com.vnpt.managementresource_backend.payload.request.AddUnitRequest;
import com.vnpt.managementresource_backend.payload.request.UserRequest;
import org.springframework.beans.BeanUtils;

public class Mapper {

    public static  void userMapper(User user, UserRequest userRequest){
        user.setFullName(userRequest.getFullName());
        user.setEmail(userRequest.getEmail());
        user.setRoles(userRequest.getRoles());


    }

    public static  void unitMapper(Unit unit, AddUnitRequest addUnitRequest){
        unit.setName(addUnitRequest.getName());
        unit.setDescription(addUnitRequest.getDescription());
//        unit.setListUser(addUnitRequest.getListIdUser());
    }

    public static  void customerMapper(Customer customer, AddCustomerRequest addCustomerRequest){
//        customer.setFullName(addCustomerRequest.getFullName());
//        customer.setEmail(addCustomerRequest.getEmail());
//        customer.setPhone(addCustomerRequest.getPhone());
//        customer.setProblem(addCustomerRequest.getProblem());
        BeanUtils.copyProperties(addCustomerRequest,customer);
    }
}
