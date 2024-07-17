package com.example.todolist.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskRequestDTO(
        @NotBlank
        String nome,
        @NotBlank
        String descricao,
        @NotNull
        int prioridade
) {
}
