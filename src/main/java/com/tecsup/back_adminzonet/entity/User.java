package com.tecsup.back_adminzonet.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

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
    private String password;
    private String role;
    private String plan;
    private boolean active;

    // ✅ RELACIÓN LÓGICA (No crea columna en la DB)
    // mappedBy indica que el dueño de la relación es el campo "user" en la entidad Pet
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Pet> pets;
}