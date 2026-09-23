package com.separe.domain.model;

import com.separe.domain.enums.PerfilColaborador;

import java.time.LocalDateTime;

public class Colaborador {

    private String id;
    private String nome;
    private String email;
    private String matricula;
    private PerfilColaborador perfil;
    private boolean ativo;
    private int tentativasLoginFalhas;
    private LocalDateTime dataCadastro;

    private static final int LIMITE_TENTATIVAS_LOGIN = 5;

    public Colaborador() {
    }

    public Colaborador(String nome, String email, PerfilColaborador perfil) {
        this.nome = nome;
        this.email = email;
        this.perfil = perfil;
        this.ativo = true;
        this.tentativasLoginFalhas = 0;
        this.dataCadastro = LocalDateTime.now();
    }

    public boolean isAdministrador() {
        return PerfilColaborador.ADMINISTRADOR.equals(this.perfil);
    }

    public boolean isSeparador() {
        return PerfilColaborador.SEPARADOR.equals(this.perfil);
    }

    public boolean podeLogar() {
        return this.ativo && this.tentativasLoginFalhas < LIMITE_TENTATIVAS_LOGIN;
    }

    public boolean isBloqueadoPorTentativas() {
        return this.tentativasLoginFalhas >= LIMITE_TENTATIVAS_LOGIN;
    }

    public void registrarLoginFalho() {
        this.tentativasLoginFalhas++;
    }

    public void resetarTentativasLogin() {
        this.tentativasLoginFalhas = 0;
    }

    public void bloquear() {
        this.ativo = false;
    }

    public void desbloquear() {
        this.ativo = true;
        this.tentativasLoginFalhas = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public PerfilColaborador getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilColaborador perfil) {
        this.perfil = perfil;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public int getTentativasLoginFalhas() {
        return tentativasLoginFalhas;
    }

    public void setTentativasLoginFalhas(int tentativasLoginFalhas) {
        this.tentativasLoginFalhas = tentativasLoginFalhas;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
