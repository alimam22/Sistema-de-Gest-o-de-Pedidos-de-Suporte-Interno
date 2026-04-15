package nosi.webapps.gestao_de_pedidos_de_suporte_.ServicoPedidoSuporte;

import java.util.ArrayList;
import java.util.List;
import nosi.core.webapp.Core;
import nosi.webapps.gestao_de_pedidos_de_suporte_.dao.PedidoSuporte;

public class ListagemPedidosService {

    /**
     * Lista todos os pedidos sem filtros.
     * 
     * @param solicitanteId  Se não nulo, filtra apenas os pedidos deste solicitante (REGRA: Solicitante só vê os seus)
     */
    public List<PedidoSuporte> pesquisarPedidos(String numero, String estado, String idDept, String idCat, Integer solicitanteId) {

        try {
            PedidoSuporte p = new PedidoSuporte();
            var query = p.find();

            // FILTRO DE SEGURANÇA: Solicitante só vê os seus próprios pedidos
            if (solicitanteId != null) {
                query.andWhere("solicitanteId", "=", solicitanteId);
            }

            // Ordenação por ID decrescente para ver os mais novos no topo
            query.orderByDesc("id");

            return query.all();
            
        } catch (Exception e) {
            Core.setMessageError("Erro ao carregar pedidos: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Sobrecarga para manter compatibilidade com chamadas sem filtro de solicitante.
     */
    public List<PedidoSuporte> pesquisarPedidos(String numero, String estado, String idDept, String idCat) {
        return pesquisarPedidos(numero, estado, idDept, idCat, null);
    }

    /**
     * Elimina um pedido. 
     * REGRA DE NEGÓCIO: Apenas pedidos 'ABERTO' podem ser excluídos.
     * REGRA DE PERMISSÃO: Apenas o GESTOR pode chamar esta ação (validação no Controller).
     */
    public boolean eliminarPedido(Object id) {
        if (Core.isNull(id)) return false;

        PedidoSuporte pedido = new PedidoSuporte().findOne(Core.toInt(id.toString()));

        if (pedido != null) {
            // Regra de negócio: Apenas pedidos 'ABERTO' podem ser excluídos
            if ("ABERTO".equalsIgnoreCase(pedido.getEstado())) {
                return pedido.delete();
            } else {
                Core.setMessageError("Não é possível eliminar pedidos com estado: " + pedido.getEstado());
                return false;
            }
        }
        return false;
    }

    public PedidoSuporte obterPorId(Object id) {
        if (Core.isNull(id)) return null;
        return new PedidoSuporte().findOne(Core.toInt(id.toString()));
    }
}
