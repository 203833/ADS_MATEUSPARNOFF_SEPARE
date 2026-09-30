package com.separe.application.service;

import com.separe.domain.enums.StatusPedido;
import com.separe.domain.exception.OperacaoInvalidaException;
import com.separe.domain.exception.PedidoNaoEncontradoException;
import com.separe.domain.model.DadosColeta;
import com.separe.domain.model.HistoricoAcao;
import com.separe.domain.model.Pedido;
import com.separe.domain.port.in.RegistrarEntregaUseCase;
import com.separe.domain.port.out.EmailServicePort;
import com.separe.domain.port.out.HistoricoAcaoRepositoryPort;
import com.separe.domain.port.out.PedidoRepositoryPort;

public class RegistrarEntregaService implements RegistrarEntregaUseCase {

    private final PedidoRepositoryPort pedidoRepository;
    private final HistoricoAcaoRepositoryPort historicoRepository;
    private final EmailServicePort emailService;

    public RegistrarEntregaService(PedidoRepositoryPort pedidoRepository,
                                   HistoricoAcaoRepositoryPort historicoRepository,
                                   EmailServicePort emailService) {
        this.pedidoRepository = pedidoRepository;
        this.historicoRepository = historicoRepository;
        this.emailService = emailService;
    }

    @Override
    public Pedido registrarColeta(String pedidoId, DadosColeta dadosColeta) {
        Pedido pedido = buscarPedido(pedidoId);

        if (!StatusPedido.AGUARDANDO_COLETA.equals(pedido.getStatus())) {
            throw new OperacaoInvalidaException(
                    "Pedido não está aguardando coleta. Status atual: " + pedido.getStatus().getDescricao());
        }

        StatusPedido statusAnterior = pedido.getStatus();
        pedido.registrarColeta(dadosColeta);
        Pedido salvo = pedidoRepository.salvar(pedido);

        registrarHistorico(pedidoId, "ADMIN", "Registro de coleta",
                statusAnterior, StatusPedido.EM_ROTA_DE_ENTREGA);

        enviarEmailStatus(pedido, "Em Rota de Entrega");

        return salvo;
    }

    @Override
    public Pedido registrarEntrega(String pedidoId) {
        Pedido pedido = buscarPedido(pedidoId);

        boolean podeEntregar = StatusPedido.EM_ROTA_DE_ENTREGA.equals(pedido.getStatus())
                || StatusPedido.PRONTO_PARA_RETIRADA.equals(pedido.getStatus());

        if (!podeEntregar) {
            throw new OperacaoInvalidaException(
                    "Pedido não está em estado válido para entrega. Status atual: "
                            + pedido.getStatus().getDescricao());
        }

        StatusPedido statusAnterior = pedido.getStatus();
        pedido.registrarEntrega();
        Pedido salvo = pedidoRepository.salvar(pedido);

        registrarHistorico(pedidoId, "ADMIN", "Registro de entrega",
                statusAnterior, StatusPedido.ENTREGUE);

        enviarEmailStatus(pedido, "Entregue");

        return salvo;
    }

    private Pedido buscarPedido(String pedidoId) {
        return pedidoRepository.buscarPorId(pedidoId)
                .orElseThrow(() -> new PedidoNaoEncontradoException(
                        "Pedido não encontrado com id: " + pedidoId));
    }

    private void registrarHistorico(String pedidoId, String matricula, String acao,
                                    StatusPedido statusAnterior, StatusPedido statusNovo) {
        HistoricoAcao historico = new HistoricoAcao(
                pedidoId, matricula, null, acao, statusAnterior, statusNovo);
        historicoRepository.salvar(historico);
    }

    private void enviarEmailStatus(Pedido pedido, String novoStatus) {
        if (pedido.getCliente() == null || pedido.getCliente().getEmail() == null) {
            return;
        }
        try {
            String assunto = "Atualização do Pedido " + pedido.getNumeroPedido();
            String corpo = String.format(
                    "Olá %s, seu pedido %s foi atualizado para: %s",
                    pedido.getCliente().getNome(),
                    pedido.getNumeroPedido(),
                    novoStatus);
            emailService.enviarEmail(pedido.getCliente().getEmail(), assunto, corpo);
        } catch (Exception e) {
            // Falha no envio de email não impede atualização de status (conforme DVP)
        }
    }
}
