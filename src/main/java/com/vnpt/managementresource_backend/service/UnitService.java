package com.vnpt.managementresource_backend.service;

import com.vnpt.managementresource_backend.database.Unit;
import com.vnpt.managementresource_backend.payload.request.AddUnitRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UnitService {
    Unit createUnit(AddUnitRequest addUnitRequest);

    void removeUnit(long id);

    Unit updateUnit(Unit unit);

    List<Unit> getAllUnit();

    Page<Unit> getAll(Integer pageNo);

    List<Unit> getAll();

    Optional<Unit> getUnitById(long id);


}
