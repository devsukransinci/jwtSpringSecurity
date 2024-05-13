package com.dev.sukran.jwtspringsecurity.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "_shelf")
public class Shelf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 11, nullable = false)
    private long shelfId;
    @Column(name = "shelfnumber")
    private int shelfNumber;

    @Column(name = "capasity")
    private int capacity;

    //kalan alan boşluk miktarı
    @Column(name = "availableSpace")
    private Integer availableSpace;

    @OneToMany(mappedBy = "shelf",cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    @JsonIgnoreProperties("products")
    private Warehouse warehouse;

    public void setAvailableSpace(Integer availableSpace) {
        if (availableSpace == null) {
            throw new IllegalArgumentException("Available space cannot be null");
        }
        this.availableSpace = availableSpace;
    }

}

