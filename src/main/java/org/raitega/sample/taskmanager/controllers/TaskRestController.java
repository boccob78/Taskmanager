package org.raitega.sample.taskmanager.controllers;

import lombok.extern.slf4j.Slf4j;
import org.raitega.sample.taskmanager.dto.TaskItems;
import org.raitega.sample.taskmanager.repository.TaskRepository;
import org.raitega.sample.taskmanager.dto.TaskViewItem;
import org.raitega.sample.taskmanager.entities.StatusType;
import org.raitega.sample.taskmanager.entities.Task;
import org.raitega.sample.taskmanager.util.ObjectMapperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * REST controller for managing the CRUD operations
 * for the Task Repository. A separate controller is used to map the DTO to Entity
 * instead of a Spring repository with REST annotations
 *
 * @author      Farzan Zubair
 */

@Slf4j
@RestController
@RequestMapping(value = "/task")
public class TaskRestController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/all")
    public TaskItems findAllTasks() {
        final List<Task> taskList = taskRepository.findAll(Sort.by(Sort.Direction.ASC, "dueDate").and(Sort.by(Sort.Direction.ASC, "priority")));
        return new TaskItems(ObjectMapperUtils.mapAll(taskList, TaskViewItem.class));
    }

    /**
     *
     * @param id
     * @param status
     */
    @PutMapping("/{id}/{status}")
    public void updateTaskStatus(@PathVariable final String id, @PathVariable final StatusType status) {
        final Task task = taskRepository.findById(UUID.fromString(id)).get();
        task.setStatus(status);
        task.setUpdatedAt(ZonedDateTime.now());
        if (task.getStatus() == StatusType.COMPLETED) {
            task.setResolvedAt(ZonedDateTime.now());
        }
        this.taskRepository.save(task);
        log.info("Task updated successfully. id {}", id);
    }

    /**
     *
     * @param id
     * @param taskViewItem
     */
    @PutMapping("/{id}")
    public void updateTask(@PathVariable final String id, @RequestBody final TaskViewItem taskViewItem) {
        final Task task = taskRepository.findById(UUID.fromString(id)).get();
        ObjectMapperUtils.map(taskViewItem, task);
        task.setUpdatedAt(ZonedDateTime.now());
        if (task.getStatus() == StatusType.COMPLETED) {
            task.setResolvedAt(ZonedDateTime.now());
        }
        this.taskRepository.save(task);
        log.info("Task updated successfully. id {}", id);
    }

    /**
     *
     * @param id
     * @param date
     */
    @PutMapping("/defer/{id}/{date}")
    public void deferTask(@PathVariable final String id, @PathVariable final String date) {
        final Task task = taskRepository.findById(UUID.fromString(id)).get();
        task.setStatus(StatusType.DEFERRED);
        SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date formatDate = sft.parse(date);
            task.setRemindAt(formatDate.toInstant().atZone(ZoneId.systemDefault()));
        } catch (ParseException e) {
            log.error(e.toString(), e);
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "Date not understood", e);
        }
        log.info("task id: "+id+" date: "+date);;

        task.setUpdatedAt(ZonedDateTime.now());
        if (task.getStatus() == StatusType.COMPLETED) {
            task.setResolvedAt(ZonedDateTime.now());
        }
        this.taskRepository.save(task);
        log.info("Task updated successfully. id {}", id);
    }


    /**
     *
     * @param id
     */
    @PutMapping("/resolve/{id}")
    public void resolveTask(@PathVariable final String id) {
        updateTaskStatus(id, StatusType.COMPLETED);
    }

    /**
     *
     * @param id
     */
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable final String id) {
        final Task task = taskRepository.findById(UUID.fromString(id)).get();
        if(task.getStatus() == StatusType.DELETED) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Task already deleted");
        }
        task.setStatus(StatusType.DELETED);
        this.taskRepository.save(task);
        log.info("Task deleted successfully. id {}", id);
    }

}
