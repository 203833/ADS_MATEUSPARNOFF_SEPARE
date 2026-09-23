package com.separe.domain.port.out;

import com.separe.domain.model.HistoricoAcao;

import java.util.List;

public interface HistoricoAcaoRepositoryPort {

    HistoricoAcao salvar(HistoricoAcao historico);

    List<HistoricoAcao> buscarPorPedidoId(String pedidoId);

    List<HistoricoAcao> buscarPorMatriculaUsuario(String matricula);
}
