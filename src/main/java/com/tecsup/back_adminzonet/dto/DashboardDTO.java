package com.tecsup.back_adminzonet.dto;

import lombok.Data;
import java.util.Map;

@Data
public class DashboardDTO {
    private long totalUsers;
    private long totalPets;
    private long activePremium;
    private long pendingTickets;
    private Map<String, Long> devicesStatus;
}