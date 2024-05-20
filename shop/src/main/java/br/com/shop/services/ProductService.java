package br.com.shop.services;

import br.com.shop.entities.Product;
import br.com.shop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public ResponseEntity findById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);

        return productOptional.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(productOptional.get());
    }

    public ResponseEntity findAll() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    public ResponseEntity create(Product product) {
        return ResponseEntity.ok(productRepository.save(product));
    }

    public ResponseEntity update(Product product) {
        Optional<Product> productOptional = productRepository.findById(product.getId());

        if (productOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        productOptional.get().setName(product.getName());
        productOptional.get().setDescription(product.getDescription());
        productOptional.get().setPrice(product.getPrice());
        productOptional.get().setQuantity(product.getQuantity());

        Product updatedProduct = productRepository.save(productOptional.get());

        return ResponseEntity.ok(updatedProduct);
    }

    public ResponseEntity delete(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        productRepository.delete(productOptional.get());
        return ResponseEntity.ok().build();
    }
}
