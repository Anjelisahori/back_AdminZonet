package com.tecsup.back_adminzonet.repository;

import com.tecsup.back_adminzonet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminStatsRepository extends JpaRepository<User, Long> {

    @Query("SELECT COUNT(u) FROM User u")
    long countTotalUsers();

    @Query("SELECT COUNT(p) FROM Pet p")
    long countTotalPets();

    @Query("SELECT COUNT(u) FROM User u WHERE u.plan = 'PREMIUM' AND u.active = true")
    long countActivePremiumUsers();

    @Query("SELECT COUNT(t) FROM SupportTicket t WHERE t.status = 'PENDING'")
    long countPendingTickets();
}