package com.tecsup.back_adminzonet.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "pets")
@Data
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String species;

    // Cambiamos el nombre de la variable de 'deviceStatus' a 'status'
    // para que coincida con la consulta del repositorio de estadísticas
    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    // Métodos auxiliares para compatibilidad con el controlador de collares
    public String getDeviceStatus() {
        return this.status;
    }
}