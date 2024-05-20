package br.com.shop.services;

import br.com.shop.DTOs.OrderDTO;
import br.com.shop.entities.Order;
import br.com.shop.entities.Product;
import br.com.shop.entities.User;
import br.com.shop.repositories.OrderRepository;
import br.com.shop.repositories.ProductRepository;
import br.com.shop.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    public ResponseEntity findAll(){
        return ResponseEntity.ok(orderRepository.findAll());
    }

    @Transactional
    public ResponseEntity createOrder(OrderDTO orderDTO) {
        Optional<User> userOptional = userRepository.findById(orderDTO.getUser());

        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Float totalPrice = 0f;
        List<Product> products = new ArrayList<>();

        for (Long id : orderDTO.getProducts()) {
            Optional<Product> productOptional = productRepository.findById(id);

            if (productOptional.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            Product product = productOptional.get();
            products.add(product);
            totalPrice += product.getPrice();
        }

        Order order = new Order(userOptional.get(), new Date(), totalPrice, products);

        return ResponseEntity.ok(orderRepository.save(order));
    }
}
