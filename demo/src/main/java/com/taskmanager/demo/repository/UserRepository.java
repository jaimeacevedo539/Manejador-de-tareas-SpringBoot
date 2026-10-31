package com.taskmanager.demo.repository;

import com.taskmanager.demo.model.Task;
import com.taskmanager.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("""
    SELECT DISTINCT u
    FROM User u
    LEFT JOIN FETCH u.tasks
    WHERE u.id = :id
""")
    Optional<User> findByIdWithTasks(Long id);

    List<Task> findByName(String name);
    List<Task> findByNameContaining(String name);

}
