package com.example.todo.task.service;

import com.example.todo.model.User;
import com.example.todo.producer.EventProducer;
import com.example.todo.task.TaskRepository;
import com.example.todo.task.model.Task;
import com.example.todo.task.model.TaskCreateDto;
import com.example.todo.task.model.TaskCreatedEvent;
import com.example.todo.task.model.TaskDto;
import com.example.todo.task.model.TaskUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final User userFromRequest;
    private final EventProducer eventProducer;

    @Override
    public TaskDto create(TaskCreateDto taskCreateRequest) {
        Task task = taskRepository.save(Task.of(taskCreateRequest, userFromRequest.getId()));
        eventProducer.sendMessage(TaskCreatedEvent.of(task, false));
        return TaskDto.of(task);
    }

    @Override
    public void delete(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(RuntimeException::new);
        eventProducer.sendMessage(TaskCreatedEvent.of(task, true));
        taskRepository.delete(task);
    }

    //TODO make custom exception class for all exceptions
    @Override
    public TaskDto get(Long taskId) {
        Optional<Task> maybeTask = taskRepository.findById(taskId);
        if (!maybeTask.isPresent()) {
            throw new RuntimeException();
        }
        return TaskDto.of(maybeTask.get());
    }

    @Override
    public Page<TaskDto> getAllTasks(Pageable pageable) {
        Page<Task> tasks = taskRepository.findByUserId(pageable, userFromRequest.getId());
        return new PageImpl<>(tasks.getContent().stream().map(TaskDto::of).collect(Collectors.toList()), tasks.getPageable(), tasks.getTotalElements());
    }

    @Override
    public List<TaskDto> getAllTByHabitId(Long id) {
        return taskRepository.findByHabitId(id).stream().map(TaskDto::of).collect(Collectors.toList());
    }

    @Override
    public TaskDto update(Long taskId, TaskUpdateDto updatedTask) {
        Task task = taskRepository.findById(taskId).orElseThrow(RuntimeException::new);
        eventProducer.sendMessage(TaskCreatedEvent.of(task, false));
        return TaskDto.of(taskRepository.save(Task.of(updatedTask, userFromRequest.getId(), task.getId(), task.getCreatedDate())));
    }

}
