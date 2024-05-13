package com.dev.sukran.jwtspringsecurity.dto;

import com.dev.sukran.jwtspringsecurity.configuration.enums.Role;
import com.dev.sukran.jwtspringsecurity.configuration.enums.Status;
import lombok.Data;

@Data
public class UserDto {

    private  Long id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private Role role;
    private Status status;
}
