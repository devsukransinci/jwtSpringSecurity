package com.dev.sukran.jwtspringsecurity.entities;

import com.dev.sukran.jwtspringsecurity.configuration.enums.Status;
import com.dev.sukran.jwtspringsecurity.models.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.util.List;

@Entity
@Data
@DynamicInsert
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, length = 11)
    private Integer warehouse_id;
    @Column(nullable = false)
    private String name;


    @Column(length = 11)
    private Integer capacity;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(nullable = false)
    private User user;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
    private List<Product> products;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
    private List<Shelf> shelves;
}
