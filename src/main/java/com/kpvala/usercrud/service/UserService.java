
// UserService.java
package com.kpvala.usercrud.service;

import com.kpvala.usercrud.dto.UserDTO;
import com.kpvala.usercrud.entity.Users;
import com.kpvala.usercrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword()))
                .collect(Collectors.toList());
    }

    public UserDTO createUser(UserDTO userDto) {
        Users user = new Users();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        Users savedUser = userRepository.save(user);  // Save and get the ID

        // Return DTO including the generated ID
        return new UserDTO(savedUser.getId(), savedUser.getFirstName(), savedUser.getLastName(), savedUser.getEmail(), savedUser.getPassword());
    }


    public UserDTO updateUser(Long id, UserDTO userDto) {
        Users user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
        return userDto;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
