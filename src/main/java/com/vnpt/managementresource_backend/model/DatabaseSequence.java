package com.vnpt.managementresource_backend.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class DatabaseSequence {

    @Id
    private String id;

    private long seq;


}
