package com.separe.domain.port.in;

import com.separe.domain.model.Ocorrencia;

import java.util.List;

public interface RegistrarOcorrenciaUseCase {

    Ocorrencia registrar(String pedidoId, String produtoNome, String codigoBarras,
                         String motivo, int quantidadeNecessaria, int quantidadeDisponivel,
                         String matriculaFuncionario, String observacao);

    List<Ocorrencia> listarPorPedido(String pedidoId);
}
