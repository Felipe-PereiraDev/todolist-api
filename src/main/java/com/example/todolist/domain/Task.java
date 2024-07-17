package com.example.todolist.domain;

import com.example.todolist.dtos.TaskRequestDTO;
import com.example.todolist.dtos.TaskUpdateDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private boolean realizado;
    @Column(nullable = false)
    private int prioridade;

    public Task (TaskRequestDTO data) {
        this.nome = data.nome();
        this.descricao = data.descricao();
        this.prioridade = data.prioridade();
    }
    // Construtor padrão sem argumentos
    public Task() {
    }
    public void atualizarDados (TaskUpdateDTO data) {
        if (data.nome() != null) {
            this.nome = data.nome();
        }
        if (data.descricao() != null) {
            this.descricao = data.descricao();
        }
        if (data.realizado() != null) {
            this.realizado = data.realizado();
        }
        if (data.prioridade() != null) {
           this.prioridade = data.prioridade();
        }
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public Long getId() {
        return id;
    }
}
