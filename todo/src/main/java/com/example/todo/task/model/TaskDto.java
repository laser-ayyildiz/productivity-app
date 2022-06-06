package com.example.todo.task.model;

import com.example.todo.model.BaseEntity;
import com.example.todo.model.Category;
import com.example.todo.model.Priority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto extends BaseEntity {

    private Long userId;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String title;
    private Date deadline;
    private String description;
    @Enumerated(EnumType.ORDINAL)
    private Priority priority;
    private boolean isRecursive;

    public static TaskDto of(Task task) {
        return TaskDto.builder()
                .id(task.getId())
                .userId(task.getUserId())
                .title(task.getTitle())
                .description(task.getDescription())
                .category(task.getCategory())
                .priority(task.getPriority())
                .deadline(task.getDeadline())
                .isRecursive(task.getIsRecursive())
                .createdDate(task.getCreatedDate())
                .lastModifiedDate(task.getLastModifiedDate())
                .build();
    }
}
