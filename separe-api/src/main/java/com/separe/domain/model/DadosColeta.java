package com.separe.domain.model;

import java.time.LocalDateTime;

public class DadosColeta {

    private String nomeMotoboy;
    private String cpfMotoboy;
    private String telefoneMotoboy;
    private LocalDateTime dataHora;

    public DadosColeta() {
    }

    public DadosColeta(String nomeMotoboy, String cpfMotoboy, String telefoneMotoboy) {
        this.nomeMotoboy = nomeMotoboy;
        this.cpfMotoboy = cpfMotoboy;
        this.telefoneMotoboy = telefoneMotoboy;
        this.dataHora = LocalDateTime.now();
    }

    public String getNomeMotoboy() {
        return nomeMotoboy;
    }

    public void setNomeMotoboy(String nomeMotoboy) {
        this.nomeMotoboy = nomeMotoboy;
    }

    public String getCpfMotoboy() {
        return cpfMotoboy;
    }

    public void setCpfMotoboy(String cpfMotoboy) {
        this.cpfMotoboy = cpfMotoboy;
    }

    public String getTelefoneMotoboy() {
        return telefoneMotoboy;
    }

    public void setTelefoneMotoboy(String telefoneMotoboy) {
        this.telefoneMotoboy = telefoneMotoboy;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
