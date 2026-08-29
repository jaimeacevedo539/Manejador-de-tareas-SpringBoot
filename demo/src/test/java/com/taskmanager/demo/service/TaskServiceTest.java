package com.taskmanager.demo.service;

import com.taskmanager.demo.exception.TaskNotFoundException;
import com.taskmanager.demo.model.Task;
import com.taskmanager.demo.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void  shouldReturnTaskWhenTaskExists() {

        Task task = new Task(null,
                "Learn JPA",
                "Learn Hibernate"
        );

        when(taskRepository.findById(1L))
                .thenReturn(Optional.of(task));

        Task result = taskService.getTask(1L);

        assertNotNull(result);
        assertEquals("Learn JPA", result.getTitle());
        assertEquals("Learn Hibernate", result.getDescription());
        verify(taskRepository).findById(1L);
        verifyNoMoreInteractions(taskRepository);
    }

    @Test
    void shouldThrowExceptionWhenTaskDoesNotExist(){
        when(taskRepository.findById(99l))
                .thenReturn(Optional.empty());
        assertThrows(
                TaskNotFoundException.class,
                ()-> taskService.getTask(99L)
        );
    }
}