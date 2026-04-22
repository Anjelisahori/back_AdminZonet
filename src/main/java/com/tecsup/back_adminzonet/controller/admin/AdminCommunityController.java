package com.tecsup.back_adminzonet.controller.admin;

import com.tecsup.back_adminzonet.service.admin.AdminModerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin/community")
@CrossOrigin(origins = "*") //
public class AdminCommunityController {

    @Autowired
    private AdminModerationService moderationService;

    /**
     * 🟢 Listar todas las publicaciones para moderación.
     * GET http://localhost:8081/api/admin/community/posts
     */
    @GetMapping("/posts")
    public ResponseEntity<List<?>> getAllPosts() {
        List<?> posts = moderationService.listAllPosts();
        return ResponseEntity.ok(posts);
    }

    /**
     * 🔴 Eliminar una publicación inapropiada.
     * DELETE http://localhost:8081/api/admin/community/posts/{id}
     */
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> removePost(@PathVariable Long id) {
        moderationService.deletePost(id);
        return ResponseEntity.ok("Contenido eliminado exitosamente");
    }

    /**
     * 🔵 Auditoría de Inteligencia Artificial (Gemini Matches).
     * GET http://localhost:8081/api/admin/community/ai-audit
     */
    @GetMapping("/ai-audit")
    public ResponseEntity<?> checkAiPerformance() {
        return ResponseEntity.ok("Historial de IA obtenido para auditoría");
    }
}