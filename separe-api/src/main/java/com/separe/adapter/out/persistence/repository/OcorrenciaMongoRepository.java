package com.separe.adapter.out.persistence.repository;

import com.separe.adapter.out.persistence.document.OcorrenciaDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OcorrenciaMongoRepository extends MongoRepository<OcorrenciaDocument, String> {

    List<OcorrenciaDocument> findByPedidoId(String pedidoId);
}
