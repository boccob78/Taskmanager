package org.raitega.sample.taskmanager.scheduler;

import org.modelmapper.ModelMapper;
import org.raitega.sample.taskmanager.repository.TaskRepository;
import org.raitega.sample.taskmanager.dto.TaskViewItem;
import org.raitega.sample.taskmanager.entities.PriorityType;
import org.raitega.sample.taskmanager.entities.Task;
import org.raitega.sample.taskmanager.util.ObjectMapperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.SplittableRandom;

/**
 * The scheduled service which generates random tasks
 * @author      Farzan Zubair
 */

@Component
public class ScheduledTask {

    private static final Logger LOG = LoggerFactory.getLogger(ScheduledTask.class);

    private int taskCounter;

    @Autowired
    private TaskRepository taskRepository;

    /**
     * Random task will be created every 15 and 30 seconds
     *
     */
    @Scheduled(fixedDelayString = "${random.int[15000,30000]}")
    public void createRandomTask() {

        final TaskViewItem taskViewItem = new TaskViewItem("Task "+(++taskCounter), "Task Description "+taskCounter, PriorityType.values()[new SplittableRandom().nextInt(1, PriorityType.values().length)].name());

        LOG.info("taskViewItem created: {} ", taskViewItem.getTitle());
        try {
            final Task savedTask = this.taskRepository.save(ObjectMapperUtils.map(taskViewItem, Task.class));
            LOG.info("Task due date is: {} ", savedTask.getDueDate());
            LOG.info("Task is successfully created with id: {} ", savedTask.getId());
        } catch (final DataIntegrityViolationException e) {
            LOG.error("Failed to created scheduled task.");
        }
    }
}
