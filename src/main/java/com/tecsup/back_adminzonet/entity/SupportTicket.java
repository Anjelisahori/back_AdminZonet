package com.tecsup.back_adminzonet.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "support_tickets")
@Data // Esto genera automáticamente el setResolvedAt
public class SupportTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String subject;
    private String description;
    private String status;
    private String priority;
    private String adminResponse;

    // ESTA LÍNEA ES LA QUE TE FALTA:
    private LocalDateTime resolvedAt;

    private LocalDateTime createdAt;
}