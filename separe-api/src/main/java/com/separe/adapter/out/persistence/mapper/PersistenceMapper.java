package com.separe.adapter.out.persistence.mapper;

import com.separe.adapter.out.persistence.document.*;
import com.separe.domain.enums.PerfilColaborador;
import com.separe.domain.enums.StatusPedido;
import com.separe.domain.enums.TipoEntrega;
import com.separe.domain.model.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersistenceMapper {

    // ===== COLABORADOR =====

    public Colaborador toDomain(ColaboradorDocument doc) {
        if (doc == null) return null;
        Colaborador c = new Colaborador();
        c.setId(doc.getId());
        c.setNome(doc.getNome());
        c.setEmail(doc.getEmail());
        c.setMatricula(doc.getMatricula());
        c.setPerfil(PerfilColaborador.valueOf(doc.getPerfil()));
        c.setAtivo(doc.isAtivo());
        c.setTentativasLoginFalhas(doc.getTentativasLoginFalhas());
        c.setDataCadastro(doc.getDataCadastro());
        return c;
    }

    public ColaboradorDocument toDocument(Colaborador c) {
        if (c == null) return null;
        ColaboradorDocument doc = new ColaboradorDocument();
        doc.setId(c.getId());
        doc.setNome(c.getNome());
        doc.setEmail(c.getEmail());
        doc.setMatricula(c.getMatricula());
        doc.setPerfil(c.getPerfil().name());
        doc.setAtivo(c.isAtivo());
        doc.setTentativasLoginFalhas(c.getTentativasLoginFalhas());
        doc.setDataCadastro(c.getDataCadastro());
        return doc;
    }

    // ===== PEDIDO =====

    public Pedido toDomain(PedidoDocument doc) {
        if (doc == null) return null;
        Pedido p = new Pedido();
        p.setId(doc.getId());
        p.setNumeroPedido(doc.getNumeroPedido());
        p.setStatus(StatusPedido.valueOf(doc.getStatus()));
        p.setTipoEntrega(TipoEntrega.valueOf(doc.getTipoEntrega()));
        p.setCliente(toDomain(doc.getCliente()));
        p.setItens(doc.getItens() != null
                ? doc.getItens().stream().map(this::toDomain).collect(Collectors.toList())
                : List.of());
        p.setSeparadorMatricula(doc.getSeparadorMatricula());
        p.setDataRecebimento(doc.getDataRecebimento());
        p.setDataInicioSeparacao(doc.getDataInicioSeparacao());
        p.setDataConclusaoSeparacao(doc.getDataConclusaoSeparacao());
        p.setDadosColeta(toDomain(doc.getDadosColeta()));
        p.setDataEntrega(doc.getDataEntrega());
        return p;
    }

    public PedidoDocument toDocument(Pedido p) {
        if (p == null) return null;
        PedidoDocument doc = new PedidoDocument();
        doc.setId(p.getId());
        doc.setNumeroPedido(p.getNumeroPedido());
        doc.setStatus(p.getStatus().name());
        doc.setTipoEntrega(p.getTipoEntrega().name());
        doc.setCliente(toDocument(p.getCliente()));
        doc.setItens(p.getItens() != null
                ? p.getItens().stream().map(this::toDocument).collect(Collectors.toList())
                : List.of());
        doc.setSeparadorMatricula(p.getSeparadorMatricula());
        doc.setDataRecebimento(p.getDataRecebimento());
        doc.setDataInicioSeparacao(p.getDataInicioSeparacao());
        doc.setDataConclusaoSeparacao(p.getDataConclusaoSeparacao());
        doc.setDadosColeta(toDocument(p.getDadosColeta()));
        doc.setDataEntrega(p.getDataEntrega());
        return doc;
    }

    // ===== ITEM PEDIDO =====

    public ItemPedido toDomain(ItemPedidoDocument doc) {
        if (doc == null) return null;
        ItemPedido item = new ItemPedido();
        item.setNome(doc.getNome());
        item.setCodigoBarras(doc.getCodigoBarras());
        item.setQuantidade(doc.getQuantidade());
        item.setQuantidadeBipada(doc.getQuantidadeBipada());
        return item;
    }

    public ItemPedidoDocument toDocument(ItemPedido item) {
        if (item == null) return null;
        return new ItemPedidoDocument(
                item.getNome(), item.getCodigoBarras(),
                item.getQuantidade(), item.getQuantidadeBipada()
        );
    }

    // ===== DADOS CLIENTE =====

    public DadosCliente toDomain(DadosClienteDocument doc) {
        if (doc == null) return null;
        return new DadosCliente(
                doc.getNome(), doc.getCpf(), doc.getTelefone(),
                doc.getEmail(), toDomain(doc.getEndereco())
        );
    }

    public DadosClienteDocument toDocument(DadosCliente c) {
        if (c == null) return null;
        return new DadosClienteDocument(
                c.getNome(), c.getCpf(), c.getTelefone(),
                c.getEmail(), toDocument(c.getEndereco())
        );
    }

    // ===== ENDERECO =====

    public Endereco toDomain(EnderecoDocument doc) {
        if (doc == null) return null;
        return new Endereco(
                doc.getRua(), doc.getNumero(), doc.getComplemento(),
                doc.getBairro(), doc.getCidade(), doc.getEstado(), doc.getCep()
        );
    }

    public EnderecoDocument toDocument(Endereco e) {
        if (e == null) return null;
        return new EnderecoDocument(
                e.getRua(), e.getNumero(), e.getComplemento(),
                e.getBairro(), e.getCidade(), e.getEstado(), e.getCep()
        );
    }

    // ===== DADOS COLETA =====

    public DadosColeta toDomain(DadosColetaDocument doc) {
        if (doc == null) return null;
        DadosColeta dc = new DadosColeta();
        dc.setNomeMotoboy(doc.getNomeMotoboy());
        dc.setCpfMotoboy(doc.getCpfMotoboy());
        dc.setTelefoneMotoboy(doc.getTelefoneMotoboy());
        dc.setDataHora(doc.getDataHora());
        return dc;
    }

    public DadosColetaDocument toDocument(DadosColeta dc) {
        if (dc == null) return null;
        return new DadosColetaDocument(
                dc.getNomeMotoboy(), dc.getCpfMotoboy(),
                dc.getTelefoneMotoboy(), dc.getDataHora()
        );
    }

    // ===== OCORRENCIA =====

    public Ocorrencia toDomain(OcorrenciaDocument doc) {
        if (doc == null) return null;
        Ocorrencia o = new Ocorrencia();
        o.setId(doc.getId());
        o.setPedidoId(doc.getPedidoId());
        o.setProdutoNome(doc.getProdutoNome());
        o.setCodigoBarras(doc.getCodigoBarras());
        o.setMotivo(doc.getMotivo());
        o.setQuantidadeNecessaria(doc.getQuantidadeNecessaria());
        o.setQuantidadeDisponivel(doc.getQuantidadeDisponivel());
        o.setMatriculaFuncionario(doc.getMatriculaFuncionario());
        o.setDataHora(doc.getDataHora());
        o.setObservacao(doc.getObservacao());
        return o;
    }

    public OcorrenciaDocument toDocument(Ocorrencia o) {
        if (o == null) return null;
        OcorrenciaDocument doc = new OcorrenciaDocument();
        doc.setId(o.getId());
        doc.setPedidoId(o.getPedidoId());
        doc.setProdutoNome(o.getProdutoNome());
        doc.setCodigoBarras(o.getCodigoBarras());
        doc.setMotivo(o.getMotivo());
        doc.setQuantidadeNecessaria(o.getQuantidadeNecessaria());
        doc.setQuantidadeDisponivel(o.getQuantidadeDisponivel());
        doc.setMatriculaFuncionario(o.getMatriculaFuncionario());
        doc.setDataHora(o.getDataHora());
        doc.setObservacao(o.getObservacao());
        return doc;
    }

    // ===== HISTORICO ACAO =====

    public HistoricoAcao toDomain(HistoricoAcaoDocument doc) {
        if (doc == null) return null;
        HistoricoAcao h = new HistoricoAcao();
        h.setId(doc.getId());
        h.setPedidoId(doc.getPedidoId());
        h.setMatriculaUsuario(doc.getMatriculaUsuario());
        h.setNomeUsuario(doc.getNomeUsuario());
        h.setAcao(doc.getAcao());
        h.setStatusAnterior(doc.getStatusAnterior() != null ? StatusPedido.valueOf(doc.getStatusAnterior()) : null);
        h.setStatusNovo(doc.getStatusNovo() != null ? StatusPedido.valueOf(doc.getStatusNovo()) : null);
        h.setDataHora(doc.getDataHora());
        return h;
    }

    public HistoricoAcaoDocument toDocument(HistoricoAcao h) {
        if (h == null) return null;
        HistoricoAcaoDocument doc = new HistoricoAcaoDocument();
        doc.setId(h.getId());
        doc.setPedidoId(h.getPedidoId());
        doc.setMatriculaUsuario(h.getMatriculaUsuario());
        doc.setNomeUsuario(h.getNomeUsuario());
        doc.setAcao(h.getAcao());
        doc.setStatusAnterior(h.getStatusAnterior() != null ? h.getStatusAnterior().name() : null);
        doc.setStatusNovo(h.getStatusNovo() != null ? h.getStatusNovo().name() : null);
        doc.setDataHora(h.getDataHora());
        return doc;
    }
}
