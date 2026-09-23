package com.separe.domain.port.out;

import com.separe.domain.model.Colaborador;

import java.util.List;
import java.util.Optional;

public interface ColaboradorRepositoryPort {

    Colaborador salvar(Colaborador colaborador);

    Optional<Colaborador> buscarPorId(String id);

    Optional<Colaborador> buscarPorMatricula(String matricula);

    Optional<Colaborador> buscarPorEmail(String email);

    boolean existePorEmail(String email);

    List<Colaborador> listarTodos();

    long contarColaboradores();
}
