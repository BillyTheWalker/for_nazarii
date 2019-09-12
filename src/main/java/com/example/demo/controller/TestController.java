package com.example.demo.controller;

import com.example.demo.taskengine.models.base.*;
import com.example.demo.taskengine.repository.ContextRepository;
import com.example.demo.taskengine.repository.TaskRepository;
import com.example.demo.taskengine.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskService taskService;

    @Autowired
    private ContextRepository contextRepository;

    @PostMapping(value = "/qwe")
    public ResponseEntity<String> test423() {
        return ResponseEntity.status(HttpStatus.LOCKED).body("pizdos");
    }

    @GetMapping(value = "/123")
    public ResponseEntity<String> test423get() {
        return ResponseEntity.status(HttpStatus.LOCKED).body("pizdos");
    }

    @PostMapping("/task")
    private ResponseEntity<Task> createTask(
            @RequestBody
                    ATask defaultTask
    ) {
        return ResponseEntity.ok(taskRepository.save(defaultTask));
    }

    @PostMapping("/btask")
    private ResponseEntity<Task> createBTask(
            @RequestBody
                    BTask defaultTask
    ) {
        return ResponseEntity.ok(taskRepository.save(defaultTask));
    }

    @PostMapping("/stask")
    private ResponseEntity<Task> createBTask(
            @RequestBody
                    AScheduledTask defaultTask
    ) {
        return ResponseEntity.ok(taskService.saveTask(defaultTask, true));
    }

    @PostMapping("/fttask")
    private ResponseEntity<Task> createBTask(
            @RequestBody
                    AFixedDateTask defaultTask
    ) {
        return ResponseEntity.ok(taskService.saveTask(defaultTask, true));
    }

    @PostMapping("/context")
    private ResponseEntity<Context> createContext(
            @RequestBody
                    AContext defaultTask
    ) {
        return ResponseEntity.ok(contextRepository.save(defaultTask));
    }

    @PostMapping("/bcontext")
    private ResponseEntity<Context> createBContext(
            @RequestBody
                    BContext defaultTask
    ) {
        return ResponseEntity.ok(contextRepository.save(defaultTask));
    }
}
