package com.delegrego.api_alunos.exception.response;

import java.time.Instant;

public record ErroResponse(

        int status,
        String mensagem,
        Instant timestamp

) {}
