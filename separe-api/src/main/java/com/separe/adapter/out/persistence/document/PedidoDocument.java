package com.separe.adapter.out.persistence.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "pedidos")
public class PedidoDocument {

    @Id
    private String id;

    @Indexed
    private String numeroPedido;

    @Indexed
    private String status;

    private String tipoEntrega;

    private DadosClienteDocument cliente;

    private List<ItemPedidoDocument> itens;

    private String separadorMatricula;

    private LocalDateTime dataRecebimento;

    private LocalDateTime dataInicioSeparacao;

    private LocalDateTime dataConclusaoSeparacao;

    private DadosColetaDocument dadosColeta;

    private LocalDateTime dataEntrega;
}
