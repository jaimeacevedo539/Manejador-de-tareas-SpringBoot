package com.taskmanager.demo.repository;

import com.taskmanager.demo.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository
        extends JpaRepository<Task, Long> {

    Page<Task> findByTitleContaining(
            String title,
            Pageable pageable
    );

    List<Task> findByTitle(String title);
    List<Task> findByTitleContaining(String title);
    List<Task> findByTitleContainingAndDescriptionContaining(
            String title,
            String description
    );
}


