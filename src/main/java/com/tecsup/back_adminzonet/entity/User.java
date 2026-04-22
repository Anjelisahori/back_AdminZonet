package com.tecsup.back_adminzonet.entity;

// IMPORTANTE: Usa jakarta, no javax
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String plan;
    private boolean active; // Este es el que falta para el error de 'setActive'
}