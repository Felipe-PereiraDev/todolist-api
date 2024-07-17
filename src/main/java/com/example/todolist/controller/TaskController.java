package com.example.todolist.controller;

import com.example.todolist.dtos.TaskRequestDTO;
import com.example.todolist.dtos.TaskResponseDTO;
import com.example.todolist.dtos.TaskUpdateDTO;
import com.example.todolist.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> list () {
        return ResponseEntity.ok().body(taskService.list());
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> create (@RequestBody @Valid TaskRequestDTO data,
                                                         UriComponentsBuilder uriComponentsBuilder) {
        var new_task = taskService.create(data);
        var uri =uriComponentsBuilder.path("/tasks/{id}")
                .buildAndExpand(new_task.getId()).toUri();
        return ResponseEntity.created(uri).body(new TaskResponseDTO(new_task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<List<TaskResponseDTO>> update (@PathVariable("id") Long id,
                                                         @RequestBody TaskUpdateDTO data) {
       return ResponseEntity.ok().body(taskService.update(id, data));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<List<TaskResponseDTO>> delete (@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(taskService.delete(id));
    }
}
