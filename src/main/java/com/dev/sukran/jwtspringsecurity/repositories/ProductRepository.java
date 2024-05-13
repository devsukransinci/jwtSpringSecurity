package com.dev.sukran.jwtspringsecurity.repositories;

import com.dev.sukran.jwtspringsecurity.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
