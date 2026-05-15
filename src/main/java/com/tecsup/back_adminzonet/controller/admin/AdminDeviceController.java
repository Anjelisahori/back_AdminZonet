package com.tecsup.back_adminzonet.controller.admin;

import com.tecsup.back_adminzonet.entity.User;
import com.tecsup.back_adminzonet.entity.Pet;
import com.tecsup.back_adminzonet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/devices")
@CrossOrigin(origins = "*")
public class AdminDeviceController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Reporte de red de collares.
     * Usa el campo 'status' sincronizado con el Dashboard y la entidad Pet.
     */
    @GetMapping("/status-report")
    public ResponseEntity<List<Map<String, Object>>> getDeviceNetworkStatus() {
        List<User> users = userRepository.findAll();
        List<Map<String, Object>> report = new ArrayList<>();

        for (User user : users) {
            // Verificamos si el usuario tiene la lista de mascotas inicializada
            if (user.getPets() != null) {
                for (Pet pet : user.getPets()) {
                    report.add(Map.of(
                            "petName", pet.getName(),
                            "ownerEmail", user.getEmail(),
                            "deviceId", "ZN-IOT-" + pet.getId(),
                            // Cambiado getDeviceStatus() -> getStatus() para total consistencia
                            "status", pet.getStatus() != null ? pet.getStatus() : "OFFLINE"
                    ));
                }
            }
        }
        return ResponseEntity.ok(report);
    }

    /**
     * Simulación de reinicio de hardware.
     */
    @PostMapping("/{deviceId}/reboot")
    public ResponseEntity<?> forceRebootDevice(@PathVariable String deviceId) {
        return ResponseEntity.ok(Map.of(
                "message", "Comando de reinicio enviado exitosamente al dispositivo " + deviceId,
                "timestamp", LocalDateTime.now(),
                "status", "SUCCESS"
        ));
    }
}