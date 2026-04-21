package com.tecsup.back_adminzonet.controller.admin;

import com.tecsup.back_adminzonet.entity.User;
import com.tecsup.back_adminzonet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@CrossOrigin(origins = "*") // Para que React en localhost:3000 pueda entrar
public class AdminUserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all")
    public List<User> getAll() {
        return userRepository.findAll(); // Esto jala directo de Railway
    }

    @PatchMapping("/toggle-active/{id}")
    public void toggleActive(@PathVariable Long id, @RequestParam boolean status) {
        userRepository.findById(id).ifPresent(u -> {
            u.setActive(status);
            userRepository.save(u);
        });
    }
}