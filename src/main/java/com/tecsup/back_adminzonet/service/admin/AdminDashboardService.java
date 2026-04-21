package com.tecsup.back_adminzonet.service.admin;

import com.tecsup.back_adminzonet.dto.DashboardDTO;
import com.tecsup.back_adminzonet.repository.AdminStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminDashboardService {
    @Autowired private AdminStatsRepository statsRepo;

    public DashboardDTO getStats() {
        DashboardDTO dto = new DashboardDTO();
        dto.setTotalUsers(statsRepo.countTotalUsers());
        dto.setTotalPets(statsRepo.countTotalPets());
        // Aquí puedes agregar más lógica de conteo
        return dto;
    }
}