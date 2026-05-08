package com.tecsup.back_adminzonet.repository;

import com.tecsup.back_adminzonet.entity.SupportTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportTicketRepository extends JpaRepository<SupportTicket, Long> {
    // Aquí puedes agregar métodos personalizados si quisieras filtrar por estado luego
    long countByStatus(String status);
}