package com.taskmanager.demo.dto;

public record UserRequest(
        String name,
        String email,
        String password
) {
}
