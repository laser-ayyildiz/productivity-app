package com.example.todo.task.model;

import com.example.todo.model.BaseEntity;
import com.example.todo.model.Category;
import com.example.todo.model.Priority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task")
public class Task extends BaseEntity {


    private Long userId;
    private Long habitId;
    private String title;
    private String description;
    @Enumerated(EnumType.ORDINAL)
    private Priority priority;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH.mm")
    private Date deadline;
    @Enumerated(EnumType.STRING)
    private Category category;
    private Boolean isRecursive;
    @Enumerated(EnumType.STRING)
    private TaskColor color;

    public static Task of(TaskCreateDto task, Long userId) {
        return Task.builder()
                .userId(userId)
                .title(task.getTitle())
                .habitId(task.getHabitId())
                .description(task.getDescription())
                .category(task.getCategory())
                .priority(task.getPriority())
                .deadline(task.getDeadline())
                .isRecursive(task.isRecursive())
                .color(task.getColor())
                .build();
    }

    public static Task of(TaskUpdateDto task, Long userId, Long taskId, Date createdDate) {
        return Task.builder()
                .id(taskId)
                .userId(userId)
                .title(task.getTitle())
                .habitId(task.getHabitId())
                .description(task.getDescription())
                .category(task.getCategory())
                .priority(task.getPriority())
                .deadline(task.getDeadline())
                .createdDate(createdDate)
                .isRecursive(task.isRecursive())
                .color(task.getColor())
                .build();

    }
}
