package com.taskmanager.demo.controller;


import com.taskmanager.demo.dto.*;
import com.taskmanager.demo.model.Task;
import com.taskmanager.demo.model.User;
import com.taskmanager.demo.service.TaskService;
import com.taskmanager.demo.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService) {

         this.taskService = taskService;
        this.userService = userService;
    }

    private List<TaskResponse> toTaskResponses(List<Task> tasks) {

        List<TaskResponse> responses = new ArrayList<>();

        for (Task task : tasks) {
            responses.add(
                    new TaskResponse(
                            task.getId(),
                            task.getTitle(),
                            task.getDescription()
                    )
            );
        }

        return responses;
    }


    @GetMapping("/{id}")
    public Task getTask(@PathVariable Long id){
        return taskService.getTask(id);
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(
            @Valid @RequestBody TaskRequest taskRequest) {

        Task task = taskService.createTask(
                taskRequest.getTitle(),
                taskRequest.getDescription()
        );

        TaskResponse response = new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/tasksAll")
    public List<TaskResponse> getTasks() {
        return taskService.getTasks();
    }

    @PostMapping("/register")
    public UserResponse register(@Valid @RequestBody UserRequest userRequest){
        return userService.register(userRequest);
    }

    @GetMapping("/{id}/tasks") //valida que deveria ser UserTaskResponse
    public User  getUserWithTasks(@PathVariable Long id) {
        return userService.getUserWithTasks(id);
    }

    @GetMapping("/title")
    public List<TaskResponse> getTasksByTitle(@RequestParam String title) {
        return toTaskResponses(taskService.getByTitle(title));
    }
    /*@GetMapping("/search")
    public List<TaskResponse> getTasksByTitleContaining(@RequestParam String title) {
        return toTaskResponses(taskService.getByTitleContaining(title));
    }*/

    @GetMapping("/Search")
    public Page<TaskResponse> getTaskByTitle( @RequestParam String title, Pageable pageable){
        return taskService.getTaskByTitle(title,pageable);

    }

    @GetMapping
    public Page<TaskResponse> getTasks(Pageable pageable) {
        return taskService.getTasks(pageable);
    }

    @GetMapping("/search2")
    public List<TaskResponse> getByTitleContainingAndDescriptionContaining(@RequestParam String title,
                                                                           @RequestParam String description) {
        return toTaskResponses(taskService.getByTitleContainingAndDescriptionContaining(title,description));
    }
    @PutMapping("/{id}")
    public TaskResponse updateTask(@PathVariable Long id, @RequestBody UpdateTaskRequest request){
        Task task = taskService.updateTask(id,request);

        return new TaskResponse( task.getId(), task.getTitle(),task.getDescription());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity  deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}


