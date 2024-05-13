package com.dev.sukran.jwtspringsecurity.service;

import com.dev.sukran.jwtspringsecurity.entities.Product;
import com.dev.sukran.jwtspringsecurity.entities.Shelf;
import com.dev.sukran.jwtspringsecurity.repositories.ProductRepository;
import com.dev.sukran.jwtspringsecurity.repositories.ShelfRepository;
import org.springframework.stereotype.Service;

import javax.management.AttributeNotFoundException;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    private final ShelfService shelfService;


    public ProductService(ProductRepository  productRepository, ShelfService shelfService) {
        this.productRepository = productRepository;

        this.shelfService = shelfService;
    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void createProduct(Product newProduct) {
        try {
            shelfService.placeProductOnShelf(newProduct);
            productRepository.save(newProduct);
        } catch (AttributeNotFoundException e) {
            // Handle the exception here
            e.printStackTrace(); // Or log it or throw a different exception
        }
    }
    public void updateProduct(Product updatedProduct) {
        productRepository.save(updatedProduct);
    }

    public void deleteProduct(long ID) {
        productRepository.deleteById(ID);
    }
}
