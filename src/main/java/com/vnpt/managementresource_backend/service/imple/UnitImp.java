package com.vnpt.managementresource_backend.service.imple;

import com.vnpt.managementresource_backend.database.MogoFunc;
import com.vnpt.managementresource_backend.database.Unit;
import com.vnpt.managementresource_backend.database.User;
import com.vnpt.managementresource_backend.models.mapper.Mapper;
import com.vnpt.managementresource_backend.payload.request.AddUnitRequest;
import com.vnpt.managementresource_backend.respository.UnitRespo;
import com.vnpt.managementresource_backend.respository.UserRespo;
import com.vnpt.managementresource_backend.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UnitImp implements UnitService {

   @Autowired
    MogoFunc mogoFunc;

    @Autowired
     UnitRespo unitRespo;
    @Autowired
    UserRespo userRespo;


    @Override
    public Unit createUnit(AddUnitRequest addUnitRequest) {
        Unit newUnit = new Unit();
        newUnit.setId(mogoFunc.generateSequence("units_sequence"));
        Mapper.unitMapper(newUnit,addUnitRequest);

        unitRespo.save(newUnit);
        return newUnit;
    }

    @Override
    public void removeUnit(long id) {

        for (User user: userRespo.findByUnitId(id)) {

//            user.setUnitId(150);

            userRespo.save(user);
        }
        unitRespo.deleteById(id);
    }

    @Override
    public Unit updateUnit(Unit unit) {
        Optional<Unit> existingUser = unitRespo.findById(unit.getId());
        if (existingUser.isPresent()) {
            return unitRespo.save(unit);
        }
        return null;
    }

    @Override
    public List<Unit> getAllUnit() {
        return unitRespo.findAll();
    }

    @Override
    public Page<Unit> getAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo-1,5);
        return this.unitRespo.findAll(pageable);
    }

    @Override
    public List<Unit> getAll() {
        return this.unitRespo.findAll();
    }

    @Override
    public Optional<Unit> getUnitById(long id) {
        return unitRespo.findById(id);
    }


}
