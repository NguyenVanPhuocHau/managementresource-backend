package com.vnpt.managementresource_backend.model.mapper;

import com.vnpt.managementresource_backend.model.Unit;
import com.vnpt.managementresource_backend.model.User;
import com.vnpt.managementresource_backend.payload.AddUnitRequest;
import com.vnpt.managementresource_backend.payload.UserRequest;
import com.vnpt.managementresource_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class Mapper {

    public static  void Usermapper(User user, UserRequest userRequest){
        user.setFullName(userRequest.getFullName());
        user.setEmail(userRequest.getEmail());
        user.setRole(userRequest.getRole());
        user.setUnitId(userRequest.getUnitId());

    }

    public static  void Unitmapper(Unit unit, AddUnitRequest addUnitRequest){
        unit.setName(addUnitRequest.getName());
        unit.setDescription(addUnitRequest.getDescription());
        unit.setListUser(addUnitRequest.getListIdUser());
    }
}
