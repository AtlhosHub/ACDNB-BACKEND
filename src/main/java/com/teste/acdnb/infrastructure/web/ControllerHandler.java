package com.teste.acdnb.infrastructure.web;

import com.teste.acdnb.core.application.exception.DataConflictException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ControllerHandler {
    @ExceptionHandler(DataConflictException.class)
    public ResponseEntity<Map<String, String>> duplicidade(DataConflictException ex) {
        Map<String, String> error = Map.of(
                "erro:", "Erro de duplicidade: %s".formatted(ex.getMessage())
        );

        return ResponseEntity.status(409).body(error);
    }
}
