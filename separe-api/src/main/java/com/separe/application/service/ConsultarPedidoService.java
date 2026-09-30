package com.separe.application.service;

import com.separe.domain.enums.StatusPedido;
import com.separe.domain.exception.PedidoNaoEncontradoException;
import com.separe.domain.model.Pedido;
import com.separe.domain.port.in.ConsultarPedidoUseCase;
import com.separe.domain.port.out.PedidoRepositoryPort;

import java.util.List;

public class ConsultarPedidoService implements ConsultarPedidoUseCase {

    private final PedidoRepositoryPort pedidoRepository;

    public ConsultarPedidoService(PedidoRepositoryPort pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public List<Pedido> listarPedidosDisponiveis() {
        return pedidoRepository.buscarPorStatusIn(List.of(
                StatusPedido.PEDIDO_RECEBIDO,
                StatusPedido.AGUARDANDO_SEPARACAO
        ));
    }

    @Override
    public Pedido buscarPorId(String id) {
        return pedidoRepository.buscarPorId(id)
                .orElseThrow(() -> new PedidoNaoEncontradoException(
                        "Pedido não encontrado com id: " + id));
    }
}
