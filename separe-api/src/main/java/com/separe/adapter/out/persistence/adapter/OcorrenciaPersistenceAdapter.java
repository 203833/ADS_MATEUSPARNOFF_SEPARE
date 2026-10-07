package com.separe.adapter.out.persistence.adapter;

import com.separe.adapter.out.persistence.mapper.PersistenceMapper;
import com.separe.adapter.out.persistence.repository.OcorrenciaMongoRepository;
import com.separe.domain.model.Ocorrencia;
import com.separe.domain.port.out.OcorrenciaRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OcorrenciaPersistenceAdapter implements OcorrenciaRepositoryPort {

    private final OcorrenciaMongoRepository repository;
    private final PersistenceMapper mapper;

    public OcorrenciaPersistenceAdapter(OcorrenciaMongoRepository repository, PersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Ocorrencia salvar(Ocorrencia ocorrencia) {
        var document = mapper.toDocument(ocorrencia);
        var salvo = repository.save(document);
        return mapper.toDomain(salvo);
    }

    @Override
    public List<Ocorrencia> buscarPorPedidoId(String pedidoId) {
        return repository.findByPedidoId(pedidoId).stream()
                .map(mapper::toDomain).toList();
    }
}
