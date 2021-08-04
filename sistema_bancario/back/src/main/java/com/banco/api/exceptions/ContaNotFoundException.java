package com.banco.api.exceptions;

public class ContaNotFoundException extends RuntimeException {

    public ContaNotFoundException(Long id) {
        super("Could not find conta " + id);
    }
}
