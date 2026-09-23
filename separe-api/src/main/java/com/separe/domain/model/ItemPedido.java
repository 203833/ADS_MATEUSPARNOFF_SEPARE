package com.separe.domain.model;

public class ItemPedido {

    private String nome;
    private String codigoBarras;
    private int quantidade;
    private int quantidadeBipada;

    public ItemPedido() {
    }

    public ItemPedido(String nome, String codigoBarras, int quantidade) {
        this.nome = nome;
        this.codigoBarras = codigoBarras;
        this.quantidade = quantidade;
        this.quantidadeBipada = 0;
    }

    public boolean isBipagemCompleta() {
        return quantidadeBipada >= quantidade;
    }

    public boolean podeSerBipado() {
        return quantidadeBipada < quantidade;
    }

    public void bipar() {
        if (!podeSerBipado()) {
            throw new IllegalStateException(
                    "Todas as unidades deste produto já foram bipadas (" + quantidadeBipada + "/" + quantidade + ")"
            );
        }
        this.quantidadeBipada++;
    }

    public void resetarBipagem() {
        this.quantidadeBipada = 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getQuantidadeBipada() {
        return quantidadeBipada;
    }

    public void setQuantidadeBipada(int quantidadeBipada) {
        this.quantidadeBipada = quantidadeBipada;
    }
}
