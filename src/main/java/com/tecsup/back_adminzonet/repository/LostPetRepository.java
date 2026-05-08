package com.tecsup.back_adminzonet.repository;

import com.tecsup.back_adminzonet.entity.LostPet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LostPetRepository extends JpaRepository<LostPet, Long> {
}