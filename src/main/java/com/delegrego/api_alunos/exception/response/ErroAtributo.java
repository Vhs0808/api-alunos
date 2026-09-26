package com.delegrego.api_alunos.exception.response;

public record ErroAtributo(
        String atributo,
        String mensagem
) {
}
