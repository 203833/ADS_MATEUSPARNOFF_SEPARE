package com.separe.adapter.out.persistence.repository;

import com.separe.adapter.out.persistence.document.PedidoDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PedidoMongoRepository extends MongoRepository<PedidoDocument, String> {

    List<PedidoDocument> findByStatus(String status);

    List<PedidoDocument> findByStatusIn(List<String> statuses);
}
