package com.vnpt.managementresource_backend.model;

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
    private long id;
    private String fullName;
    private String email;
    private String role;
}
