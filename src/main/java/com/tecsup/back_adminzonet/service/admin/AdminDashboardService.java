package com.tecsup.back_adminzonet.service.admin;

import com.tecsup.back_adminzonet.dto.DashboardDTO;
import com.tecsup.back_adminzonet.repository.AdminStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminDashboardService {

    @Autowired
    private AdminStatsRepository statsRepo;

    public DashboardDTO getStats() {
        DashboardDTO dto = new DashboardDTO();

        // 1. Estadísticas principales (Consultas de solo lectura)
        dto.setTotalUsers(statsRepo.countTotalUsers());
        dto.setTotalPets(statsRepo.countTotalPets());
        dto.setActivePremium(statsRepo.countActivePremiumUsers());
        dto.setPendingTickets(statsRepo.countPendingTickets());

        // 2. Procesar estados de collares para el gráfico circular
        Map<String, Long> statusMap = new HashMap<>();
        List<Object[]> results = statsRepo.countPetsByStatus();

        for (Object[] result : results) {
            // Transformación "al vuelo": Si el status es nulo o vacío en BD, mostramos "CONECTADO"
            String statusValue = (result[0] != null && !result[0].toString().trim().isEmpty())
                    ? (String) result[0]
                    : "CONECTADO";

            Long count = (Long) result[1];

            // Agrupamos bajo la misma etiqueta para evitar duplicados en el gráfico
            statusMap.put(statusValue, statusMap.getOrDefault(statusValue, 0L) + count);
        }

        dto.setDevicesStatus(statusMap);

        // El successRate se toma del valor por defecto en el DTO (94.8%)
        return dto;
    }
}