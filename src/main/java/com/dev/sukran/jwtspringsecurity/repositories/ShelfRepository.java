package com.dev.sukran.jwtspringsecurity.repositories;

import com.dev.sukran.jwtspringsecurity.entities.Product;
import com.dev.sukran.jwtspringsecurity.entities.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShelfRepository extends JpaRepository<Shelf,Long> {
    Shelf findByShelfId(long shelfId);

}
