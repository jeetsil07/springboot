package com.example.crudSpringBootDemo.exception;

import ch.qos.logback.classic.util.LogbackMDCAdapter;
import com.example.crudSpringBootDemo.dto.ExceptionResponseDto;
import com.example.crudSpringBootDemo.dto.ValidationExceptionResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleResourceNotFound(ResourceNotFoundException ex, HttpServletRequest request) {

        ExceptionResponseDto exceptionDto = new ExceptionResponseDto(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.
                status(HttpStatus.NOT_FOUND).
                body(exceptionDto);
    }
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ExceptionResponseDto> handleDuplicateResource(DuplicateResourceException ex) {
        ExceptionResponseDto exceptionDto = new ExceptionResponseDto(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                ""
        );
        return ResponseEntity.
                status(HttpStatus.CONFLICT).
                body(exceptionDto);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionResponseDto> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> fieldErrors = new java.util.HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> fieldErrors.put(error.getField(), error.getDefaultMessage()));
        ValidationExceptionResponseDto exceptionDto = new ValidationExceptionResponseDto(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation failed for one or more fields",
                request.getRequestURI(),
                fieldErrors
        );
        return ResponseEntity.
                status(HttpStatus.BAD_REQUEST).
                body(exceptionDto);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponseDto> handleRuntimeException(RuntimeException ex) {
        ExceptionResponseDto exceptionDto = new ExceptionResponseDto(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                ""
        );
        return ResponseEntity.
                status(HttpStatus.INTERNAL_SERVER_ERROR).
                body(exceptionDto);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDto> handleGenericException(Exception ex, HttpServletRequest request) {
        ExceptionResponseDto exceptionDto = new ExceptionResponseDto(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Something went wrong. Please try again later.",
                request.getRequestURI()
        );
        return ResponseEntity.
                status(HttpStatus.INTERNAL_SERVER_ERROR).
                body(exceptionDto);
    }
}
