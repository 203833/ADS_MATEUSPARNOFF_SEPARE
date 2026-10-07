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
@Document(collection = "colaboradores")
public class ColaboradorDocument {

    @Id
    private String id;

    private String nome;

    @Indexed(unique = true)
    private String email;

    @Indexed(unique = true)
    private String matricula;

    private String perfil;

    private boolean ativo;

    private int tentativasLoginFalhas;

    private LocalDateTime dataCadastro;
}
