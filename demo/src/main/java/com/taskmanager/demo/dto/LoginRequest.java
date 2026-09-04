package com.taskmanager.demo.dto;

public record LoginRequest(
        String email,
        String password
) {
}
