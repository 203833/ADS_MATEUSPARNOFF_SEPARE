package com.separe.domain.exception;

public class AcessoBloqueadoException extends RuntimeException {

    public AcessoBloqueadoException(String mensagem) {
        super(mensagem);
    }
}
