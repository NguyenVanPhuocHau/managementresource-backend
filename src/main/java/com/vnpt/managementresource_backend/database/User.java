package com.vnpt.managementresource_backend.database;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
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
    @DBRef
    private Role role;
    private String password;
    @DBRef
    @JsonIgnoreProperties({"listUser"})
    private Unit unit;
    @DBRef
    private List<Customer> listCustomer;



}
