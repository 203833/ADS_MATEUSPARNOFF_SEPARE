package com.separe.domain.port.in;

import com.separe.domain.model.HistoricoAcao;

import java.util.List;

public interface ConsultarHistoricoUseCase {

    List<HistoricoAcao> listarPorPedido(String pedidoId);

    List<HistoricoAcao> listarPorColaborador(String matricula);
}
