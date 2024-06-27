package com.vnpt.managementresource_backend.service.imple;

import com.vnpt.managementresource_backend.database.MogoFunc;
import com.vnpt.managementresource_backend.database.Permission;
import com.vnpt.managementresource_backend.database.Role;
import com.vnpt.managementresource_backend.payload.request.CustomRoleRequest;
import com.vnpt.managementresource_backend.respository.PermissionRespo;
import com.vnpt.managementresource_backend.respository.RoleRespo;
import com.vnpt.managementresource_backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleImp implements RoleService {
    @Autowired
    RoleRespo roleRespo;
    @Autowired
    PermissionRespo permissionRespo;
    @Autowired
    MogoFunc mogoFunc;
    @Override
    public List<Role> getAllRole() {
        return roleRespo.findAll();
    }

    @Override
    public Role customUserRole(CustomRoleRequest customRoleRequest) {
        Role customRole = new Role();
        customRole.setId(mogoFunc.generateSequence("roles_sequence"));
        customRole.setName(customRoleRequest.getName());
        for (long id: customRoleRequest.getListPermissionId()) {
            Permission permission = permissionRespo.findById(id).get();
            customRole.getPermissions().add(permission);
        }

        roleRespo.save(customRole);

        return customRole;
    }


}
