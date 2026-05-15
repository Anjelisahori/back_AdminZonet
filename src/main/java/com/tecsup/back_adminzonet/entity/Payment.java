package com.tecsup.back_adminzonet.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "plan_type")
    private String planType;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "user_id")
    private Long userId;

    private String status; // "PENDING", "COMPLETED"

    // Relación para jalar los datos del usuario automáticamente
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
}