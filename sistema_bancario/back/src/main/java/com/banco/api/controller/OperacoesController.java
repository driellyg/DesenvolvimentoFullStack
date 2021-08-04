package com.banco.api.controller;

import com.banco.api.enums.OperacaoEnum;
import com.banco.api.exceptions.InsufficientFundsException;
import com.banco.api.exceptions.InvalidRequestException;
import com.banco.api.model.Conta;
import com.banco.api.model.Operacao;
import com.banco.api.repository.ContaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
class OperacoesController {

    private final ContaRepository contaRepository;

    OperacoesController(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @PostMapping("/operacao")
    Conta operacao(
            @RequestBody Operacao operacao
    ) {
        Map<String, Object> payload = operacao.getPayload();

        if (operacao.getOperacao() == OperacaoEnum.SAQUE) {
            Long idContaOrigem = Long.valueOf((Integer) payload.get("contaOrigem"));
            Conta conta = this.contaRepository.findByNumeroConta(idContaOrigem);

            Double valor = Double.valueOf(String.valueOf(payload.get("valor")));

            if (valor > conta.getSaldo()) {
                throw new InsufficientFundsException(idContaOrigem);
            }

            conta.setSaldo(conta.getSaldo() - valor);

            return this.contaRepository.save(conta);
        } else if (operacao.getOperacao() == OperacaoEnum.DEPOSITO) {
            Long contaDestino = Long.valueOf((Integer) payload.get("contaDestino"));
            Conta conta = this.contaRepository.findByNumeroConta(contaDestino);

            Double valor = Double.valueOf(String.valueOf(payload.get("valor")));
            conta.setSaldo(conta.getSaldo() + valor);

            return this.contaRepository.save(conta);
        } else if (operacao.getOperacao() == OperacaoEnum.TRANSFERENCIA) {
            Long idContaOrigem = Long.valueOf((Integer) payload.get("contaOrigem"));
            Long idContaDestino = Long.valueOf((Integer) payload.get("contaDestino"));
            Conta contaOrigem = this.contaRepository.findByNumeroConta(idContaOrigem);

            Double valor = Double.valueOf(String.valueOf(payload.get("valor")));
            if (valor > contaOrigem.getSaldo()) {
                throw new InsufficientFundsException(idContaOrigem);
            }

            Conta contaDestino = this.contaRepository.findByNumeroConta(idContaDestino);

            contaOrigem.setSaldo(contaOrigem.getSaldo() - valor);
            contaDestino.setSaldo(contaDestino.getSaldo() + valor);

            this.contaRepository.save(contaDestino);
            return this.contaRepository.save(contaOrigem);
        }

        throw new InvalidRequestException();
    }

}
