package com.dev.sukran.jwtspringsecurity.service;

import com.dev.sukran.jwtspringsecurity.dto.SignupRequest;
import com.dev.sukran.jwtspringsecurity.dto.UserDto;
import com.dev.sukran.jwtspringsecurity.models.User;
import com.dev.sukran.jwtspringsecurity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{
   @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto createUser(SignupRequest signupRequest) {
        User user =new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());

        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        User createdUser=userRepository.save(user);
        UserDto userDto =new UserDto();

        userDto.setId(createdUser.getId());
        userDto.setEmail(createdUser.getEmail());
        userDto.setName(createdUser.getName());

        userDto.setPassword(createdUser.getPassword());
        return userDto;
    }
}
