package com.tecsup.back_adminzonet.service;

import com.tecsup.back_adminzonet.dto.LoginRequestDTO;
import com.tecsup.back_adminzonet.dto.LoginResponseDTO;
import com.tecsup.back_adminzonet.entity.User;
import com.tecsup.back_adminzonet.repository.UserRepository;
import com.tecsup.back_adminzonet.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    public LoginResponseDTO login(LoginRequestDTO request) {
        // 1. Buscar usuario por email (usando el método Optional que agregamos al repo)
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 2. Verificar que la contraseña coincida con el hash de la DB
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        // 3. Validar el rol (Spring Security requiere el prefijo ROLE_ para hasRole)
        if (!"ROLE_ADMIN".equals(user.getRole())) {
            throw new RuntimeException("Acceso denegado: No cuenta con privilegios de administrador");
        }

        // 4. Generar el token (Aquí es donde saltaba el error de WeakKeyException)
        String token = jwtUtils.generateToken(user.getEmail());

        return new LoginResponseDTO(token, user.getEmail(), user.getRole());
    }
}