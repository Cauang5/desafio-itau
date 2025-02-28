package com.desafio.desfio_itau.Service;

import com.desafio.desfio_itau.DTO.EstatisticaDTOResponse;
import com.desafio.desfio_itau.DTO.TransacaoDTORequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
public class EstatisticaService {

    private static final Logger log = LogManager.getLogger();
    private final TransacaoService transacaoService;

    public EstatisticaService(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    public EstatisticaDTOResponse calcularEstatisticas(Integer intervaloBusca) {
        log.info("Iniciando a busca das estatisticas pelo intervalo de tempo");
        List<TransacaoDTORequest> transacoes = transacaoService.buscarTransacoes(intervaloBusca);

        long tempoInicial = System.currentTimeMillis();

        if (transacoes.isEmpty()) {
            return new EstatisticaDTOResponse(0, 0, 0, 0, 0);
        }

        DoubleSummaryStatistics estatisticasTransacoes = transacoes.stream()
                .mapToDouble(TransacaoDTORequest::valor).summaryStatistics();

        long tempoFinal = System.currentTimeMillis();
        long tempoRequisicao = tempoFinal - tempoInicial;
        System.out.println("Tempo de requisição: " + tempoRequisicao + " ms");

        log.info("Estatisticas retornada com sucesso!");
        return new EstatisticaDTOResponse(
                estatisticasTransacoes.getCount(),
                estatisticasTransacoes.getSum(),
                estatisticasTransacoes.getAverage(),
                estatisticasTransacoes.getMin(),
                estatisticasTransacoes.getMax()
        );
    }
}
