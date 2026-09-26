package com.delegrego.api_alunos.exception.response;

import java.time.Instant;
import java.util.List;

public record ErroAtributoResponse(
        int status,
        String mensagem,
        Instant timestamp,
        List<ErroAtributo> errosAtributos
) {
}
