package com.tecsup.back_adminzonet.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "pets")
@Data
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String species;
    private String status; // Ej: "CONNECTED", "LOST"

    // Nuevos campos para monitoreo IoT
    private String deviceStatus; // "ONLINE", "OFFLINE"
    private int batteryLevel;    // 0 a 100
    private String nextVaccinationDate; // Para el control de salud
}