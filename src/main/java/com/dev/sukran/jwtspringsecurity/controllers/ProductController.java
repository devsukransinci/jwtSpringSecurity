package com.dev.sukran.jwtspringsecurity.controllers;

import com.dev.sukran.jwtspringsecurity.entities.Product;
import com.dev.sukran.jwtspringsecurity.repositories.ProductRepository;
import com.dev.sukran.jwtspringsecurity.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.AttributeNotFoundException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductService productService;

    public ProductController(ProductService productService ,ProductRepository productRepository) {

        this.productService = productService;
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product newProduct) {
        productService.createProduct(newProduct);
        return new ResponseEntity<Product>(newProduct, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProductbyId(@PathVariable long id,@RequestBody Product productDetails) throws AttributeNotFoundException {
        Product product= productRepository.findById(id).orElseThrow(() -> new AttributeNotFoundException("Product not found with id" + id ));
        product.setName(productDetails.getName());


        Product savedProduct =productRepository.save(product);
        return ResponseEntity.ok(savedProduct);
    }




    @GetMapping("/{id}") // Corrected mapping for fetching a single product by ID
    public ResponseEntity<Product> getProductId(@PathVariable long id) throws AttributeNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AttributeNotFoundException("Product not found with id: " + id));
        return ResponseEntity.ok(product);
    }


    @DeleteMapping("/delete/{ID}")
    public void  deleteProduct(@PathVariable Long ID) {
        productService.deleteProduct(ID);

    }
}


