package com.vnpt.managementresource_backend.database;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Customer {
    @Id
    private long id;
    private String fullName;
    @NotEmpty(message = "Email is required")
    @Email(message =  "Email should be valid")
    private String email;
    @NotEmpty(message = "Phone is required")
    private String phone;
    private String problem;
    @DBRef
    @JsonIgnoreProperties({"roles", "password","unit","listCustomer"})
    private User user;
}
