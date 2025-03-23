package com.iitj.autoscaleapp.controller;

import com.iitj.autoscaleapp.service.TaskService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Value("${gcp.instance.running:false}")
    private boolean isGcpRunning;

    @Value("${gcp.vm.ip:localhost}")
    private String gcpIp;

    // Task 1 - Always runs on Local VM
    @GetMapping("/task1")
    public String task1() {
        return taskService.executeTask("Task 1 (Local VM)");
    }

    // Task 2 - Always runs on Local VM
    @GetMapping("/task2")
    public String task2() {
        return taskService.executeTask("Task 2 (Local VM)");
    }

    // Task 3 - Moves to GCP if usage exceeds 75%
    @GetMapping("/task3")
    public String task3() {
        String url = isGcpRunning ? "http://" + gcpIp + ":8080/tasks/task3" : "http://localhost:8080/tasks/task3";
        return taskService.executeTask("Task 3", url);
    }

    // Task 4 - Moves to GCP if usage exceeds 75%
    @GetMapping("/task4")
    public String task4() {
        String url = isGcpRunning ? "http://" + gcpIp + ":8080/tasks/task4" : "http://localhost:8080/tasks/task4";
        return taskService.executeTask("Task 4", url);
    }
}

