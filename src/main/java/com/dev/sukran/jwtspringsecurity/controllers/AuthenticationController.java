package com.dev.sukran.jwtspringsecurity.controllers;

import com.dev.sukran.jwtspringsecurity.dto.AuthenticationRequest;
import com.dev.sukran.jwtspringsecurity.dto.AuthenticationResponse;
import com.dev.sukran.jwtspringsecurity.service.jwt.UserDetailsServiceImpl;
import com.dev.sukran.jwtspringsecurity.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response)throws BadCredentialsException , DisabledException, UsernameNotFoundException, IOException {
    try {
   authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
           authenticationRequest.getPassword()));
    }catch (BadCredentialsException e){
        throw new BadCredentialsException("Incorrect username or password");

    }catch (DisabledException disabledException){
        response.sendError(HttpServletResponse.SC_NOT_FOUND,"user is not created.Register User first");
    return null;
    }
    final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
     final String jwt = jwtUtil.generateToken(userDetails.getUsername());
     return new AuthenticationResponse(jwt);

    }

}
