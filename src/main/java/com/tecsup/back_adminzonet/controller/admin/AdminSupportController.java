package com.tecsup.back_adminzonet.controller.admin;

import com.tecsup.back_adminzonet.entity.Notification;
import com.tecsup.back_adminzonet.entity.SupportTicket;
import com.tecsup.back_adminzonet.repository.NotificationRepository;
import com.tecsup.back_adminzonet.repository.SupportTicketRepository;
import com.tecsup.back_adminzonet.service.admin.AdminSupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/admin/support")
@CrossOrigin(origins = "*") // Asegura la conexión con tu Panel de React
public class AdminSupportController {

    @Autowired
    private SupportTicketRepository supportTicketRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private AdminSupportService adminSupportService;

    // 1. MANTIENE: Obtener todos los tickets para la tabla principal
    @GetMapping("/tickets")
    public List<SupportTicket> getAllTickets() {
        return supportTicketRepository.findAll();
    }

    /**
     * 2. NUEVO/ACTUALIZADO: Endpoint para el Modal "Notificar"
     * Resuelve el ticket y genera el registro en la tabla 'notifications' de Railway.
     */
    @PostMapping("/tickets/{id}/resolve-and-notify")
    public ResponseEntity<?> resolveAndNotify(@PathVariable Long id) {
        // 1. Buscamos el ticket en la BD para obtener el user_id vinculado
        SupportTicket ticket = supportTicketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket no encontrado"));

        // 2. Cambiamos estado del ticket a CLOSED de forma persistente
        ticket.setStatus("CLOSED");
        supportTicketRepository.save(ticket);

        // 3. CREAMOS EL AVISO para la App móvil del usuario
        Notification aviso = new Notification();
        aviso.setUserId(ticket.getUserId());
        aviso.setMessage("Soporte Zoonet: Tu ticket #" + ticket.getId() + " sobre '" + ticket.getSubject() + "' ha sido resuelto.");
        aviso.setCreatedAt(LocalDateTime.now());

        // Guardamos en la tabla de notificaciones compartida
        notificationRepository.save(aviso);

        return ResponseEntity.ok("Usuario notificado y ticket cerrado con éxito");
    }

    // 3. MANTIENE: Cambio de estado simple mediante el Service
    @PutMapping("/tickets/{id}/status")
    public void updateTicketStatus(@PathVariable Long id) {
        adminSupportService.answerTicket(id);
    }
}