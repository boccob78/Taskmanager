package org.raitega.sample.taskmanager.dto;

import lombok.*;
import org.raitega.sample.taskmanager.entities.StatusType;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.SplittableRandom;

/**
 * The DTO for the data to view layer conversion
 * @author      Farzan Zubair
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskViewItem {

    private String id;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private ZonedDateTime dueDate;
    private ZonedDateTime remindAt;
    private ZonedDateTime resolvedAt;
    private String title;
    private String description;
    private String priority;
    private String status;

    public TaskViewItem(String title, String description, String priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = StatusType.CREATED.name();
        this.createdAt = ZonedDateTime.now();
        this.updatedAt = ZonedDateTime.now();
        this.dueDate = ZonedDateTime.now().plus(new SplittableRandom().nextLong(1, 10), ChronoUnit.DAYS);
    }

}
