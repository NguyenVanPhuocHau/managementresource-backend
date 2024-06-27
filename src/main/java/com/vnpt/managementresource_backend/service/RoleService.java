package com.vnpt.managementresource_backend.service;

import com.vnpt.managementresource_backend.database.Role;
import com.vnpt.managementresource_backend.payload.request.CustomRoleRequest;

import java.util.List;

public interface RoleService {
    List<Role> getAllRole();

    Role customUserRole(CustomRoleRequest customRoleRequest);
}
