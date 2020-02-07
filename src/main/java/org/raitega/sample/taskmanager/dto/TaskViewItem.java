package org.raitega.sample.taskmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.raitega.sample.taskmanager.entities.StatusType;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.SplittableRandom;

@Getter
@Setter
@NoArgsConstructor
public class TaskViewItem {

    private String id;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant dueDate;
    private Instant remindAt;
    private Instant resolvedAt;
    private String title;
    private String description;
    private String priority;
    private String status;

    public TaskViewItem(String title, String description, String priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = StatusType.CREATED.name();
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
        this.dueDate = Instant.now().plus(new SplittableRandom().nextLong(1, 10), ChronoUnit.DAYS);
    }

}
