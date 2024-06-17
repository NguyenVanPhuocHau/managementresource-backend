package com.vnpt.managementresource_backend.service.imp;

import com.vnpt.managementresource_backend.model.DatabaseSequence;
import com.vnpt.managementresource_backend.model.Unit;
import com.vnpt.managementresource_backend.model.User;
import com.vnpt.managementresource_backend.model.mapper.Mapper;
import com.vnpt.managementresource_backend.payload.AddUnitRequest;
import com.vnpt.managementresource_backend.respository.UnitRespo;
import com.vnpt.managementresource_backend.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Component
public class UnitImp implements UnitService {

    @Autowired
    MongoOperations mongoOperations;

    @Autowired
     UnitRespo unitRespo;


    @Override
    public Unit createUnit(AddUnitRequest addUnitRequest) {
        Unit newUnit = new Unit();
        newUnit.setId(generateSequence("units_sequence"));
        Mapper.Unitmapper(newUnit,addUnitRequest);

        unitRespo.save(newUnit);
        return newUnit;
    }

    @Override
    public void removeUnit(long id) {
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

    public long generateSequence(String seqName) {
        DatabaseSequence counter = mongoOperations.findAndModify(
                Query.query(where("_id").is(seqName)),
                new Update().inc("seq",1),
                FindAndModifyOptions.options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
}
