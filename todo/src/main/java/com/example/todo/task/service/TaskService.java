package com.example.todo.task.service;

import com.example.todo.task.model.TaskCreateDto;
import com.example.todo.task.model.TaskDto;
import com.example.todo.task.model.TaskUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService{

    TaskDto create(TaskCreateDto task);

    void delete(Long taskId);

    TaskDto get(Long taskId);

    Page<TaskDto> getAllTasks(Pageable pageable);

    List<TaskDto> getAllTByHabitId(Long id);

    TaskDto update(Long taskId, TaskUpdateDto updatedTask);
}
