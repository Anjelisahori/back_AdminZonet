package com.tecsup.back_adminzonet.repository;

import com.tecsup.back_adminzonet.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Aquí puedes agregar consultas personalizadas en el futuro
}