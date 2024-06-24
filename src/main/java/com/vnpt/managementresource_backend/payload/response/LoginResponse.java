package com.vnpt.managementresource_backend.payload.response;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginResponse {
    private long id;
    private String fullName;
    private String email;
    private List<String> roles;
    private String token;
    private String type;
}
