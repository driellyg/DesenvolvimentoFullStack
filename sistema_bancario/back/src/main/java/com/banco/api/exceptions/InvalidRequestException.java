package com.banco.api.exceptions;

public class InvalidRequestException extends RuntimeException {

    public InvalidRequestException() {
        super("Requisição não suportada");
    }
}
