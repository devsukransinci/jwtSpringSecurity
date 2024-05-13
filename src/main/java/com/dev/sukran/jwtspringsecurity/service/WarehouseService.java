package com.dev.sukran.jwtspringsecurity.service;

import com.dev.sukran.jwtspringsecurity.entities.Product;
import com.dev.sukran.jwtspringsecurity.entities.Shelf;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WarehouseService {
    Product getProductById(Long productId);
    List<Product> getAllProducts();
    Shelf getShelfForProduct(Long productId);
    void checkoutProduct(Long productId, int quantity);
}
