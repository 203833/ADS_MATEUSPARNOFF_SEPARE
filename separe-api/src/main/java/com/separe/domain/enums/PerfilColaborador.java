package com.separe.domain.enums;

public enum PerfilColaborador {

    ADMINISTRADOR("Administrador"),
    SEPARADOR("Separador");

    private final String descricao;

    PerfilColaborador(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
