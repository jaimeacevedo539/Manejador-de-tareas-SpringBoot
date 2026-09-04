package com.taskmanager.demo.service;

import com.taskmanager.demo.dto.UserRequest;
import com.taskmanager.demo.dto.UserResponse;
import com.taskmanager.demo.exception.UserNotFoundException;
import com.taskmanager.demo.model.User;
import com.taskmanager.demo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserService {

    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserWithTasks(Long id){
        return  userRepository.findByIdWithTasks(id)
                .orElseThrow(()-> new UserNotFoundException(id));
    }

    public UserResponse register(UserRequest request){
        // 1. verificar email

        // 2. encriptar password

        // 3. crear User

        // 4. guardar

        // 5. devolver Response
        return null;
    }
}
