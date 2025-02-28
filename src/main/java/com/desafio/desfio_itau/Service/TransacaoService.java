package com.desafio.desfio_itau.Service;

import com.desafio.desfio_itau.DTO.TransacaoDTORequest;
import com.desafio.desfio_itau.Exceptions.UnprocessableEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class TransacaoService {

    private static final Logger log = LogManager.getLogger();
    private final List<TransacaoDTORequest> transacoes = new ArrayList<>();

    public void receberTransacoes(TransacaoDTORequest dto){

        log.info("Recebendo uma nova transação " +dto);
        if (dto.dataHora().isAfter(OffsetDateTime.now())){
            log.error("Data e hora maiores que a data atual");
            throw new UnprocessableEntity("A transação não deve ocorrer no futuro");
        }

        if (dto.valor() < 0){
            log.error("Valor não pode ser menor do que 0");
            throw new UnprocessableEntity("A transação não pode ter valor negativo");
        }

        transacoes.add(dto);
        log.info("Transações realizadas com sucesso!");
    }

    public void limparTransacoes(){
        log.info("iniciando a limpeza das transações");
        transacoes.clear();
        log.info("Finalizado a limpeza das transações");
    }

    public List<TransacaoDTORequest> buscarTransacoes(Integer intervaloBusca){
        log.info("Buscando transações a partir do intervalo de tempo: " +intervaloBusca);
        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(intervaloBusca);

        log.info("Transações buscadas com sucesso!");
        return transacoes.stream()
                .filter(transacoes -> transacoes.dataHora().isAfter(dataHoraIntervalo))
                .toList();
    }
}
