package com.vnpt.managementresource_backend.database;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.vnpt.managementresource_backend.models.EPermission;
import com.vnpt.managementresource_backend.models.ERole;
import com.vnpt.managementresource_backend.respository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Configuration
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
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
    @Autowired
    PermissionRespo permissionRespo;

    public boolean roleCheckExist() {
        return roleRespo.findAll().isEmpty();
    }

    public boolean userCheckExits() {
        return userRespo.findAll().isEmpty();
    }

    public boolean unitCheckExits() {
        return unitRespo.findAll().isEmpty();
    }

    public boolean customerExits() {
        return customerRespo.findAll().isEmpty();
    }

    public boolean permissionExits() {
        return permissionRespo.findAll().isEmpty();
    }

    public void insertPermisson() {
        Set<EPermission> setEnumPer = EnumSet.allOf(EPermission.class);
        for (EPermission e : setEnumPer) {
            permissionRespo.save(new Permission(mogoFunc.generateSequence("permission_sequence"), e.toString()));
        }
    }

    public void insertRolesData() {
        List<Permission> setPermission = new ArrayList<>();
        Set<EPermission> setEnumPer = EnumSet.allOf(EPermission.class);
//        for (EPermission e: setEnumPer) {
//            setPermission.add(e);
//        }
        setPermission = permissionRespo.findAll();
        roleRespo.save(new Role(mogoFunc.generateSequence("roles_sequence"), ERole.ROLE_ADMIN.toString(), setPermission));
        setPermission.clear();
        setPermission.add(permissionRespo.findById(7l).get());
        setPermission.add(permissionRespo.findById(6l).get());
//        setPermission.add(permissionRespo.findById(4l).get());
        roleRespo.save(new Role(mogoFunc.generateSequence("roles_sequence"), ERole.ROLE_USER.toString(), setPermission));
        setPermission.clear();
        setPermission.add(permissionRespo.findById(6l).get());
        setPermission.add(permissionRespo.findById(4l).get());
        setPermission.add(permissionRespo.findById(5l).get());
        roleRespo.save(new Role(mogoFunc.generateSequence("roles_sequence"), ERole.ROLE_EDITER.toString(), setPermission));
    }

    public void insertUnit() {
        List<User> userList = new ArrayList<>();
        unitRespo.save(new Unit(mogoFunc.generateSequence("units_sequence"), "Kinh doanh", "Phòng kinh doanh", userList));
        unitRespo.save(new Unit(mogoFunc.generateSequence("units_sequence"), "IT", "Phòng IT", userList));
//        unitRespo.save(new Unit(mogoFunc.generateSequence("units_sequence"),"Nhân sư","Phòng Nhân sự",userList));
    }

    public void insertUser() {

        Role role1 = roleRespo.findById(1L).get();
        Unit unit1 = unitRespo.findById(1l).get();
        User user1 = new User(mogoFunc.generateSequence("users_sequence"), "Nguyen Van Phuoc Hau1", "nguyenhau31867@gmail.com", role1, encoder.encode("Hau1234567"), unitRespo.findById(1l).get(), new ArrayList<>());
        unit1.getListUser().add(user1);
        Role role2 = roleRespo.findById(2L).get();
        User user2 = new User(mogoFunc.generateSequence("users_sequence"), "Nguyen Van Phuoc Hau2", "nguyenhau31868@gmail.com", role2, encoder.encode("Hau1234568"), unitRespo.findById(1l).get(), new ArrayList<>());
        unit1.getListUser().add(user2);
        Role role3 = roleRespo.findById(3L).get();
        Unit unit2 = unitRespo.findById(2L).get();
        User user3 = new User(mogoFunc.generateSequence("users_sequence"), "Nguyen Van Phuoc Hau3", "nguyenhau31869@gmail.com", role3, encoder.encode("Hau1234569"), unitRespo.findById(2l).get(), new ArrayList<>());
        unit2.getListUser().add(user3);
        //


        User user4 = new User(mogoFunc.generateSequence("users_sequence"), "Nguyen Van Phuoc Hau4", "nguyenhau31860@gmail.com", role2, encoder.encode("Hau1234560"), unitRespo.findById(2l).get(), new ArrayList<>());
        unit2.getListUser().add(user4);

        unitRespo.save(unit1);
        unitRespo.save(unit2);
        unitRespo.save(unit2);
        userRespo.save(user1);
        userRespo.save(user2);
        userRespo.save(user3);
        userRespo.save(user4);

    }

    public void insertCustomer() {
        Customer customer = customerRespo.save(new Customer(mogoFunc.generateSequence("customers_sequence"), "Nguyen Van A", "nguyenvana@gmail.com", "0824831867", "No problem", userRespo.findById(2l).get()));
        User user = userRespo.findById(2l).get();
        user.getListCustomer().add(customer);
//       userRespo.save(user);
        //
        Customer customer1 = customerRespo.save(new Customer(mogoFunc.generateSequence("customers_sequence"), "Nguyen Van B", "nguyenvanb@gmail.com", "0824831868", "No problem", userRespo.findById(2l).get()));
        user.getListCustomer().add(customer1);
        userRespo.save(user);
        //
        Customer customer2 = customerRespo.save(new Customer(mogoFunc.generateSequence("customers_sequence"), "Nguyen Van A", "nguyenvana@gmail.com", "0824831867", "No problem", userRespo.findById(4l).get()));
        User user1 = userRespo.findById(4l).get();
        user1.getListCustomer().add(customer2);
//       userRespo.save(user);
        //
        Customer customer3 = customerRespo.save(new Customer(mogoFunc.generateSequence("customers_sequence"), "Nguyen Van B", "nguyenvanb@gmail.com", "0824831868", "No problem", userRespo.findById(4l).get()));
        user1.getListCustomer().add(customer3);
        userRespo.save(user1);


    }


    @Override
    public void run(String... args) throws Exception {
        if (permissionExits()) {
            insertPermisson();
        }
        if (roleCheckExist()) {
            insertRolesData();
        }
        if (unitCheckExits()) {
            insertUnit();
        }
        if (userCheckExits()) {
            insertUser();
        }
        if (customerExits()) {
            insertCustomer();
        }
    }


}
