package com.separe.domain.model;

public class DadosCliente {

    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private Endereco endereco;

    public DadosCliente() {
    }

    public DadosCliente(String nome, String cpf, String telefone, String email, Endereco endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }

    /**
     * Retorna o CPF parcialmente mascarado conforme DVP.
     * Ex: 123.456.789-00 → 123.***.89
     */
    public String getCpfMascarado() {
        if (cpf == null || cpf.length() < 5) {
            return "***";
        }
        String soDigitos = cpf.replaceAll("[^0-9]", "");
        if (soDigitos.length() < 5) {
            return "***";
        }
        return soDigitos.substring(0, 3) + ".***." + soDigitos.substring(soDigitos.length() - 2);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
