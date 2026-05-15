package com.tecsup.back_adminzonet.repository;

import com.tecsup.back_adminzonet.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    /**
     * Permite al administrador filtrar rápidamente qué pagos están PENDIENTES
     * y cuáles ya han sido COMPLETADOS.
     */
    List<Payment> findByStatus(String status);
}