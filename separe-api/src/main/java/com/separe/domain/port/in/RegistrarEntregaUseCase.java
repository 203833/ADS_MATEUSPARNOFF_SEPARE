package com.separe.domain.port.in;

import com.separe.domain.model.DadosColeta;
import com.separe.domain.model.Pedido;

public interface RegistrarEntregaUseCase {

    Pedido registrarColeta(String pedidoId, DadosColeta dadosColeta);

    Pedido registrarEntrega(String pedidoId);
}
