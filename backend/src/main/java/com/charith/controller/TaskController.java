package com.charith.controller;

import com.charith.dto.TaskDTO;
import com.charith.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {

        return ResponseEntity.ok(taskService.createTask(taskDTO));
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> findMostRecentTasks() {
        return ResponseEntity.ok(taskService.findMostRecentTasks());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateTask(@PathVariable Long id,
                                             @RequestBody TaskDTO taskDTO) throws Exception {
        taskService.updateTaskStatus(id, taskDTO);
        return ResponseEntity.ok("Task status updated successfully");
    }
}
