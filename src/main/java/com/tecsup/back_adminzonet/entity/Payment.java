package com.tecsup.back_adminzonet.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments") // Se conecta a la tabla 'payments' de tu imagen
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id") // Relación con la tabla 'users'
    private User user;

    private Double amount;
    private String planType; // Ej: "PREMIUM"

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    private String status; // Ej: "COMPLETED"
}