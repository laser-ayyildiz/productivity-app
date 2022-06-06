package com.example.todo.task;

import com.example.producer.KafkaProducer;
import com.example.todo.task.model.TaskCreateDto;
import com.example.todo.task.model.TaskDto;
import com.example.todo.task.model.TaskUpdateDto;
import com.example.todo.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
//TODO change endpoint path
@RequestMapping(value = "${application.base-path}/task")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TodoController {

    private final TaskService taskService;
    private final KafkaProducer producer;

    //TODO serialize string
    @PostMapping()
    public ResponseEntity<TaskDto> createTask(@RequestBody @Valid TaskCreateDto task) {
        return new ResponseEntity<>(taskService.create(task), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable Long id) {
        return new ResponseEntity<>(taskService.get(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Page<TaskDto>> getAllTasks(@RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "0") int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return new ResponseEntity<>(taskService.getAllTasks(pageable), HttpStatus.OK);
    }

    @GetMapping("/habit/{id}")
    public ResponseEntity<List<TaskDto>> getAllTasksByHabitId(@PathVariable Long id) {
        return new ResponseEntity<>(taskService.getAllTByHabitId(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateTask(@PathVariable Long id, @RequestBody TaskUpdateDto updatedTask) {
        return new ResponseEntity(taskService.update(id, updatedTask), HttpStatus.OK);
    }

}

