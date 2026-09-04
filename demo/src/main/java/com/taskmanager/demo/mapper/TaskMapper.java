package com.taskmanager.demo.mapper;

import com.taskmanager.demo.dto.TaskResponse;
import com.taskmanager.demo.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskResponse toResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getUsers()
        );
    }
}
