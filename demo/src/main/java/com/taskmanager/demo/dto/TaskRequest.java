package com.taskmanager.demo.dto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public class TaskRequest {

    @NotBlank
    private String title;
    @NotBlank
    private String description;

    public TaskRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
