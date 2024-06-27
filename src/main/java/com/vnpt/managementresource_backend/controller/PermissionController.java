package com.vnpt.managementresource_backend.controller;

import com.vnpt.managementresource_backend.database.Permission;
import com.vnpt.managementresource_backend.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping( "api/v1/permissions")
public class PermissionController {
    @Autowired
    PermissionService permissionService;

    @GetMapping("")
    public List<Permission> getAllPermiss(){
        return  permissionService.getAllPermission();
    }
}
