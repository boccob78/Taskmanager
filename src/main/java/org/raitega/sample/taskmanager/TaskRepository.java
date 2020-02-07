package org.raitega.sample.taskmanager;

import org.raitega.sample.taskmanager.entities.Task;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(collectionResourceRel = "tasks", path = "task")
public interface TaskRepository extends PagingAndSortingRepository<Task, UUID> {

}
