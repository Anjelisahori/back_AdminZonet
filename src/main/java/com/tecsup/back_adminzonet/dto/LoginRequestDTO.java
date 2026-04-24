package com.tecsup.back_adminzonet.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String email;
    private String password;
}