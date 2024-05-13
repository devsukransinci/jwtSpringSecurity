package com.dev.sukran.jwtspringsecurity.service;

import com.dev.sukran.jwtspringsecurity.entities.Product;
import com.dev.sukran.jwtspringsecurity.entities.Shelf;
import com.dev.sukran.jwtspringsecurity.repositories.ProductRepository;
import com.dev.sukran.jwtspringsecurity.repositories.ShelfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarehouseServiceImpl implements WarehouseService{
    private final ShelfRepository shelfRepository;
    private final ProductRepository productRepository;

    @Autowired

    public WarehouseServiceImpl(ShelfRepository shelfRepository, ProductRepository productRepository) {
        this.shelfRepository = shelfRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Shelf getShelfForProduct(Long productId) {
        return null;
    }

    @Override
    @Transactional
    public void checkoutProduct(Long id, int quantity) {
        // Find the product
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            // Handle if product does not exist
            return;
        }

        // Get shelves where the product is stored
        List<Shelf> shelvesWithProduct = product.getShelves();

        // Find the least full shelf
        Shelf leastFullShelf = findLeastFullShelf(shelvesWithProduct);

        // Perform checkout if a shelf is found
        if (leastFullShelf != null) {
            // Perform the checkout operation here
            // For example, you can remove the product from the shelf
            // and update the quantity accordingly
        } else {
            // Handle if no shelves with the product are found
        }
    }

    private Shelf findLeastFullShelf(List<Shelf> shelves) {
        // Sort shelves by product count in ascending order
        List<Shelf> sortedShelves = shelves.stream()
                .sorted(Comparator.comparingInt(shelf -> shelf.getProducts().size()))
                .collect(Collectors.toList());

        // Return the first (least full) shelf
        return sortedShelves.isEmpty() ? null : sortedShelves.get(0);
    }
}