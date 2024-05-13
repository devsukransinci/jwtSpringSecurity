package com.dev.sukran.jwtspringsecurity.controllers;

import com.dev.sukran.jwtspringsecurity.entities.Product;
import com.dev.sukran.jwtspringsecurity.entities.Shelf;
import com.dev.sukran.jwtspringsecurity.repositories.ShelfRepository;
import com.dev.sukran.jwtspringsecurity.service.ShelfService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.AttributeNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/shelf")
public class ShelfController {
    private final ShelfService shelfService;
    private final ShelfRepository shelfRepository;

    public ShelfController(ShelfService shelfService, ShelfRepository shelfRepository) {
        this.shelfService = shelfService;
        this.shelfRepository = shelfRepository;
    }
    @GetMapping
    public List<Shelf> getAllShelf(){
        return shelfService.getAllShelf();
    }

    @PostMapping("/create")
    public ResponseEntity<Shelf> createShelf(@RequestBody Shelf newShelf) {
        Shelf createdShelf = shelfService.createShelf(newShelf);
        return new ResponseEntity<>(createdShelf, HttpStatus.OK);
    }
    @PutMapping("/update/{shelfId}")
    public ResponseEntity<Shelf> updateProductbyId(@PathVariable long shelfId, @RequestBody Shelf shelfDetails) throws AttributeNotFoundException {
        Shelf shelf= shelfRepository.findById(shelfId).orElseThrow(() -> new AttributeNotFoundException("Shelf not found with id" + shelfId ));
        shelf.setCapacity(shelfDetails.getCapacity());
        shelf.setAvailableSpace(shelfDetails.getAvailableSpace());
        Shelf savedShelf =shelfRepository.save(shelf);
        return ResponseEntity.ok(savedShelf);
    }
    @DeleteMapping("/delete/{shelfId}")
    public void deleteShelf(@PathVariable  Long shelfId) {
        shelfService.deleteShelf(shelfId);
    }
    @GetMapping("/{shelfId}") // Corrected mapping for fetching a single product by ID
    public ResponseEntity<Shelf> getShelfById(@PathVariable long shelfId) throws AttributeNotFoundException {
        Shelf shelf = shelfRepository.findById(shelfId)
                .orElseThrow(() -> new AttributeNotFoundException("Product not found with id: " + shelfId));
        return ResponseEntity.ok(shelf);
    }

}
