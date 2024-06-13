package com.vnpt.managementresource_backend.service;

import com.vnpt.managementresource_backend.model.Unit;
import org.springframework.stereotype.Service;

@Service
public interface UnitService {
    void addUnit(Unit unit);
    void removeUnit(String id);

    void updateUnit(Unit unit);

}
