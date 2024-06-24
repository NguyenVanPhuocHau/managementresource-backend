package com.vnpt.managementresource_backend.database;

import com.vnpt.managementresource_backend.models.EPermission;
import com.vnpt.managementresource_backend.models.ERole;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
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

    private Set<String> permissions;

//    public Role(String name, Set<String> setRole){
//        this.name = name;
//        this.permissions = setRole;
//    }

}
