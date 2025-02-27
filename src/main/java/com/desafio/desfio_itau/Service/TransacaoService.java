package com.desafio.desfio_itau.Service;

import com.desafio.desfio_itau.DTO.TransacaoDTORequest;
import com.desafio.desfio_itau.Exceptions.UnprocessableEntity;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransacaoService {

    private final List<TransacaoDTORequest> transacoes = new ArrayList<>();

    public void receberTransacoes(TransacaoDTORequest dto){

        if (dto.dataHora().isAfter(OffsetDateTime.now())){
            throw new UnprocessableEntity("A transação não deve ocorrer no futuro");
        }

        if (dto.valor() < 0){
            throw new UnprocessableEntity("A transação não pode ter valor negativo");
        }

        transacoes.add(dto);
    }

    public void limparTransacoes(){
        transacoes.clear();
    }

    public List<TransacaoDTORequest> buscarTransacoes(Integer intervaloBusca){
        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(intervaloBusca);

        return transacoes.stream()
                .filter(transacoes -> transacoes.dataHora().isAfter(dataHoraIntervalo))
                .toList();
    }
}
