package com.tecsup.back_adminzonet.repository;

import com.tecsup.back_adminzonet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.plan = 'PREMIUM'")
    List<User> findPremiumUsers();
}