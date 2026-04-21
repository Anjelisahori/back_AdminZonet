package com.tecsup.back_adminzonet.controller.admin;

import com.tecsup.back_adminzonet.entity.SupportTicket;
import com.tecsup.back_adminzonet.repository.SupportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/admin/support")
public class AdminSupportController {
    @Autowired private SupportRepository supportRepo;

    @GetMapping("/tickets")
    public List<SupportTicket> getTickets() {
        return supportRepo.findAll();
    }

    @PostMapping("/tickets/{id}/resolve")
    public void resolve(@PathVariable Long id, @RequestBody String response) {
        supportRepo.findById(id).ifPresent(t -> {
            t.setAdminResponse(response);
            t.setStatus("RESOLVED");
            t.setResolvedAt(LocalDateTime.now());
            supportRepo.save(t);
        });
    }
}