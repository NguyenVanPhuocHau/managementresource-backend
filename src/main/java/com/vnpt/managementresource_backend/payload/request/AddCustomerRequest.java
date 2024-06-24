package com.vnpt.managementresource_backend.payload.request;

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
public class AddCustomerRequest {
    private String fullName;
    private String email;

    private String phone;

    private String problem;
}
