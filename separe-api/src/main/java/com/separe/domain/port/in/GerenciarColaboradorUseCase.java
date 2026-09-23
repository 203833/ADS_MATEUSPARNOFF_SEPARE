package com.separe.domain.port.in;

import com.separe.domain.model.Colaborador;

import java.util.List;

public interface GerenciarColaboradorUseCase {

    Colaborador cadastrar(String nome, String email);

    Colaborador buscarPorId(String id);

    Colaborador buscarPorMatricula(String matricula);

    List<Colaborador> listarTodos();

    Colaborador editar(String id, String nome, String email);

    void bloquear(String id);

    void desbloquear(String id);
}
