package com.banco.api;

import com.banco.api.model.*;
import com.banco.api.repository.ContaRepository;
import com.banco.api.repository.PessoaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(PessoaRepository pessoaRepository, ContaRepository contaRepository) {
        return args -> {
            Pessoa pessoaFisica = pessoaRepository.save(new PessoaFisica("Vinícius Alencar", "Rua J2, qd.15 lote 30, Aruaña 1", "masculino", "05196072101", new Date()));
            Pessoa pessoaJuridica = pessoaRepository.save(new PessoaJuridica("Vinícius Alencar", "Rua J2, qd.15 lote 30, Aruaña 1", "05196072101", "Desenvolvedor de sistemas"));

            log.info(
                    "Preloading " + pessoaFisica
            );
            log.info(
                    "Preloading " + pessoaJuridica
            );
            log.info(
                    "Preloading " + contaRepository.save(new ContaPoupanca(1L, pessoaFisica, 0.0, 0.85))
            );
            log.info(
                    "Preloading " + contaRepository.save(new ContaEspecial(2L, pessoaJuridica, 0.0, 4000.0))
            );
        };
    }
}
