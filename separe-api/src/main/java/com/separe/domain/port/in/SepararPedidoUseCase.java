package com.separe.domain.port.in;

import com.separe.domain.model.Pedido;

public interface SepararPedidoUseCase {

    Pedido iniciarSeparacao(String pedidoId, String matriculaSeparador);

    Pedido biparProduto(String pedidoId, String codigoBarras);

    Pedido concluirSeparacao(String pedidoId);
}
