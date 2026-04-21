package com.tecsup.back_adminzonet.controller.admin;

import com.tecsup.back_adminzonet.dto.DashboardDTO;
import com.tecsup.back_adminzonet.service.admin.AdminDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/dashboard")
public class AdminDashboardController {
    @Autowired private AdminDashboardService dashboardService;

    @GetMapping("/summary")
    public DashboardDTO getSummary() {
        return dashboardService.getStats();
    }
}