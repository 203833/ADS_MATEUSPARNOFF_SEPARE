package com.separe.adapter.out.persistence.adapter;

import com.separe.adapter.out.persistence.mapper.PersistenceMapper;
import com.separe.adapter.out.persistence.repository.HistoricoAcaoMongoRepository;
import com.separe.domain.model.HistoricoAcao;
import com.separe.domain.port.out.HistoricoAcaoRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HistoricoAcaoPersistenceAdapter implements HistoricoAcaoRepositoryPort {

    private final HistoricoAcaoMongoRepository repository;
    private final PersistenceMapper mapper;

    public HistoricoAcaoPersistenceAdapter(HistoricoAcaoMongoRepository repository, PersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public HistoricoAcao salvar(HistoricoAcao historico) {
        var document = mapper.toDocument(historico);
        var salvo = repository.save(document);
        return mapper.toDomain(salvo);
    }

    @Override
    public List<HistoricoAcao> buscarPorPedidoId(String pedidoId) {
        return repository.findByPedidoId(pedidoId).stream()
                .map(mapper::toDomain).toList();
    }

    @Override
    public List<HistoricoAcao> buscarPorMatriculaUsuario(String matricula) {
        return repository.findByMatriculaUsuario(matricula).stream()
                .map(mapper::toDomain).toList();
    }
}
