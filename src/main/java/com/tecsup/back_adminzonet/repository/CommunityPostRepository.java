package com.tecsup.back_adminzonet.repository;

import com.tecsup.back_adminzonet.entity.CommunityPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CommunityPostRepository extends JpaRepository<CommunityPost, Long> {

    /**
     * HU-23: Filtrar publicaciones por contenido de la descripción.
     * Permite al administrador buscar publicaciones específicas sin añadir
     * nuevas columnas a la tabla de la base de datos.
     */
    List<CommunityPost> findByContentContainingIgnoreCase(String content);
}