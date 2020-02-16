package org.raitega.sample.taskmanager.repository;

import org.raitega.sample.taskmanager.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * The Spring data repository. No REST annotations are used here to separate
 * The entity to DTO layers (a requirement)
 *
 * @author      Farzan Zubair
 */

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

}
