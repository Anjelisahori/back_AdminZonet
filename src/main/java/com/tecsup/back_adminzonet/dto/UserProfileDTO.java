package com.tecsup.back_adminzonet.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserProfileDTO {
    private Long id;
    private String name;
    private String email;
    private String role;
    private boolean active;
    // Puedes agregar más campos aquí, como fecha de registro si la tienes
}