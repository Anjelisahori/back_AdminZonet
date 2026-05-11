package com.tecsup.back_adminzonet.controller;

import com.tecsup.back_adminzonet.dto.LoginRequestDTO;
import com.tecsup.back_adminzonet.dto.LoginResponseDTO;
import com.tecsup.back_adminzonet.dto.UserProfileDTO; // Importar el nuevo DTO
import com.tecsup.back_adminzonet.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication; // Importación necesaria para el perfil
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // Permitir React
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }

    /**
     * 🟢 Obtener el perfil detallado del administrador autenticado.
     * GET http://localhost:8081/api/auth/profile
     */
    @GetMapping("/profile")
    public ResponseEntity<UserProfileDTO> getProfile(Authentication authentication) {
        // authentication.getName() extrae el 'subject' (email) del token validado por el filtro
        String email = authentication.getName();
        return ResponseEntity.ok(authService.getMyProfile(email));
    }
}