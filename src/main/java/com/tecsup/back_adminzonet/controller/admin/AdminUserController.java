package com.tecsup.back_adminzonet.controller.admin;

import com.tecsup.back_adminzonet.entity.User;
import com.tecsup.back_adminzonet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@CrossOrigin(origins = "*") // Permite que tu Front-end en React se conecte sin bloqueos
public class AdminUserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * 🟢 Lista todos los usuarios de la base de datos en Railway.
     * GET http://localhost:8081/api/admin/users/all
     */
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        // Si no hay usuarios, devolverá una lista vacía [], pero con estado 200 OK
        return ResponseEntity.ok(users);
    }

    /**
     * 🟡 Cambia el estado del usuario (Activar/Desactivar).
     * PATCH http://localhost:8081/api/admin/users/toggle-active/{id}?status=false
     */
    @PatchMapping("/toggle-active/{id}")
    public ResponseEntity<?> toggleActive(@PathVariable Long id, @RequestParam boolean status) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setActive(status);
                    userRepository.save(user);
                    return ResponseEntity.ok().body("Estado del usuario ID " + id + " actualizado a: " + status);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}