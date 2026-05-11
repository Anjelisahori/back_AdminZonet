package com.tecsup.back_adminzonet.service;

import com.tecsup.back_adminzonet.dto.LoginRequestDTO;
import com.tecsup.back_adminzonet.dto.LoginResponseDTO;
import com.tecsup.back_adminzonet.dto.UserProfileDTO; // 1. Importa el nuevo DTO
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
        // 1. Buscar usuario por email
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 2. Verificar contraseña
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        // 3. Validar rol de administrador
        if (!"ROLE_ADMIN".equals(user.getRole())) {
            throw new RuntimeException("Acceso denegado: No cuenta con privilegios de administrador");
        }

        // 4. Generar el token
        String token = jwtUtils.generateToken(user.getEmail());

        return new LoginResponseDTO(token, user.getEmail(), user.getRole());
    }

    // 🆕 Nuevo método para obtener el perfil detallado del administrador
    public UserProfileDTO getMyProfile(String email) {
        // Buscamos al usuario en la base de datos por el email extraído del token
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Construimos el DTO con los datos de la entidad User
        return UserProfileDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .active(user.isActive())
                .build();
    }
}