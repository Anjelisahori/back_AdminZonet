package com.tecsup.back_adminzonet.repository;

import com.tecsup.back_adminzonet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface AdminStatsRepository extends JpaRepository<User, Long> {

    @Query("SELECT COUNT(u) FROM User u")
    long countTotalUsers();

    @Query("SELECT COUNT(p) FROM Pet p")
    long countTotalPets();

    @Query("SELECT COUNT(u) FROM User u WHERE u.plan = 'PREMIUM' AND u.active = true")
    long countActivePremiumUsers();

    @Query("SELECT COUNT(t) FROM SupportTicket t WHERE t.status = 'PENDING'")
    long countPendingTickets();

    /**
     * Obtiene el conteo agrupado por el campo 'status' de la entidad Pet.
     * Esta consulta es la que causaba el error de compilación.
     * Al usar p.status, Hibernate buscará el atributo 'status' en la clase Pet.
     */
    @Query("SELECT p.status, COUNT(p) FROM Pet p GROUP BY p.status")
    List<Object[]> countPetsByStatus();
}