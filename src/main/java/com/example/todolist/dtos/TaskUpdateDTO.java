package com.example.todolist.dtos;


public record TaskUpdateDTO (
        String nome,
        String descricao,
        Boolean realizado,
        Integer prioridade
){
}
