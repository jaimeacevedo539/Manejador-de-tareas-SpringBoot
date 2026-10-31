package com.taskmanager.demo.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Task {
    @Id
    @GeneratedValue
    private  Long id;
    private  String title;
    private  String description;
    @Version
    private Long version;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Task() {
    }

    public Task(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Task(String tilte, String description) {
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
