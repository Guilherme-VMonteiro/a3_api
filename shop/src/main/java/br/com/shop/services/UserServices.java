package br.com.shop.services;

import br.com.shop.DTOs.UserDTO;
import br.com.shop.entities.User;
import br.com.shop.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServices {

    @Autowired
    UserRepository userRepository;

    ModelMapper modelMapper = new ModelMapper();

    public ResponseEntity findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        return userOptional.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(modelMapper.map(userOptional.get(), UserDTO.class));
    }

    public ResponseEntity findAll() {

        List<UserDTO> dtos = userRepository.findAll().stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity create(User user) {
        return ResponseEntity.ok(modelMapper.map(userRepository.save(user), UserDTO.class));
    }

    public ResponseEntity update(User user) {
        Optional<User> userOptional = userRepository.findById(user.getId());

        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User userFound = userOptional.get();

        userFound.setEmail(user.getEmail());
        userFound.setName(user.getName());

        return ResponseEntity.ok(modelMapper.map(userRepository.save(userFound), UserDTO.class));
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
