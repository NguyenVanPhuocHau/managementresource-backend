package com.vnpt.managementresource_backend.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AddUnitRequest {
    private String name;
    private String description;
    private List<Long> listIdUser;

}
