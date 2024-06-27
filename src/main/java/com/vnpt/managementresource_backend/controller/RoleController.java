package com.vnpt.managementresource_backend.controller;

import com.vnpt.managementresource_backend.database.Role;
import com.vnpt.managementresource_backend.database.User;
import com.vnpt.managementresource_backend.payload.request.CustomRoleRequest;
import com.vnpt.managementresource_backend.payload.request.UserRequest;
import com.vnpt.managementresource_backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping( "api/v1/roles")
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("")
    public List<Role> getAllRole(){
        return roleService.getAllRole();
    }
    @PostMapping("customRole")
    public ResponseEntity<?> customRole(@RequestBody CustomRoleRequest request) {
        try {
            Role customRole = roleService.customUserRole(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(customRole);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create role: " + e.getMessage());
        }
    }
}
