package com.separe.application.service;

import com.separe.domain.exception.PedidoNaoEncontradoException;
import com.separe.domain.model.Ocorrencia;
import com.separe.domain.port.in.RegistrarOcorrenciaUseCase;
import com.separe.domain.port.out.OcorrenciaRepositoryPort;
import com.separe.domain.port.out.PedidoRepositoryPort;

import java.util.List;

public class RegistrarOcorrenciaService implements RegistrarOcorrenciaUseCase {

    private final OcorrenciaRepositoryPort ocorrenciaRepository;
    private final PedidoRepositoryPort pedidoRepository;

    public RegistrarOcorrenciaService(OcorrenciaRepositoryPort ocorrenciaRepository,
                                      PedidoRepositoryPort pedidoRepository) {
        this.ocorrenciaRepository = ocorrenciaRepository;
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public Ocorrencia registrar(String pedidoId, String produtoNome, String codigoBarras,
                                String motivo, int quantidadeNecessaria, int quantidadeDisponivel,
                                String matriculaFuncionario, String observacao) {

        pedidoRepository.buscarPorId(pedidoId)
                .orElseThrow(() -> new PedidoNaoEncontradoException(
                        "Pedido não encontrado com id: " + pedidoId));

        Ocorrencia ocorrencia = new Ocorrencia(
                pedidoId, produtoNome, codigoBarras, motivo,
                quantidadeNecessaria, quantidadeDisponivel,
                matriculaFuncionario, observacao);

        return ocorrenciaRepository.salvar(ocorrencia);
    }

    @Override
    public List<Ocorrencia> listarPorPedido(String pedidoId) {
        return ocorrenciaRepository.buscarPorPedidoId(pedidoId);
    }
}
