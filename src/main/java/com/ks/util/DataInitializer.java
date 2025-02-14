//package com.ks.util;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import com.ks.entity.Role;
//import com.ks.entity.User;
//import com.ks.repository.RoleRepository;
//import com.ks.repository.UserRepository;
//
//import jakarta.annotation.PostConstruct;
//
//@Component
//public class DataInitializer {
//
//    @Autowired
//    private RoleRepository roleRepository;
//    
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @PostConstruct
//    public void init() {
//        Role adminRole = new Role();
//        adminRole.setName("ADMIN");
//        Role saveAdmin = roleRepository.save(adminRole);
//
//        Role managerRole = new Role();
//        managerRole.setName("MANAGER");
//        Role saveMan = roleRepository.save(managerRole);
//
//        Role userRole = new Role();
//        userRole.setName("USER");
//        Role saveUser = roleRepository.save(userRole);
//
//        User admin = new User();
//        admin.setName("admin");
//        admin.setEmail("admin@gmail.com");
//        admin.setPassword(passwordEncoder.encode("admin@123"));
//        admin.setRoles(List.of(saveAdmin));
//        userRepository.save(admin);
//
//        User manager = new User();
//        manager.setName("manager");
//        manager.setEmail("manager@gmail.com");
//        manager.setPassword(passwordEncoder.encode("manager@123"));
//        manager.setRoles(List.of(saveMan));
//        userRepository.save(manager);
//
//        User user = new User();
//        user.setName("user");
//        user.setEmail("user@gmail.com.com");
//        user.setPassword(passwordEncoder.encode("user@123"));
//        user.setRoles(List.of(saveUser));
//        userRepository.save(user);
//    }
//}
