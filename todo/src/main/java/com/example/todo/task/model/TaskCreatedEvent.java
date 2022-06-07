package com.example.todo.task.model;

import com.example.todo.util.BaseMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskCreatedEvent {

    private Long id;

    private Long userId;

    private Date createdAt;

    private Date deadline;

    private Boolean isDeleted;

    public static String of(Task task, Boolean isDeleted) {
        TaskCreatedEvent event = TaskCreatedEvent.builder()
                .id(task.getId())
                .userId(task.getUserId())
                .createdAt(task.getCreatedDate())
                .deadline(task.getDeadline())
                .isDeleted(isDeleted)
                .build();
        return BaseMapper.mapToJSON(event);
    }
}
