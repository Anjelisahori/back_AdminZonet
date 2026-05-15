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

    // 1. Obtener todos los usuarios de la base de datos de Railway
    public List<User> obtenerTodosLosUsuarios() {
        return userRepository.findAll();
    }

    // 2. Cambiar el estado de activación (Activo/Suspendido)
    public void cambiarEstadoUsuario(Long id, boolean estado) {
        userRepository.findById(id).ifPresent(user -> {
            user.setActive(estado);
            userRepository.save(user);
        });
    }

    // 3. Crear nuevo usuario con contraseña encriptada
    public User crearUsuario(User user) {
        if (user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        // Al crear, se guarda el plan (BASIC/PREMIUM) enviado desde el front
        return userRepository.save(user);
    }

    // 4. Actualizar datos, incluyendo la sincronización del Plan Premium
    public User actualizarUsuario(Long id, User datos) {
        return userRepository.findById(id).map(user -> {
            user.setName(datos.getName());
            user.setEmail(datos.getEmail());

            // SINCRONIZACIÓN PREMIUM: Actualiza el campo plan (BASIC o PREMIUM)
            // que también es modificado por el backend de usuario tras el pago
            user.setPlan(datos.getPlan());

            user.setActive(datos.isActive());

            // Solo actualiza la contraseña si se proporciona una nueva
            if (datos.getPassword() != null && !datos.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(datos.getPassword()));
            }

            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    // 5. Buscar usuarios por nombre o email
    public List<User> buscarUsuarios(String termino) {
        return userRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(termino, termino);
    }
}