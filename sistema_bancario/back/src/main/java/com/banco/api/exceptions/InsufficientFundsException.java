package com.banco.api.exceptions;

public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException(Long id) {
        super("Saldo insuficiente para conta " + id);
    }
}
