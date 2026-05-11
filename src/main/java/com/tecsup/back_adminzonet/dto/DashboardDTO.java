package com.tecsup.back_adminzonet.dto;

import lombok.Data;
import java.util.HashMap;
import java.util.Map;

@Data
public class DashboardDTO {
    // Estos valores vienen de las consultas SELECT que ya tienes en el Service
    private long totalUsers;
    private long totalPets;
    private long activePremium;
    private long pendingTickets;

    // Esto es un valor FIJO para el diseño del frontend, NO toca la BD
    private double successRate = 94.8;

    // Se inicializa para que React no falle si no hay datos que mostrar
    private Map<String, Long> devicesStatus = new HashMap<>();
}