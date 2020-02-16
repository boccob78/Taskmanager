package org.raitega.sample.taskmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * The DTO with a list of TaskViewItems
 * @author      Farzan Zubair
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskItems {

    private List<TaskViewItem> taskViewItems;
}
