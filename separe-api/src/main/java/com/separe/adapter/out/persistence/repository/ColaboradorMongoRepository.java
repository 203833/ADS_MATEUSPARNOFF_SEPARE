package com.separe.adapter.out.persistence.repository;

import com.separe.adapter.out.persistence.document.ColaboradorDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ColaboradorMongoRepository extends MongoRepository<ColaboradorDocument, String> {

    Optional<ColaboradorDocument> findByMatricula(String matricula);

    Optional<ColaboradorDocument> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<ColaboradorDocument> findTopByOrderByMatriculaDesc();
}
