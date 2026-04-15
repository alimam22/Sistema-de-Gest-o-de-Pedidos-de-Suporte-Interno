package nosi.webapps.gestao_de_pedidos_de_suporte_.RepositoryPedidoSuporte;

import nosi.webapps.gestao_de_pedidos_de_suporte_.dao.PedidoSuporte;
import java.util.List;
import java.util.stream.Collectors;

public class PedidoSuporteRepository {

    public PedidoSuporte findById(Integer id) {
        return new PedidoSuporte().findOne(id);
    }

    // --- MELHORIA: Método Save Inteligente ---
    public PedidoSuporte save(PedidoSuporte pedido) {
        if (pedido == null) return null;

        // Se o pedido já tem ID, faz update. Se não tem, faz insert.
        if (pedido.getId() != null) {
            return pedido.update();
        } else {
            return pedido.insert();
        }
    }

    public PedidoSuporte update(PedidoSuporte pedido) {
        return pedido.update();
    }

    public List<PedidoSuporte> findAll() {
        return new PedidoSuporte().findAll();
    }

    public List<PedidoSuporte> findBySolicitante(Integer solicitanteId) {
        return new PedidoSuporte().findAll()
                .stream()
                .filter(p -> solicitanteId.equals(p.getSolicitanteId()))
                .collect(Collectors.toList());
    }

    public List<PedidoSuporte> findByTecnico(Integer tecnicoId) {
        return new PedidoSuporte().findAll()
                .stream()
                .filter(p -> p.getTecnicoId() != null && tecnicoId.equals(p.getTecnicoId()))
                .collect(Collectors.toList());
    }

    public Long proximoNumeroSequencia(int ano) {
        return new PedidoSuporte().findAll()
                .stream()
                .filter(p -> p.getDataCriacao() != null &&
                        p.getDataCriacao().getYear() == ano)
                .count() + 1;
    }

    public Long countByCategoria(Integer categoriaId) {
        return new PedidoSuporte().findAll()
                .stream()
                .filter(p -> p.getCategoriaId() != null &&
                        categoriaId.equals(p.getCategoriaId().getId()))
                .count();
    }

    public Long countByDepartamento(Integer departamentoId) {
        return new PedidoSuporte().findAll()
                .stream()
                .filter(p -> p.getDepartamentoId() != null &&
                        departamentoId.equals(p.getDepartamentoId().getId()))
                .count();
    }
}