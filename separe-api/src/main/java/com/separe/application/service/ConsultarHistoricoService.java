package com.separe.application.service;

import com.separe.domain.model.HistoricoAcao;
import com.separe.domain.port.in.ConsultarHistoricoUseCase;
import com.separe.domain.port.out.HistoricoAcaoRepositoryPort;

import java.util.List;

public class ConsultarHistoricoService implements ConsultarHistoricoUseCase {

    private final HistoricoAcaoRepositoryPort historicoRepository;

    public ConsultarHistoricoService(HistoricoAcaoRepositoryPort historicoRepository) {
        this.historicoRepository = historicoRepository;
    }

    @Override
    public List<HistoricoAcao> listarPorPedido(String pedidoId) {
        return historicoRepository.buscarPorPedidoId(pedidoId);
    }

    @Override
    public List<HistoricoAcao> listarPorColaborador(String matricula) {
        return historicoRepository.buscarPorMatriculaUsuario(matricula);
    }
}
