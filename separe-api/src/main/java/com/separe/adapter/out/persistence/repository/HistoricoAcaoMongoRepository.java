package com.separe.adapter.out.persistence.repository;

import com.separe.adapter.out.persistence.document.HistoricoAcaoDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HistoricoAcaoMongoRepository extends MongoRepository<HistoricoAcaoDocument, String> {

    List<HistoricoAcaoDocument> findByPedidoId(String pedidoId);

    List<HistoricoAcaoDocument> findByMatriculaUsuario(String matriculaUsuario);
}
