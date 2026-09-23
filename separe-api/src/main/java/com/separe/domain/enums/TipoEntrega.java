package com.separe.domain.enums;

public enum TipoEntrega {

    RAPIDA("Rápida"),
    PADRAO("Padrão"),
    RETIRADA_EM_LOJA("Retirada em Loja");

    private final String descricao;

    TipoEntrega(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
