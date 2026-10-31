package com.taskmanager.demo.service;

import com.taskmanager.demo.auth.CurrentUserProvider;
import com.taskmanager.demo.dto.TaskResponse;
import com.taskmanager.demo.dto.UpdateTaskRequest;
import com.taskmanager.demo.dto.UserTaskResponse;
import com.taskmanager.demo.exception.TaskNotFoundException;
import com.taskmanager.demo.exception.UserNotFoundException;
import com.taskmanager.demo.mapper.TaskMapper;
import com.taskmanager.demo.mapper.UserMapper;
import com.taskmanager.demo.model.Task;
import com.taskmanager.demo.model.User;
import com.taskmanager.demo.repository.TaskRepository;
import com.taskmanager.demo.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;


@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private  final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CurrentUserProvider currentUserProvider;


    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper, UserRepository userRepository, UserMapper userMapper, CurrentUserProvider currentUserProvider) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.currentUserProvider = currentUserProvider;
    }

    public Task createTask( String title, String description){
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        return taskRepository.save(task);
    }
    public Task getTask(Long id){

        return taskRepository.findById(id)
                 .orElseThrow(()-> new TaskNotFoundException(id));
    }


    public Page<TaskResponse> getTasks(Pageable pageable) {

        Page<Task> tasks = taskRepository.findAll(pageable);

        return tasks.map(taskMapper::toResponse);
    }

    public List<TaskResponse> getTasks() {

        List<Task> tasks = taskRepository.findAll();

        return tasks.stream()
                .map(taskMapper::toResponse)
                .toList();
    }

    public UserTaskResponse getUserWithTasks(Long userId) {

        User user = userRepository.findByIdWithTasks(userId)
                .orElseThrow(() ->
                        new UserNotFoundException( userId)
                );

        return userMapper.toResponse(user);
    }

    public Task updateTask(Long id, UpdateTaskRequest request){
        Task task = taskRepository.findById(id)
                .orElseThrow(()-> new TaskNotFoundException(id));

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());

        return taskRepository.save(task);
    }

    public void deleteTask(Long id){

        taskRepository.findById(id)
                .orElseThrow(()-> new TaskNotFoundException(id));

        Long currentUserId = currentUserProvider.getCurrentUserId();

        taskRepository.deleteById(id);

    }
    public List<Task> getByTitle(String title){
        return taskRepository.findByTitle(title);
    }

    public List<Task> getByTitleContaining(String title){
        return  taskRepository.findByTitleContaining(title);
    }

    public List<Task> getByTitleContainingAndDescriptionContaining(String title, String description){
        return  taskRepository.findByTitleContainingAndDescriptionContaining(title,description);
    }

    public Page<TaskResponse> getTaskByTitle( String title, Pageable pageable){
        return  taskRepository
                .findByTitleContaining(title,pageable)
                .map(taskMapper::toResponse);
    }
}
