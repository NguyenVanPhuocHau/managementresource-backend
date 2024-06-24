package com.vnpt.managementresource_backend.database;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.vnpt.managementresource_backend.models.EPermission;
import com.vnpt.managementresource_backend.models.ERole;
import com.vnpt.managementresource_backend.respository.CustomerRespo;
import com.vnpt.managementresource_backend.respository.RoleRespo;
import com.vnpt.managementresource_backend.respository.UnitRespo;
import com.vnpt.managementresource_backend.respository.UserRespo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Configuration
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class DataLoader implements CommandLineRunner {
    @Autowired
    RoleRespo roleRespo;
    @Autowired
    UserRespo userRespo;
    @Autowired
    CustomerRespo customerRespo;
    @Autowired
    UnitRespo unitRespo;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    MogoFunc mogoFunc;
    public boolean roleCheckExist() {
        return roleRespo.findAll().isEmpty();
    }

    public boolean userCheckExits() {return  userRespo.findAll().isEmpty();}

    public boolean unitCheckExits(){return  unitRespo.findAll().isEmpty();}

    public boolean customerExits(){return  customerRespo.findAll().isEmpty();}

    public void insertRolesData(){
        Set<String> setPermission = new HashSet<>();
        Set<EPermission> setEnumPer = EnumSet.allOf(EPermission.class);
        for (Enum e: setEnumPer) {
            setPermission.add(e.toString());
        }
        roleRespo.save(new Role(mogoFunc.generateSequence("roles_sequence"),ERole.ROLE_ADMIN.toString(),setPermission));
        setPermission.clear();
        setPermission.add(EPermission.ADD_CUSTOMER.toString());
        setPermission.add(EPermission.EDIT_CUSTOMER.toString());
        setPermission.add(EPermission.EDIT_USER.toString());
        setPermission.add(EPermission.REMOVE_USER.toString());
        roleRespo.save(new Role(mogoFunc.generateSequence("roles_sequence"),ERole.ROLE_USER.toString(),setPermission));
        setPermission.clear();
        setPermission.add(EPermission.EDIT_CUSTOMER.toString());
        roleRespo.save(new Role(mogoFunc.generateSequence("roles_sequence"),ERole.ROLE_USER.toString(),setPermission));
    }

    public void insertUnit(){
        List<User> userList = new ArrayList<>();
        unitRespo.save(new Unit(mogoFunc.generateSequence("units_sequence"),"Kinh doanh","Phòng kinh doanh",userList));
        unitRespo.save(new Unit(mogoFunc.generateSequence("units_sequence"),"IT","Phòng IT",userList));
//        unitRespo.save(new Unit(mogoFunc.generateSequence("units_sequence"),"Nhân sư","Phòng Nhân sự",userList));
    }

    public void insertUser(){
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleRespo.findById(1L).get());
        Unit unit1 = unitRespo.findById(1l).get();
        User user1 = new User(mogoFunc.generateSequence("users_sequence"),"Nguyen Van Phuoc Hau1","nguyenhau31867@gmail.com",roleSet,encoder.encode("Hau1234567"),unitRespo.findById(1l).get(),new ArrayList<>());
        unit1.getListUser().add(user1);
        Set<Role> roleSet2 = new HashSet<>();
        roleSet2.add(roleRespo.findById(2L).get());
        User user2 = new User(mogoFunc.generateSequence("users_sequence"),"Nguyen Van Phuoc Hau2","nguyenhau31868@gmail.com",roleSet2,encoder.encode("Hau1234568"),unitRespo.findById(1l).get(),new ArrayList<>());
        unit1.getListUser().add(user2);
        Set<Role> roleSet3 = new HashSet<>();
        roleSet3.add(roleRespo.findById(3L).get());
        Unit unit2 = unitRespo.findById(2L).get();
        User user3 = new User(mogoFunc.generateSequence("users_sequence"),"Nguyen Van Phuoc Hau3","nguyenhau31869@gmail.com",roleSet3,encoder.encode("Hau1234569"),unitRespo.findById(2l).get(),new ArrayList<>());
        unit2.getListUser().add(user3);

        unitRespo.save(unit1);
        unitRespo.save(unit2);
        unitRespo.save(unit2);
        userRespo.save(user1);
        userRespo.save(user2);
        userRespo.save(user3);
    }

    public void insertCustomer(){
       Customer customer = customerRespo.save(new Customer(mogoFunc.generateSequence("customers_sequence"),"Nguyen Van A","nguyenvana1@gmail.com","0824831867","No problem", userRespo.findById(1l).get()));
       User user = userRespo.findById(1l).get();
       user.getListCustomer().add(customer);
       userRespo.save(user);

    }



    @Override
    public void run(String... args) throws Exception {
        if (roleCheckExist()){
            insertRolesData();
        }
        if (unitCheckExits()){
            insertUnit();
        }
        if (userCheckExits()){
            insertUser();
        }
        if (customerExits()){
            insertCustomer();
        }
    }


}
