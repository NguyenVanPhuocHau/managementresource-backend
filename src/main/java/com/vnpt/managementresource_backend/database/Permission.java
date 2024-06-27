package com.vnpt.managementresource_backend.database;

import com.vnpt.managementresource_backend.models.EPermission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Permission {
    @Id
    private long id;

    private String name;
}
