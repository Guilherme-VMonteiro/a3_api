package br.com.shop.controllers;

import br.com.shop.entities.User;
import br.com.shop.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServices userServices;

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable(name = "id") Long id) {
        return userServices.findById(id);
    }

    @GetMapping()
    public ResponseEntity findAll() {
        return userServices.findAll();
    }

    @PostMapping()
    public ResponseEntity create(@RequestBody User user) {
        return userServices.create(user);
    }

    @PutMapping()
    public ResponseEntity update(@RequestBody User user) {
        return userServices.update(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(name = "id") Long id) {
        return userServices.delete(id);
    }
}
