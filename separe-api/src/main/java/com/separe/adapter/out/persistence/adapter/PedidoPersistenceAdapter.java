package com.separe.adapter.out.persistence.adapter;

import com.separe.adapter.out.persistence.mapper.PersistenceMapper;
import com.separe.adapter.out.persistence.repository.PedidoMongoRepository;
import com.separe.domain.enums.StatusPedido;
import com.separe.domain.model.Pedido;
import com.separe.domain.port.out.PedidoRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PedidoPersistenceAdapter implements PedidoRepositoryPort {

    private final PedidoMongoRepository repository;
    private final PersistenceMapper mapper;

    public PedidoPersistenceAdapter(PedidoMongoRepository repository, PersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Pedido salvar(Pedido pedido) {
        var document = mapper.toDocument(pedido);
        var salvo = repository.save(document);
        return mapper.toDomain(salvo);
    }

    @Override
    public Optional<Pedido> buscarPorId(String id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Pedido> buscarPorStatus(StatusPedido status) {
        return repository.findByStatus(status.name()).stream()
                .map(mapper::toDomain).toList();
    }

    @Override
    public List<Pedido> buscarPorStatusIn(List<StatusPedido> statuses) {
        List<String> statusNames = statuses.stream().map(StatusPedido::name).toList();
        return repository.findByStatusIn(statusNames).stream()
                .map(mapper::toDomain).toList();
    }

    @Override
    public List<Pedido> listarTodos() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }
}
