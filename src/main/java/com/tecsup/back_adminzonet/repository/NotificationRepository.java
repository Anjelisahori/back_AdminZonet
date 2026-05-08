package com.tecsup.back_adminzonet.repository;

import com.tecsup.back_adminzonet.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    // Aquí heredamos todos los métodos para guardar notificaciones en Railway
}