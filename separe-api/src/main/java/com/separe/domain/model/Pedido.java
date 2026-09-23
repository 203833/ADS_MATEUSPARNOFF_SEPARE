package com.separe.domain.model;

import com.separe.domain.enums.StatusPedido;
import com.separe.domain.enums.TipoEntrega;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Pedido {

    private String id;
    private String numeroPedido;
    private StatusPedido status;
    private TipoEntrega tipoEntrega;
    private DadosCliente cliente;
    private List<ItemPedido> itens;
    private String separadorMatricula;
    private LocalDateTime dataRecebimento;
    private LocalDateTime dataInicioSeparacao;
    private LocalDateTime dataConclusaoSeparacao;
    private DadosColeta dadosColeta;
    private LocalDateTime dataEntrega;

    public Pedido() {
        this.itens = new ArrayList<>();
    }

    public boolean todosItensBipados() {
        if (itens == null || itens.isEmpty()) {
            return false;
        }
        return itens.stream().allMatch(ItemPedido::isBipagemCompleta);
    }

    public int totalItens() {
        if (itens == null) return 0;
        return itens.stream().mapToInt(ItemPedido::getQuantidade).sum();
    }

    public int totalItensBipados() {
        if (itens == null) return 0;
        return itens.stream().mapToInt(ItemPedido::getQuantidadeBipada).sum();
    }

    public Optional<ItemPedido> buscarItemPorCodigoBarras(String codigoBarras) {
        if (itens == null) return Optional.empty();
        return itens.stream()
                .filter(item -> item.getCodigoBarras().equals(codigoBarras))
                .findFirst();
    }

    public boolean produtoPertenceAoPedido(String codigoBarras) {
        return buscarItemPorCodigoBarras(codigoBarras).isPresent();
    }

    public void iniciarSeparacao(String matriculaSeparador) {
        this.status = StatusPedido.EM_SEPARACAO;
        this.separadorMatricula = matriculaSeparador;
        this.dataInicioSeparacao = LocalDateTime.now();
    }

    public void resetarBipagem() {
        if (itens != null) {
            itens.forEach(ItemPedido::resetarBipagem);
        }
    }

    public StatusPedido proximoStatusAposSeparacao() {
        return switch (tipoEntrega) {
            case RETIRADA_EM_LOJA -> StatusPedido.PRONTO_PARA_RETIRADA;
            case RAPIDA, PADRAO -> StatusPedido.AGUARDANDO_COLETA;
        };
    }

    public void concluirSeparacao() {
        if (!todosItensBipados()) {
            throw new IllegalStateException("Não é possível concluir: existem itens pendentes de bipagem");
        }
        this.status = proximoStatusAposSeparacao();
        this.dataConclusaoSeparacao = LocalDateTime.now();
    }

    public void registrarColeta(DadosColeta dadosColeta) {
        if (this.status != StatusPedido.AGUARDANDO_COLETA) {
            throw new IllegalStateException("Pedido não está aguardando coleta");
        }
        this.dadosColeta = dadosColeta;
        this.status = StatusPedido.EM_ROTA_DE_ENTREGA;
    }

    public void registrarEntrega() {
        boolean podeEntregar = this.status == StatusPedido.EM_ROTA_DE_ENTREGA
                || this.status == StatusPedido.PRONTO_PARA_RETIRADA;
        if (!podeEntregar) {
            throw new IllegalStateException("Pedido não está em estado válido para entrega");
        }
        this.status = StatusPedido.ENTREGUE;
        this.dataEntrega = LocalDateTime.now();
    }

    // Getters e Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(String numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public TipoEntrega getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(TipoEntrega tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    public DadosCliente getCliente() {
        return cliente;
    }

    public void setCliente(DadosCliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public String getSeparadorMatricula() {
        return separadorMatricula;
    }

    public void setSeparadorMatricula(String separadorMatricula) {
        this.separadorMatricula = separadorMatricula;
    }

    public LocalDateTime getDataRecebimento() {
        return dataRecebimento;
    }

    public void setDataRecebimento(LocalDateTime dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }

    public LocalDateTime getDataInicioSeparacao() {
        return dataInicioSeparacao;
    }

    public void setDataInicioSeparacao(LocalDateTime dataInicioSeparacao) {
        this.dataInicioSeparacao = dataInicioSeparacao;
    }

    public LocalDateTime getDataConclusaoSeparacao() {
        return dataConclusaoSeparacao;
    }

    public void setDataConclusaoSeparacao(LocalDateTime dataConclusaoSeparacao) {
        this.dataConclusaoSeparacao = dataConclusaoSeparacao;
    }

    public DadosColeta getDadosColeta() {
        return dadosColeta;
    }

    public void setDadosColeta(DadosColeta dadosColeta) {
        this.dadosColeta = dadosColeta;
    }

    public LocalDateTime getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDateTime dataEntrega) {
        this.dataEntrega = dataEntrega;
    }
}
