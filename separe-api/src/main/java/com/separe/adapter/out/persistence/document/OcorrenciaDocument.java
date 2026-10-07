package com.separe.adapter.out.persistence.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "ocorrencias")
public class OcorrenciaDocument {

    @Id
    private String id;

    @Indexed
    private String pedidoId;

    private String produtoNome;
    private String codigoBarras;
    private String motivo;
    private int quantidadeNecessaria;
    private int quantidadeDisponivel;
    private String matriculaFuncionario;
    private LocalDateTime dataHora;
    private String observacao;
}
