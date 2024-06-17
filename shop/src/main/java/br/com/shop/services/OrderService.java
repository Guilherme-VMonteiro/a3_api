package br.com.shop.services;

import br.com.shop.DTOs.OrderDTO;
import br.com.shop.DTOs.OrderDTOInput;
import br.com.shop.entities.Order;
import br.com.shop.entities.OrderStatus;
import br.com.shop.entities.Product;
import br.com.shop.entities.User;
import br.com.shop.repositories.OrderRepository;
import br.com.shop.repositories.ProductRepository;
import br.com.shop.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
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

    ModelMapper modelMapper = new ModelMapper();

    public ResponseEntity findAll(){
        return ResponseEntity.ok(orderRepository.findAll().stream().map(order -> modelMapper.map(order, OrderDTO.class)));
    }

    @Transactional
    public ResponseEntity createOrder(OrderDTOInput orderDTOInput) {
        Optional<User> userOptional = userRepository.findById(orderDTOInput.getUser());

        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Float totalPrice = 0f;
        List<Product> products = new ArrayList<>();

        for (Long id : orderDTOInput.getProducts()) {
            Optional<Product> productOptional = productRepository.findById(id);

            if (productOptional.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            Product product = productOptional.get();
            products.add(product);
            totalPrice += product.getPrice();
        }

        if(orderDTOInput.getOrderStatus() == null){
            orderDTOInput.setOrderStatus(OrderStatus.AWAITING_PAYMENT);
        }

        Order order = new Order(userOptional.get(), new Date(), totalPrice, orderDTOInput.getOrderStatus(),products);

        return ResponseEntity.ok(modelMapper.map(orderRepository.save(order), OrderDTO.class));
    }
}
