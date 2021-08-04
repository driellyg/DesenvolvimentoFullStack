package com.banco.api.controller;

import com.banco.api.model.Conta;
import com.banco.api.repository.ContaRepository;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
class RelatorioController {

    private final ContaRepository contaRepository;

    RelatorioController(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @GetMapping("/relatorio")
    List<Conta> relatorio() {
        return this.contaRepository.findAll();
    }

}
