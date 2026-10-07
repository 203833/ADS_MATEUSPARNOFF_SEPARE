package com.separe.adapter.out.persistence.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoDocument {

    private String nome;
    private String codigoBarras;
    private int quantidade;
    private int quantidadeBipada;
}
