package com.taskmanager.demo.dto;

import com.taskmanager.demo.model.Task;
import com.taskmanager.demo.model.User;

import java.util.List;

public record UserTaskResponse(
        Long userId,
        String userName,
        List<TaskResponse> tasks){

    public UserTaskResponse {
    }



    @Override
    public Long userId() {
        return userId;
    }

    @Override
    public String userName() {
        return userName;
    }

    @Override
    public List<TaskResponse> tasks() {
        return tasks;
    }
}
