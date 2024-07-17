package com.example.todolist.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> error404 () {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> erro400 (MethodArgumentNotValidException erro) {
        var erros = erro.getFieldErrors();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros.stream().map(DadosErros::new)
                .toList());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<?> handleIdNotFoundException(IdNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse("ID", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    public record DadosErros (String campo, String mensagem) {
        public DadosErros (FieldError erro){
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
    public record ErrorResponse(String campo, String message) {
    }

}

