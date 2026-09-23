package com.separe.domain.exception;

public class PedidoNaoEncontradoException extends RuntimeException {

    public PedidoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
