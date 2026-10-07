package com.separe.adapter.out.persistence.adapter;

import com.separe.adapter.out.persistence.mapper.PersistenceMapper;
import com.separe.adapter.out.persistence.repository.ColaboradorMongoRepository;
import com.separe.domain.model.Colaborador;
import com.separe.domain.port.out.ColaboradorRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ColaboradorPersistenceAdapter implements ColaboradorRepositoryPort {

    private final ColaboradorMongoRepository repository;
    private final PersistenceMapper mapper;

    public ColaboradorPersistenceAdapter(ColaboradorMongoRepository repository, PersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Colaborador salvar(Colaborador colaborador) {
        var document = mapper.toDocument(colaborador);
        var salvo = repository.save(document);
        return mapper.toDomain(salvo);
    }

    @Override
    public Optional<Colaborador> buscarPorId(String id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Colaborador> buscarPorMatricula(String matricula) {
        return repository.findByMatricula(matricula).map(mapper::toDomain);
    }

    @Override
    public Optional<Colaborador> buscarPorEmail(String email) {
        return repository.findByEmail(email).map(mapper::toDomain);
    }

    @Override
    public boolean existePorEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public List<Colaborador> listarTodos() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public long contarColaboradores() {
        return repository.count();
    }
}
