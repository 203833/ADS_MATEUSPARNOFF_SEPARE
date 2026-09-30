package com.separe.application.service;

import com.separe.domain.exception.AcessoBloqueadoException;
import com.separe.domain.exception.ColaboradorNaoEncontradoException;
import com.separe.domain.model.Colaborador;
import com.separe.domain.port.in.GerenciarLoginUseCase;
import com.separe.domain.port.out.ColaboradorRepositoryPort;
import com.separe.domain.port.out.TokenServicePort;

public class GerenciarLoginService implements GerenciarLoginUseCase {

    private final ColaboradorRepositoryPort colaboradorRepository;
    private final TokenServicePort tokenService;

    public GerenciarLoginService(ColaboradorRepositoryPort colaboradorRepository,
                                 TokenServicePort tokenService) {
        this.colaboradorRepository = colaboradorRepository;
        this.tokenService = tokenService;
    }

    @Override
    public String login(String matricula) {
        Colaborador colaborador = colaboradorRepository.buscarPorMatricula(matricula)
                .orElseThrow(() -> new ColaboradorNaoEncontradoException(
                        "Colaborador não encontrado com matrícula: " + matricula));

        if (!colaborador.isAtivo()) {
            throw new AcessoBloqueadoException("Colaborador está inativo");
        }

        if (colaborador.isBloqueadoPorTentativas()) {
            throw new AcessoBloqueadoException(
                    "Acesso bloqueado por excesso de tentativas de login");
        }

        colaborador.resetarTentativasLogin();
        colaboradorRepository.salvar(colaborador);

        return tokenService.gerarToken(colaborador);
    }
}
