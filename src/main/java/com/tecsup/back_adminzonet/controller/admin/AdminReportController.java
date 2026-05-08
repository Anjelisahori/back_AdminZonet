package com.tecsup.back_adminzonet.controller.admin;

import com.tecsup.back_adminzonet.entity.LostPet;
import com.tecsup.back_adminzonet.repository.LostPetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/reports")
public class AdminReportController {

    @Autowired
    private LostPetRepository lostPetRepository;

    @GetMapping("/lost-pets")
    public List<LostPet> getLostPets() {
        // Esto jalará todos los datos de la tabla lost_pets de tu DB en Railway
        return lostPetRepository.findAll();
    }
}