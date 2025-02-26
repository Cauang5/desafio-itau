package com.desafio.desfio_itau.Service;

import com.desafio.desfio_itau.DTO.EstatisticaDTOResponse;
import com.desafio.desfio_itau.DTO.TransacaoDTORequest;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
public class EstatisticaService {

    private final TransacaoService transacaoService;

    public EstatisticaService(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    public EstatisticaDTOResponse calcularEstatisticas(Integer intervaloBusca) {

        List<TransacaoDTORequest> transacoes = transacaoService.buscarTransacoes(intervaloBusca);

        if (transacoes.isEmpty()) {
            return new EstatisticaDTOResponse(0, 0, 0, 0, 0);
        }

        DoubleSummaryStatistics estatisticasTransacoes = transacoes.stream()
                .mapToDouble(TransacaoDTORequest::valor).summaryStatistics();

        return new EstatisticaDTOResponse(
                estatisticasTransacoes.getCount(),
                estatisticasTransacoes.getSum(),
                estatisticasTransacoes.getAverage(),
                estatisticasTransacoes.getMin(),
                estatisticasTransacoes.getMax()
        );
    }
}
