package com.banco.api.exceptions;

public class PessoaNotFoundException extends RuntimeException {

    public PessoaNotFoundException(Long id) {
        super("Could not find pessoa " + id);
    }
}
