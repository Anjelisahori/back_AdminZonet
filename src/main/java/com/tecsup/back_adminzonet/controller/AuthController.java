package com.tecsup.back_adminzonet.controller;

import com.tecsup.back_adminzonet.dto.LoginRequestDTO;
import com.tecsup.back_adminzonet.dto.LoginResponseDTO;
import com.tecsup.back_adminzonet.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
}