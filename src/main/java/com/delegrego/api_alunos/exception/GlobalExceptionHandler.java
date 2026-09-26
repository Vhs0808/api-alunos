package com.delegrego.api_alunos.exception;

import com.delegrego.api_alunos.exception.response.ErroAtributo;
import com.delegrego.api_alunos.exception.response.ErroAtributoResponse;
import com.delegrego.api_alunos.exception.response.ErroResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(AlunoNaoEncontradoException.class)
    ResponseEntity<ErroResponse> handleAlunoNaoEncontradoException(AlunoNaoEncontradoException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErroResponse(404, ex.getMessage(), Instant.now()));
    }

    @ExceptionHandler(EmailDuplicadoException.class)
    ResponseEntity<ErroResponse> handleEmailDuplicadoException(EmailDuplicadoException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErroResponse(409, ex.getMessage(), Instant.now()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ErroAtributoResponse> handleValidationRequest(MethodArgumentNotValidException ex){
        List<ErroAtributo> errosAtributos =
                ex.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(erro ->
                                new ErroAtributo(erro.getField(),
                                        erro.getDefaultMessage())).toList();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErroAtributoResponse(
                        400, "Erro de validação", Instant.now(), errosAtributos
                ));
    }
}