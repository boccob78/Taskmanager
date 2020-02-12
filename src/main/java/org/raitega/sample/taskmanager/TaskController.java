package org.raitega.sample.taskmanager;

import org.raitega.sample.taskmanager.dto.TaskViewItem;
import org.raitega.sample.taskmanager.entities.StatusType;
import org.raitega.sample.taskmanager.entities.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.xml.ws.Response;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/task")
public class TaskController {

    private static final Logger LOG = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/all")
    public List<TaskViewItem> findAllTasks() {
        final List<Task> taskList = taskRepository.findAll();
        return ObjectMapperUtils.mapAll(taskList, TaskViewItem.class);
    }

    /**
     *
     * @param id
     * @param status
     */
    @PutMapping("/{id}")
    public void updateTask(@PathVariable final String id, @RequestBody final StatusType status) {
        final Task task = taskRepository.findById(UUID.fromString(id)).get();
        task.setStatus(status);
        task.setUpdatedAt(Instant.now());
        if (task.getStatus() == StatusType.COMPLETED) {
            task.setResolvedAt(Instant.now());
        }
        this.taskRepository.save(task);
        LOG.info("Task updated successfully. id {}", id);
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
        LOG.info("Task deleted successfully. id {}", id);
    }

}
