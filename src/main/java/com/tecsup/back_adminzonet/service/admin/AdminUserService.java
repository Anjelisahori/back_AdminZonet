package com.tecsup.back_adminzonet.service.admin;

import com.tecsup.back_adminzonet.entity.User;
import com.tecsup.back_adminzonet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdminUserService {

    @Autowired
    private UserRepository userRepository;

    // Obtener todos los usuarios de Railway
    public List<User> obtenerTodosLosUsuarios() {
        return userRepository.findAll();
    }

    // Bloquear/Desactivar usuario desde el panel
    public void cambiarEstadoUsuario(Long id, boolean estado) {
        userRepository.findById(id).ifPresent(user -> {
            user.setActive(estado);
            userRepository.save(user);
        });
    }
}