package com.vnpt.managementresource_backend.database;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor

@Setter
public class Role {

    @Id
    private long id;

    private String name;
    @DBRef
    private List<Permission> permissions = new ArrayList<>();


}
