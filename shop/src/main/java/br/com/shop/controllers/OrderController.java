package br.com.shop.controllers;

import br.com.shop.DTOs.OrderDTOInput;
import br.com.shop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping
    public ResponseEntity findAll(){
        return orderService.findAll();
    }

    @PostMapping
    public ResponseEntity createOrder(@RequestBody OrderDTOInput orderDTOInput){
        return orderService.createOrder(orderDTOInput);
    }
}
