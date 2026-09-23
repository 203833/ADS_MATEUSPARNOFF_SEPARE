package com.separe.domain.port.out;

import com.separe.domain.model.Ocorrencia;

import java.util.List;

public interface OcorrenciaRepositoryPort {

    Ocorrencia salvar(Ocorrencia ocorrencia);

    List<Ocorrencia> buscarPorPedidoId(String pedidoId);
}
