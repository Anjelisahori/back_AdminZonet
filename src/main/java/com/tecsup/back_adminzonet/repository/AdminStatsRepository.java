package com.tecsup.back_adminzonet.repository;

import com.tecsup.back_adminzonet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminStatsRepository extends JpaRepository<User, Long> {

    @Query("SELECT COUNT(u) FROM User u")
    long countTotalUsers();

    @Query("SELECT COUNT(p) FROM Pet p")
    long countTotalPets();
}