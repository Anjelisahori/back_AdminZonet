package com.tecsup.back_adminzonet.controller.admin;

import com.tecsup.back_adminzonet.entity.Pet;
import com.tecsup.back_adminzonet.repository.AdminStatsRepository; // Usamos el repo de stats para conteos
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin/pets")
@CrossOrigin(origins = "*")
public class AdminPetController {

    @Autowired
    private AdminStatsRepository petRepo; // O tu PetRepository si lo creaste

    @GetMapping("/all")
    public ResponseEntity<List<Pet>> getAllPets() {
        // En un entorno real usarías un PetRepository específico
        return ResponseEntity.ok(null); // Aquí retornarías la lista de la DB
    }

    @GetMapping("/alerts")
    public ResponseEntity<?> getDeviceAlerts() {
        // Lógica para filtrar mascotas con batería < 20%
        return ResponseEntity.ok("Alertas de dispositivos críticos enviadas");
    }
}