package com.example.todo.task.model;

import com.example.todo.model.Category;
import com.example.todo.model.Priority;
import lombok.Builder;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
public class TaskCreateDto {

    @NotNull
    @Enumerated(EnumType.STRING)
    private Category category;
    @NotNull
    private String title;
    private Long habitId;
    private Date deadline;
    private String description;
    @Builder.Default
    @Enumerated(EnumType.ORDINAL)
    private Priority priority = Priority.COFFEA;

    @NotNull
    @Builder.Default
    private boolean isRecursive = false;


}
