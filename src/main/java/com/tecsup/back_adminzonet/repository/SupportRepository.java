package com.tecsup.back_adminzonet.repository;

import com.tecsup.back_adminzonet.entity.SupportTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportRepository extends JpaRepository<SupportTicket, Long> {
}