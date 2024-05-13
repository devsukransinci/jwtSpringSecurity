package com.dev.sukran.jwtspringsecurity.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String name;
    private String email;
    private String password;
    private String phone;
}
