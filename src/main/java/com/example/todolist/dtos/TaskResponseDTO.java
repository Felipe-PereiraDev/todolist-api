package com.example.todolist.dtos;

import com.example.todolist.domain.Task;

public record TaskResponseDTO (
        Long task_id,
        String nome,
        String descricao,
        Boolean realizado,
        int prioridade
) {
    public TaskResponseDTO (Task data) {
        this (
                data.getId(),
                data.getNome(),
                data.getDescricao(),
                data.isRealizado(),
                data.getPrioridade()
        );
    }
}
