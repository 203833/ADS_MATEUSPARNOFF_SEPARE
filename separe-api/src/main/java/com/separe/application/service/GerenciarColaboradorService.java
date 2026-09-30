package com.separe.application.service;

import com.separe.domain.enums.PerfilColaborador;
import com.separe.domain.exception.ColaboradorNaoEncontradoException;
import com.separe.domain.exception.EmailDuplicadoException;
import com.separe.domain.model.Colaborador;
import com.separe.domain.port.in.GerenciarColaboradorUseCase;
import com.separe.domain.port.out.ColaboradorRepositoryPort;

import java.util.List;

public class GerenciarColaboradorService implements GerenciarColaboradorUseCase {

    private final ColaboradorRepositoryPort colaboradorRepository;

    public GerenciarColaboradorService(ColaboradorRepositoryPort colaboradorRepository) {
        this.colaboradorRepository = colaboradorRepository;
    }

    @Override
    public Colaborador cadastrar(String nome, String email) {
        if (colaboradorRepository.existePorEmail(email)) {
            throw new EmailDuplicadoException("Já existe um colaborador com o e-mail: " + email);
        }

        Colaborador colaborador = new Colaborador(nome, email, PerfilColaborador.SEPARADOR);

        String matricula = gerarMatricula();
        colaborador.setMatricula(matricula);

        return colaboradorRepository.salvar(colaborador);
    }

    @Override
    public Colaborador buscarPorId(String id) {
        return colaboradorRepository.buscarPorId(id)
                .orElseThrow(() -> new ColaboradorNaoEncontradoException(
                        "Colaborador não encontrado com id: " + id));
    }

    @Override
    public Colaborador buscarPorMatricula(String matricula) {
        return colaboradorRepository.buscarPorMatricula(matricula)
                .orElseThrow(() -> new ColaboradorNaoEncontradoException(
                        "Colaborador não encontrado com matrícula: " + matricula));
    }

    @Override
    public List<Colaborador> listarTodos() {
        return colaboradorRepository.listarTodos();
    }

    @Override
    public Colaborador editar(String id, String nome, String email) {
        Colaborador colaborador = buscarPorId(id);

        if (!colaborador.getEmail().equals(email) && colaboradorRepository.existePorEmail(email)) {
            throw new EmailDuplicadoException("Já existe um colaborador com o e-mail: " + email);
        }

        colaborador.setNome(nome);
        colaborador.setEmail(email);

        return colaboradorRepository.salvar(colaborador);
    }

    @Override
    public void bloquear(String id) {
        Colaborador colaborador = buscarPorId(id);
        colaborador.bloquear();
        colaboradorRepository.salvar(colaborador);
    }

    @Override
    public void desbloquear(String id) {
        Colaborador colaborador = buscarPorId(id);
        colaborador.desbloquear();
        colaboradorRepository.salvar(colaborador);
    }

    private String gerarMatricula() {
        long total = colaboradorRepository.contarColaboradores();
        return String.format("SEP%05d", total + 1);
    }
}
