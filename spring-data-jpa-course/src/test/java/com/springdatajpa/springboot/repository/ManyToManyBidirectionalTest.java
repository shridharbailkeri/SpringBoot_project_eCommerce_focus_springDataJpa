package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Role;
import com.springdatajpa.springboot.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ManyToManyBidirectionalTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void saveRole() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("T");
        user.setEmail("johnt@mail.com");
        user.setPassword("secret");

        User admin = new User();
        admin.setFirstName("admin");
        admin.setLastName("admin");
        admin.setEmail("admin@mail.com");
        admin.setPassword("admin");

        Role roleadmin = new Role();
        roleadmin.setName("ROLE_ADMIN");

        roleadmin.getUsers().add(user);
        roleadmin.getUsers().add(admin);

        user.getRoles().add(roleadmin);
        admin.getRoles().add(roleadmin);

        roleRepository.save(roleadmin);
    }

    @Test
    void fetchRole() {
        List<Role> roles = roleRepository.findAll();

        roles.forEach(role -> {
            System.out.println(role.getName());
            role.getUsers().forEach(user -> {
                System.out.println(user.getFirstName());
            });
        });
    }
}
