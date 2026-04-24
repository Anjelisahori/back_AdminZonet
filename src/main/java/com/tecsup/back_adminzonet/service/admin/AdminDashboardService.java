package com.tecsup.back_adminzonet.service.admin;

import com.tecsup.back_adminzonet.dto.DashboardDTO;
import com.tecsup.back_adminzonet.repository.AdminStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminDashboardService {

    @Autowired
    private AdminStatsRepository statsRepo;

    /**
     * Obtiene las estadísticas completas para el Dashboard del Administrador.
     * Se sincroniza con los contadores definidos en AdminStatsRepository.
     */
    public DashboardDTO getStats() {
        DashboardDTO dto = new DashboardDTO();

        // 1. Usuarios totales registrados en el sistema
        dto.setTotalUsers(statsRepo.countTotalUsers());

        // 2. Mascotas totales (incluyendo todos los estados)
        dto.setTotalPets(statsRepo.countTotalPets());

        // 3. Usuarios con plan PREMIUM y cuenta activa [CORRECCIÓN]
        dto.setActivePremium(statsRepo.countActivePremiumUsers());

        // 4. Tickets de soporte que aún no han sido resueltos [CORRECCIÓN]
        dto.setPendingTickets(statsRepo.countPendingTickets());

        return dto;
    }
}