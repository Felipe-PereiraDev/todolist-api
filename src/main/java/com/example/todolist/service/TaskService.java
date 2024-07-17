package com.example.todolist.service;

import com.example.todolist.domain.Task;
import com.example.todolist.dtos.TaskRequestDTO;
import com.example.todolist.dtos.TaskResponseDTO;
import com.example.todolist.dtos.TaskUpdateDTO;
import com.example.todolist.infra.IdNotFoundException;
import com.example.todolist.repository.TaskRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    // Criar uma nova tarefa
    public Task create (TaskRequestDTO dataRequest) {
        return repository.save(new Task(dataRequest));
    }

    // Listar todas as tarefas
    public List<TaskResponseDTO> list () {
        Sort sort = Sort.by("prioridade").ascending();
        var list_task = repository.findAll(sort).stream().map(TaskResponseDTO::new).toList();;
        return  list_task;
    }

    // atualizar tarefa
    public List<TaskResponseDTO> update (Long id, TaskUpdateDTO data) {
        Optional<Task> task = repository.findById(id);
        // verifica se existe uma tarefa com esse id, caso contrário lança uma exceção
        if (task.isPresent()) {
            var taskATT = task.get();
            taskATT.atualizarDados(data);
            repository.save(taskATT);
            System.out.println(data);
        }
        else {
            throw new IdNotFoundException("não encontrado");
        }
        return list();
    }

    public List<TaskResponseDTO> delete (Long id) {
        Optional<Task> task = repository.findById(id);
        if (task.isPresent()) {
            repository.deleteById(id);
        }
        else {
            throw new IdNotFoundException("não encontrado");
        }
        return list();
    }
}
