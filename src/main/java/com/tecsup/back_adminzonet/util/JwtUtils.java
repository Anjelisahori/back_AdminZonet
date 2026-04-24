package com.tecsup.back_adminzonet.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtils {

    // 🔑 Esta llave tiene más de 64 caracteres, cumpliendo el requisito de 512 bits para HS512
    private final String jwtSecret = "Zoonet_Admin_Super_Secret_Key_2026_Secure_String_For_HS512_Algorithm_Requirement_Zoonet_Project";
    private final int jwtExpirationMs = 86400000; // 24 horas

    public String generateToken(String email) {
        // 🛠️ Convertimos el String en una SecretKey válida para JJWT 0.11.5
        SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key, SignatureAlgorithm.HS512) // Usamos el objeto Key en lugar del String
                .compact();
    }
}