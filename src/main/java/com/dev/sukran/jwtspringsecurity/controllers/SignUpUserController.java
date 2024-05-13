package com.dev.sukran.jwtspringsecurity.controllers;

import com.dev.sukran.jwtspringsecurity.dto.SignupRequest;
import com.dev.sukran.jwtspringsecurity.dto.UserDto;
import com.dev.sukran.jwtspringsecurity.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class SignUpUserController {

    @Autowired
    private AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> createUser(@RequestBody SignupRequest signupRequest){

        UserDto createUser =authService.createUser(signupRequest);
        if (createUser ==null){
            return new ResponseEntity<>("user is not created,try again later", HttpStatus.BAD_REQUEST);
        } return new ResponseEntity<>(createUser,HttpStatus.CREATED);

    }


}
