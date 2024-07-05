package com.vnpt.managementresource_backend.service.imple;

import com.vnpt.managementresource_backend.database.Permission;
import com.vnpt.managementresource_backend.respository.PermissionRespo;
import com.vnpt.managementresource_backend.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PermissionImp implements PermissionService {
    @Autowired
    PermissionRespo permissionRespo;

    @Override
    public List<Permission> getAllPermission() {
        return permissionRespo.findAll();
    }
}
