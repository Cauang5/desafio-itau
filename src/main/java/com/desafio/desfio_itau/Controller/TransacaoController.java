package com.desafio.desfio_itau.Controller;

import com.desafio.desfio_itau.DTO.TransacaoDTORequest;
import com.desafio.desfio_itau.Service.TransacaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")

public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    public ResponseEntity<Void> receberTransacao(@RequestBody TransacaoDTORequest dto){
        transacaoService.receberTransacoes(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> limparTransacoes(){
        transacaoService.limparTransacoes();
        return ResponseEntity.ok().build();
    }
}
