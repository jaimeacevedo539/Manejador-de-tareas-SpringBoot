package com.taskmanager.demo.service;

import com.taskmanager.demo.exception.UserNotFoundException;
import com.taskmanager.demo.model.User;
import com.taskmanager.demo.repository.UserRepository;

public class UserService {

    private UserRepository userRepository;

    public User getUserWithTasks(Long id){
        return  userRepository.findByIdWithTasks(id)
                .orElseThrow(()-> new UserNotFoundException(id));
    }
}
