package org.raitega.sample.taskmanager.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * A simple Controller for Spring to map the homepage
 * @author      Farzan Zubair
 */

@Controller
@Slf4j
public class TaskController {

    /**
     * Returns the view id for tasks
     * @return String
     */

    @GetMapping("/")
    public String start() {
        return "tasks";
    }

}
