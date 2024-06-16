package com.vnpt.managementresource_backend.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UpdateUserRequest {
    private long id;
    private String fullName;
    private String email;
    private String role;
}
