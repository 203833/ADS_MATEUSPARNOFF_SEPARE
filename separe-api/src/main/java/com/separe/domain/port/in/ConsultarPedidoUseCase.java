package com.separe.domain.port.in;

import com.separe.domain.model.Pedido;

import java.util.List;

public interface ConsultarPedidoUseCase {

    List<Pedido> listarPedidosDisponiveis();

    Pedido buscarPorId(String id);
}
