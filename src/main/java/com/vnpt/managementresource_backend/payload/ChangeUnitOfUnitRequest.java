package com.vnpt.managementresource_backend.payload;

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
public class ChangeUnitOfUnitRequest {
    private long idUser;
    private long idUnit;
}
