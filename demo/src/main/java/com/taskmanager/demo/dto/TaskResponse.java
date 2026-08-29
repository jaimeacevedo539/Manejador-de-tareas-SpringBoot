package com.taskmanager.demo.dto;

import com.taskmanager.demo.model.Task;

import java.util.List;

public class TaskResponse {
    private final Long id;
    private String title;
    private String description;
    List<Task> tasks;

    public TaskResponse(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public TaskResponse(Long id, String title, String description, List<Task> tasks) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
