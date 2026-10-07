package com.separe.adapter.out.persistence.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DadosClienteDocument {

    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private EnderecoDocument endereco;
}
