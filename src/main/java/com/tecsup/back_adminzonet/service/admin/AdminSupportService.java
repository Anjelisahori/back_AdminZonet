package com.tecsup.back_adminzonet.service.admin;

import com.tecsup.back_adminzonet.repository.SupportTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminSupportService {

    // Cambiado a SupportTicketRepository para coincidir con tu entidad SupportTicket
    @Autowired
    private SupportTicketRepository supportTicketRepo;

    /**
     * Actualiza el estado del ticket a CLOSED.
     * Se eliminaron setAdminResponse y setResolvedAt porque no existen en la BD.
     */
    public void answerTicket(Long ticketId) {
        supportTicketRepo.findById(ticketId).ifPresent(t -> {
            // Solo usamos el campo 'status' que sí existe en tu tabla support_tickets
            t.setStatus("CLOSED");
            supportTicketRepo.save(t);
        });
    }
}