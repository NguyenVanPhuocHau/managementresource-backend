package com.vnpt.managementresource_backend.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class User {
    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";
    @Id
    @NotEmpty(message = "ID is required")
    private long id;
    private String fullName;
    @NotEmpty(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    @NotEmpty(message = "Role is required")
    private String role;
    private long unitId;
}
