package com.separe.application.service;

import com.separe.domain.enums.StatusPedido;
import com.separe.domain.exception.OperacaoInvalidaException;
import com.separe.domain.exception.PedidoNaoEncontradoException;
import com.separe.domain.exception.ProdutoNaoPertenceAoPedidoException;
import com.separe.domain.model.HistoricoAcao;
import com.separe.domain.model.ItemPedido;
import com.separe.domain.model.Pedido;
import com.separe.domain.port.in.SepararPedidoUseCase;
import com.separe.domain.port.out.EmailServicePort;
import com.separe.domain.port.out.HistoricoAcaoRepositoryPort;
import com.separe.domain.port.out.PedidoRepositoryPort;

public class SepararPedidoService implements SepararPedidoUseCase {

    private final PedidoRepositoryPort pedidoRepository;
    private final HistoricoAcaoRepositoryPort historicoRepository;
    private final EmailServicePort emailService;

    public SepararPedidoService(PedidoRepositoryPort pedidoRepository,
                                HistoricoAcaoRepositoryPort historicoRepository,
                                EmailServicePort emailService) {
        this.pedidoRepository = pedidoRepository;
        this.historicoRepository = historicoRepository;
        this.emailService = emailService;
    }

    @Override
    public Pedido iniciarSeparacao(String pedidoId, String matriculaSeparador) {
        Pedido pedido = buscarPedido(pedidoId);

        StatusPedido statusAnterior = pedido.getStatus();

        boolean jaEmSeparacao = StatusPedido.EM_SEPARACAO.equals(pedido.getStatus());
        boolean separadorDiferente = jaEmSeparacao
                && !matriculaSeparador.equals(pedido.getSeparadorMatricula());

        if (separadorDiferente) {
            pedido.resetarBipagem();
        }

        pedido.iniciarSeparacao(matriculaSeparador);
        Pedido salvo = pedidoRepository.salvar(pedido);

        registrarHistorico(pedidoId, matriculaSeparador, "Início da separação",
                statusAnterior, StatusPedido.EM_SEPARACAO);

        enviarEmailStatus(pedido, "Em Separação");

        return salvo;
    }

    @Override
    public Pedido biparProduto(String pedidoId, String codigoBarras) {
        Pedido pedido = buscarPedido(pedidoId);

        if (!StatusPedido.EM_SEPARACAO.equals(pedido.getStatus())) {
            throw new OperacaoInvalidaException(
                    "Pedido não está em separação. Status atual: " + pedido.getStatus().getDescricao());
        }

        if (!pedido.produtoPertenceAoPedido(codigoBarras)) {
            throw new ProdutoNaoPertenceAoPedidoException(
                    "Produto com código de barras " + codigoBarras + " não pertence a este pedido");
        }

        ItemPedido item = pedido.buscarItemPorCodigoBarras(codigoBarras)
                .orElseThrow(() -> new ProdutoNaoPertenceAoPedidoException(
                        "Produto não encontrado no pedido"));

        item.bipar();

        return pedidoRepository.salvar(pedido);
    }

    @Override
    public Pedido concluirSeparacao(String pedidoId) {
        Pedido pedido = buscarPedido(pedidoId);

        if (!StatusPedido.EM_SEPARACAO.equals(pedido.getStatus())) {
            throw new OperacaoInvalidaException(
                    "Pedido não está em separação. Status atual: " + pedido.getStatus().getDescricao());
        }

        StatusPedido statusAnterior = pedido.getStatus();
        pedido.concluirSeparacao();
        Pedido salvo = pedidoRepository.salvar(pedido);

        registrarHistorico(pedidoId, pedido.getSeparadorMatricula(), "Conclusão da separação",
                statusAnterior, pedido.getStatus());

        enviarEmailStatus(pedido, pedido.getStatus().getDescricao());

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
