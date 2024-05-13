package com.dev.sukran.jwtspringsecurity.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 11, nullable = false)
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "shelf_id")
    @JsonIgnoreProperties("products")
    private Shelf shelf;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    @JsonIgnoreProperties("products")
    private Warehouse warehouse;

    @ManyToMany(mappedBy = "products")
    private List<Shelf> shelves;



}
