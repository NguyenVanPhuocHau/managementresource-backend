package com.vnpt.managementresource_backend.payload.request;


import com.vnpt.managementresource_backend.database.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserRequest {
    private String fullName;
    private String email;
    private Set<Role> roles;
    private long unitId;
}
