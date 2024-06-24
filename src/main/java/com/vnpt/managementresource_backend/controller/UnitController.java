package com.vnpt.managementresource_backend.controller;


import com.vnpt.managementresource_backend.database.Unit;
import com.vnpt.managementresource_backend.payload.request.AddUnitRequest;
import com.vnpt.managementresource_backend.service.UnitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping( "api/v1/units")
public class UnitController {

    @Autowired
    UnitService unitService;
    @GetMapping("")
    public List<Unit> getAllUnit(){
        return unitService.getAllUnit();
    }


    @PostMapping("createUnit")
    public ResponseEntity<Unit> addUser(@Valid @RequestBody AddUnitRequest addUnitRequest) {
        Unit newUnit = unitService.createUnit(addUnitRequest);
        return ResponseEntity.ok(newUnit);
    }

    @GetMapping("pages")
    public Page<Unit> getAllUnits(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        return unitService.getAll(pageNo);
    }

    @DeleteMapping("deleteUnit/{id}")
    public void deleteUser(@PathVariable long id){
        unitService.removeUnit(id);
    }

    @PutMapping("updateUnit")
    public ResponseEntity<Unit> updateUser( @RequestBody Unit unit){
        Optional<Unit> existingUser = unitService.getUnitById(unit.getId());
        if (existingUser.isPresent()) {
            Unit updatedUnit = unitService.updateUnit(unit);
            return new ResponseEntity<>(updatedUnit, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public Optional<Unit> getAllUser(@PathVariable long id){
        return unitService.getUnitById(id);
    }
}
