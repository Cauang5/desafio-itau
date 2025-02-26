package com.desafio.desfio_itau.Controller;

import com.desafio.desfio_itau.DTO.EstatisticaDTOResponse;
import com.desafio.desfio_itau.Service.EstatisticaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatistica")
public class EstatisticasController {

    private final EstatisticaService estatisticaService;

    public EstatisticasController(EstatisticaService estatisticaService) {
        this.estatisticaService = estatisticaService;
    }

    @GetMapping
    public ResponseEntity<EstatisticaDTOResponse> obterEstatisticas(@RequestParam Integer intervaloBusca){
        EstatisticaDTOResponse estatisticas= estatisticaService.calcularEstatisticas(intervaloBusca);
        return ResponseEntity.ok(estatisticas);
    }
}
