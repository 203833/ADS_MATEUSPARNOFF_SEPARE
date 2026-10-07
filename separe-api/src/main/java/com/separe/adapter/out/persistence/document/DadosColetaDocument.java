package com.separe.adapter.out.persistence.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DadosColetaDocument {

    private String nomeMotoboy;
    private String cpfMotoboy;
    private String telefoneMotoboy;
    private LocalDateTime dataHora;
}
