package com.taskmanager.demo.service;

import com.taskmanager.demo.dto.UserRequest;
import com.taskmanager.demo.dto.UserResponse;
import com.taskmanager.demo.exception.UserNotFoundException;
import com.taskmanager.demo.model.User;
import com.taskmanager.demo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
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

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }
}
