package com.taskmanager.demo.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "app_users")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private String password;
    @OneToMany(mappedBy ="user")
    List<Task> tasks;

    public User(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
