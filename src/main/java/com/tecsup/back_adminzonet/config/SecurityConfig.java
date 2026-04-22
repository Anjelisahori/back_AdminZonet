package com.tecsup.back_adminzonet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. Habilitar CORS para que tu Front-end en React pueda conectarse
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // 2. Desactivar CSRF para permitir peticiones desde Postman
                .csrf(csrf -> csrf.disable())

                // 3. Configuración de autorización
                .authorizeHttpRequests(auth -> auth
                        // 🔓 PERMITIR ACCESO TOTAL A LAS RUTAS DE ADMIN PARA PRUEBAS (Temporal)
                        .requestMatchers("/api/admin/**").permitAll()

                        // Permitir rutas de autenticación y recursos estáticos (como fotos de mascotas)
                        .requestMatchers("/api/auth/**", "/uploads/**").permitAll()

                        // El resto de peticiones requieren autenticación
                        .anyRequest().authenticated()
                );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*")); // Permitir cualquier origen en desarrollo
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(false);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // Necesario para que el sistema reconozca las contraseñas encriptadas de Railway
        return new BCryptPasswordEncoder();
    }
}