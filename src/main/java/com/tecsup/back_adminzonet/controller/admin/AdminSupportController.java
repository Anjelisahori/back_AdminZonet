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
public class AdminSupportController {

    @Autowired
    private SupportTicketRepository supportTicketRepository;

    @Autowired
    private NotificationRepository notificationRepository; // Inyectamos el nuevo repo

    @Autowired
    private AdminSupportService adminSupportService;

    // 1. Obtener todos los tickets
    @GetMapping("/tickets")
    public List<SupportTicket> getAllTickets() {
        return supportTicketRepository.findAll();
    }

    // 2. Endpoint para resolver y notificar al usuario (El que usa tu Modal)
    @PostMapping("/tickets/{id}/resolve-and-notify")
    public ResponseEntity<?> resolveAndNotify(@PathVariable Long id) {
        // 1. Buscamos el ticket para obtener el user_id
        SupportTicket ticket = supportTicketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket no encontrado"));

        // 2. Cambiamos estado del ticket a CLOSED
        ticket.setStatus("CLOSED");
        supportTicketRepository.save(ticket);

        // 3. INSERTAMOS EL AVISO en la tabla 'notifications' que ya tienes
        Notification aviso = new Notification();
        aviso.setUserId(ticket.getUserId());
        aviso.setMessage("Soporte Zoonet: Tu ticket #" + ticket.getId() + " sobre '" + ticket.getSubject() + "' ha sido resuelto.");
        aviso.setCreatedAt(LocalDateTime.now());

        notificationRepository.save(aviso);

        return ResponseEntity.ok("Usuario notificado y ticket cerrado con éxito");
    }

    // 3. Mantener el PUT por si solo quieres cambiar el estado sin notificar
    @PutMapping("/tickets/{id}/status")
    public void updateTicketStatus(@PathVariable Long id) {
        adminSupportService.answerTicket(id);
    }
}