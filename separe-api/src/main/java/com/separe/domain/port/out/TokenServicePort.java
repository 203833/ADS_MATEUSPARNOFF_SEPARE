package com.separe.domain.port.out;

import com.separe.domain.model.Colaborador;

public interface TokenServicePort {

    String gerarToken(Colaborador colaborador);

    String extrairMatricula(String token);

    boolean isTokenValido(String token);
}
