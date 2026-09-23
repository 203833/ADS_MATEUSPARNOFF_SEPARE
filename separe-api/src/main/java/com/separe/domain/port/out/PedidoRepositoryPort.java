package com.separe.domain.port.out;

import com.separe.domain.enums.StatusPedido;
import com.separe.domain.model.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoRepositoryPort {

    Pedido salvar(Pedido pedido);

    Optional<Pedido> buscarPorId(String id);

    List<Pedido> buscarPorStatus(StatusPedido status);

    List<Pedido> buscarPorStatusIn(List<StatusPedido> statuses);

    List<Pedido> listarTodos();
}
