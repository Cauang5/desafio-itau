package com.desafio.desfio_itau.DTO;

public record EstatisticaDTOResponse(
        long count,
        double sum,
        double avg,
        double min,
        double max
) {
}
