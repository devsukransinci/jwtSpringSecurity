package com.dev.sukran.jwtspringsecurity.service;

import com.dev.sukran.jwtspringsecurity.dto.SignupRequest;
import com.dev.sukran.jwtspringsecurity.dto.UserDto;

public interface AuthService {

    UserDto createUser(SignupRequest signupRequest);
}
