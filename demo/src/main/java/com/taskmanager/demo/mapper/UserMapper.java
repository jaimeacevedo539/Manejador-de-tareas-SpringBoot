package com.taskmanager.demo.mapper;

import com.taskmanager.demo.dto.TaskResponse;
import com.taskmanager.demo.dto.UserTaskResponse;
import com.taskmanager.demo.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    private final  TaskMapper taskMapper;

    public UserMapper(TaskMapper taskMapper){
        this.taskMapper = taskMapper;
    }

    public UserTaskResponse toResponse(User user){

        List<TaskResponse> tasks = user.getTasks()
                .stream()
                .map(taskMapper::toResponse)
                .toList();
        return new UserTaskResponse(
                user.getId(),
                user.getName(),
                tasks
        );
    }
}
