package com.teste.acdnb.infrastructure.web;

import com.teste.acdnb.core.application.exception.DataConflictException;
import com.teste.acdnb.core.application.exception.InvalidDataException;
import com.teste.acdnb.core.application.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ControllerHandler {
    @ExceptionHandler(DataConflictException.class)
    public ResponseEntity<Map<String, Object>> dataConflict(DataConflictException ex) {
        Map<String, Object> error = Map.of(
                "timestamp", java.time.LocalDateTime.now(),
                "status", 409,
                "error", "Conflict",
                "message", ex.getMessage()
        );

        return ResponseEntity.status(409).body(error);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> resourceNotFound(ResourceNotFoundException ex) {
        Map<String, Object> error = Map.of(
                "timestamp", java.time.LocalDateTime.now(),
                "status", 404,
                "error", "Not Found",
                "message", ex.getMessage()
        );

        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<Map<String, Object>> invalidDataException(InvalidDataException ex) {
        Map<String, Object> error = Map.of(
                "timestamp", java.time.LocalDateTime.now(),
                "status", 400,
                "error", "Bad Request",
                "message", ex.getMessage()
        );

        return ResponseEntity.status(400).body(error);
    }
}
