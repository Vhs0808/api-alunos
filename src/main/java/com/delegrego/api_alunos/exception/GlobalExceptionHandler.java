package com.delegrego.api_alunos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlunoNaoEncontradoException.class)
    public ResponseEntity<ErroResponse> handleAlunoNaoEncontradoException(AlunoNaoEncontradoException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErroResponse(404, ex.getMessage(), Instant.now()));
    }

    @ExceptionHandler(EmailDuplicadoException.class)
    public ResponseEntity<ErroResponse> handleEmailDuplicadoException(EmailDuplicadoException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErroResponse(409, ex.getMessage(), Instant.now()));
    }

}
