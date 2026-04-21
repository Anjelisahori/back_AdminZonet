package com.tecsup.back_adminzonet.service.admin;

import com.tecsup.back_adminzonet.entity.SupportTicket;
import com.tecsup.back_adminzonet.repository.SupportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class AdminSupportService {
    @Autowired private SupportRepository supportRepo;

    public void answerTicket(Long ticketId, String response) {
        supportRepo.findById(ticketId).ifPresent(t -> {
            t.setAdminResponse(response);
            t.setStatus("RESOLVED");
            t.setResolvedAt(LocalDateTime.now());
            supportRepo.save(t);
        });
    }
}