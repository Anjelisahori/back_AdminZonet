package com.tecsup.back_adminzonet.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "pets") // Nombre de la tabla en Railway
@Data
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String species;
    private String status; // Ej: "CONNECTED", "LOST"
}