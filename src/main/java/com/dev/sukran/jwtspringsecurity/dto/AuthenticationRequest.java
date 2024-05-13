package com.dev.sukran.jwtspringsecurity.dto;

import com.dev.sukran.jwtspringsecurity.configuration.enums.Role;
import lombok.Data;

@Data
public class AuthenticationRequest {
    private String email;
    private String password;

}
