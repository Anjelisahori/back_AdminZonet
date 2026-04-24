package com.tecsup.back_adminzonet;

import com.tecsup.back_adminzonet.entity.User;
import com.tecsup.back_adminzonet.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BackAdminZonetApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackAdminZonetApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            String adminEmail = "admin@zoonet.com";
            // Verifica si el admin ya existe en Railway
            if (userRepository.findByEmail(adminEmail).isEmpty()) {
                User admin = new User();
                admin.setName("Admin Zoonet");
                admin.setEmail(adminEmail);
                admin.setPassword(passwordEncoder.encode("admin123")); // admin123
                admin.setRole("ROLE_ADMIN");
                admin.setActive(true);
                admin.setPlan("PREMIUM");

                userRepository.save(admin);
                System.out.println(">>> SEEDER: Administrador creado exitosamente.");
            } else {
                System.out.println(">>> SEEDER: El Administrador ya existe en la base de datos.");
            }
        };
    }
}