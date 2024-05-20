package br.com.shop.services;

import br.com.shop.entities.User;
import br.com.shop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    UserRepository userRepository;

    public ResponseEntity findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        return userOptional.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(userOptional.get());
    }

    public ResponseEntity findAll() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    public ResponseEntity create(User user) {
        return ResponseEntity.ok(userRepository.save(user));
    }

    public ResponseEntity update(User user) {
        Optional<User> userOptional = userRepository.findById(user.getId());

        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User userFound = userOptional.get();

        userFound.setEmail(user.getEmail());
        userFound.setName(user.getName());

        return ResponseEntity.ok(userRepository.save(userFound));
    }

    public ResponseEntity delete(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        userRepository.delete(userOptional.get());
        return ResponseEntity.ok().build();
    }
}
