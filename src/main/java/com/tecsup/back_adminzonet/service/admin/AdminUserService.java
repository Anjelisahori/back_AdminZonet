package com.tecsup.back_adminzonet.service.admin;

import com.tecsup.back_adminzonet.entity.User;
import com.tecsup.back_adminzonet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdminUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 1. Coincide con getAllUsers() en el controlador
    public List<User> obtenerTodosLosUsuarios() {
        return userRepository.findAll();
    }

    // 2. Coincide con toggleActive() en el controlador
    public void cambiarEstadoUsuario(Long id, boolean estado) {
        userRepository.findById(id).ifPresent(user -> {
            user.setActive(estado);
            userRepository.save(user);
        });
    }

    // 3. Coincide con create() en el controlador
    public User crearUsuario(User user) {
        if (user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(user);
    }

    // 4. Coincide con update() en el controlador
    public User actualizarUsuario(Long id, User datos) {
        return userRepository.findById(id).map(user -> {
            user.setName(datos.getName()); // Cambiado de getFullName a getName
            user.setEmail(datos.getEmail());
            user.setPlan(datos.getPlan());
            user.setActive(datos.isActive());
            if (datos.getPassword() != null && !datos.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(datos.getPassword()));
            }
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    // 5. Coincide con search() en el controlador
    public List<User> buscarUsuarios(String termino) {
        return userRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(termino, termino);
    }
}