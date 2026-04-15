package nosi.webapps.gestao_de_pedidos_de_suporte_.RepositoryPedidoSuporte;

import nosi.core.webapp.Core;
import nosi.core.webapp.databse.helpers.QueryInterface;
import nosi.webapps.gestao_de_pedidos_de_suporte_.dao.PedidoSuporte;

public class ListagemPedidosRepository {

    /**
     * Retorna a QueryInterface apontando para a tabela da DAO PedidoSuporte
     */
    public QueryInterface getQueryBase() {
        // Core.query(null, ...) utiliza a conexão padrão da base de dados
        // Usamos o SELECT t FROM PedidoSuporte t definido na tua @NamedQuery
        return Core.query(null, "SELECT t FROM PedidoSuporte t");
    }

    public PedidoSuporte findById(Integer id) {
        return new PedidoSuporte().findOne(id);
    }
}