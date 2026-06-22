package com.ComplaintManagement.config;

import com.ComplaintManagement.model.Role;
import com.ComplaintManagement.model.User;
import com.ComplaintManagement.repo.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initDatabase(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepo.count() == 0) {
                User admin = new User();
                admin.setUserName("Super Admin");
                admin.setEmail("admin@complainthub.com");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole(Role.SUPER_ADMIN);
                admin.setApproved(true);
                
                userRepo.save(admin);
                System.out.println("✅ Default Super Admin created! Email: admin@complainthub.com | Password: admin123");
            }
        };
    }
}
