package com.separe.domain.exception;

public class ProdutoNaoPertenceAoPedidoException extends RuntimeException {

    public ProdutoNaoPertenceAoPedidoException(String mensagem) {
        super(mensagem);
    }
}
