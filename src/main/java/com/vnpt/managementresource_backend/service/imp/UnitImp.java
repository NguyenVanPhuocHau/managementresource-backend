package com.vnpt.managementresource_backend.service.imp;

import com.vnpt.managementresource_backend.model.DatabaseSequence;
import com.vnpt.managementresource_backend.model.Unit;
import com.vnpt.managementresource_backend.respository.UnitRespo;
import com.vnpt.managementresource_backend.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Objects;

import static org.springframework.data.mongodb.core.query.Criteria.where;

public class UnitImp implements UnitService {

    @Autowired
    MongoOperations mongoOperations;

    @Autowired
     UnitRespo unitRespo;


    @Override
    public void addUnit(Unit unit) {

    }

    @Override
    public void removeUnit(String id) {

    }

    @Override
    public void updateUnit(Unit unit) {

    }
}
