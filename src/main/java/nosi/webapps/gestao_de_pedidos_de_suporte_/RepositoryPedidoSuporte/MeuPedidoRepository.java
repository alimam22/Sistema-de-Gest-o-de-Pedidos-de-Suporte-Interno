package nosi.webapps.gestao_de_pedidos_de_suporte_.RepositoryPedidoSuporte;

import java.time.LocalDateTime;
import nosi.webapps.gestao_de_pedidos_de_suporte_.dao.PedidoSuporte;

public class MeuPedidoRepository {

    /**
     * Busca um pedido pelo ID.
     */
    public PedidoSuporte findById(Integer id) {
        if (id == null) return null;
        return new PedidoSuporte().findOne(id);
    }

    /**
     * Atualiza estado + data de última atualização.
     */
    public boolean atualizarEstado(PedidoSuporte pedido, String novoEstado) {
        pedido.setEstado(novoEstado);
        pedido.setDataUltimaActualizacao(LocalDateTime.now());
        return pedido.update() != null;
    }

    /**
     * Atualiza estado + observação + data de última atualização (rejeitar).
     */
    public boolean atualizarEstadoComObservacao(PedidoSuporte pedido, String novoEstado, String observacao) {
        pedido.setEstado(novoEstado);
        pedido.setObservacaoResolucao(observacao);
        pedido.setDataUltimaActualizacao(LocalDateTime.now());
        return pedido.update() != null;
    }

    /**
     * Atualiza estado + observação + data de resolução (concluir).
     */
    public boolean atualizarEstadoComResolucao(PedidoSuporte pedido, String novoEstado, String observacao) {
        pedido.setEstado(novoEstado);
        pedido.setObservacaoResolucao(observacao);
        pedido.setDataResolucao(LocalDateTime.now());
        pedido.setDataUltimaActualizacao(LocalDateTime.now());
        return pedido.update() != null;
    }
}