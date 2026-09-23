package com.separe.domain.model;

import java.time.LocalDateTime;

public class Ocorrencia {

    private String id;
    private String pedidoId;
    private String produtoNome;
    private String codigoBarras;
    private String motivo;
    private int quantidadeNecessaria;
    private int quantidadeDisponivel;
    private String matriculaFuncionario;
    private LocalDateTime dataHora;
    private String observacao;

    public Ocorrencia() {
    }

    public Ocorrencia(String pedidoId, String produtoNome, String codigoBarras,
                      String motivo, int quantidadeNecessaria, int quantidadeDisponivel,
                      String matriculaFuncionario, String observacao) {
        this.pedidoId = pedidoId;
        this.produtoNome = produtoNome;
        this.codigoBarras = codigoBarras;
        this.motivo = motivo;
        this.quantidadeNecessaria = quantidadeNecessaria;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.matriculaFuncionario = matriculaFuncionario;
        this.observacao = observacao;
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

    public String getProdutoNome() {
        return produtoNome;
    }

    public void setProdutoNome(String produtoNome) {
        this.produtoNome = produtoNome;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public int getQuantidadeNecessaria() {
        return quantidadeNecessaria;
    }

    public void setQuantidadeNecessaria(int quantidadeNecessaria) {
        this.quantidadeNecessaria = quantidadeNecessaria;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public String getMatriculaFuncionario() {
        return matriculaFuncionario;
    }

    public void setMatriculaFuncionario(String matriculaFuncionario) {
        this.matriculaFuncionario = matriculaFuncionario;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
