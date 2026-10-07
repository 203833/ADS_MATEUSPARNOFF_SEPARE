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
@Document(collection = "historico_acoes")
public class HistoricoAcaoDocument {

    @Id
    private String id;

    @Indexed
    private String pedidoId;

    @Indexed
    private String matriculaUsuario;

    private String nomeUsuario;
    private String acao;
    private String statusAnterior;
    private String statusNovo;
    private LocalDateTime dataHora;
}
