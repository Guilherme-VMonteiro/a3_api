package br.com.shop.controllers;

import br.com.shop.entities.Product;
import br.com.shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable(name = "id") Long id) {
        return productService.findById(id);
    }

    @GetMapping()
    public ResponseEntity findAll() {
        return productService.findAll();
    }

    @PostMapping()
    public ResponseEntity create(@RequestBody Product product) {
        return productService.create(product);
    }

    @PutMapping()
    public ResponseEntity update(@RequestBody Product product) {
        return productService.update(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(name = "id") Long id) {
        return productService.delete(id);
    }
}
