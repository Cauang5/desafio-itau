package com.desafio.desfio_itau.DTO;

import java.time.OffsetDateTime;

public record TransacaoDTORequest(
        Double valor,
        OffsetDateTime dataHora
) {

}
