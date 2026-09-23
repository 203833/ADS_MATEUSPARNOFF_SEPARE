package com.separe.domain.model;

import com.separe.domain.enums.StatusPedido;

import java.time.LocalDateTime;

public class HistoricoAcao {

    private String id;
    private String pedidoId;
    private String matriculaUsuario;
    private String nomeUsuario;
    private String acao;
    private StatusPedido statusAnterior;
    private StatusPedido statusNovo;
    private LocalDateTime dataHora;

    public HistoricoAcao() {
    }

    public HistoricoAcao(String pedidoId, String matriculaUsuario, String nomeUsuario,
                         String acao, StatusPedido statusAnterior, StatusPedido statusNovo) {
        this.pedidoId = pedidoId;
        this.matriculaUsuario = matriculaUsuario;
        this.nomeUsuario = nomeUsuario;
        this.acao = acao;
        this.statusAnterior = statusAnterior;
        this.statusNovo = statusNovo;
        this.dataHora = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(String pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getMatriculaUsuario() {
        return matriculaUsuario;
    }

    public void setMatriculaUsuario(String matriculaUsuario) {
        this.matriculaUsuario = matriculaUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public StatusPedido getStatusAnterior() {
        return statusAnterior;
    }

    public void setStatusAnterior(StatusPedido statusAnterior) {
        this.statusAnterior = statusAnterior;
    }

    public StatusPedido getStatusNovo() {
        return statusNovo;
    }

    public void setStatusNovo(StatusPedido statusNovo) {
        this.statusNovo = statusNovo;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
