package br.com.shop.services;

import br.com.shop.DTOs.ProductDTO;
import br.com.shop.entities.Product;
import br.com.shop.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    ModelMapper modelMapper = new ModelMapper();

    public ResponseEntity findById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);

        return productOptional.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(modelMapper.map(productOptional.get(), ProductDTO.class));
    }

    public ResponseEntity findAll() {
        return ResponseEntity.ok(productRepository.findAll().stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList()));
    }

    public ResponseEntity create(Product product) {
        return ResponseEntity.ok(modelMapper.map(productRepository.save(product), ProductDTO.class));
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

        return ResponseEntity.ok(modelMapper.map(updatedProduct, ProductDTO.class));
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
