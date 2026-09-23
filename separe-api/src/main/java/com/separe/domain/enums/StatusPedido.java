package com.separe.domain.enums;

public enum StatusPedido {

    PEDIDO_RECEBIDO("Pedido Recebido"),
    AGUARDANDO_SEPARACAO("Aguardando Separação"),
    EM_SEPARACAO("Em Separação"),
    PRONTO_PARA_RETIRADA("Pronto para Retirada"),
    AGUARDANDO_COLETA("Aguardando Coleta"),
    EM_ROTA_DE_ENTREGA("Em Rota de Entrega"),
    ENTREGUE("Entregue");

    private final String descricao;

    StatusPedido(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
