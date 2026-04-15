package nosi.webapps.gestao_de_pedidos_de_suporte_.RepositoryPedidoSuporte;

/*----#start-code(packages_import)----*/
import java.util.List;
import java.util.ArrayList;
import nosi.core.webapp.Core;
import nosi.webapps.gestao_de_pedidos_de_suporte_.dao.Departamento;
/*----#end-code----*/

public class DepartamentoRepository {

    /*----#start-code(methods)----*/

    public Departamento findById(Integer id) {
        if (Core.isNull(id)) return null;
        return new Departamento().findOne(id);
    }

    public List<Departamento> findAll() {
        try {
            return new Departamento().find().all();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public boolean existsByNome(String nome, Integer id) {
        var d = new Departamento().find().andWhere("nome", "=", nome);
        if (Core.isNotNull(id)) {
            d.andWhere("id", "<>", id);
        }
        return d.one() != null;
    }

    /**
     * REGRA: Verifica se existe QUALQUER pedido (histórico) para impedir a ELIMINAÇÃO física.
     */
    public boolean temAlgumPedido(Integer id) {
        if (Core.isNull(id)) return false;
        // Usamos SQL Nativo via Core.query para uma contagem rápida
        String sql = "SELECT count(*) as total FROM tbl_pedido_suporte WHERE departamento_id = :id";
        var row = Core.query(null, sql).addInt("id", id).getSingleResult();
        return row != null && Core.toInt(row.get("total").toString()) > 0;
    }

    /**
     * REGRA: Verifica se existem pedidos EM CURSO para impedir a DESATIVAÇÃO.
     * Estados considerados ativos: ABERTO, ATRIBUIDO, EM_ANALISE, EM_EXECUCAO.
     */
    public boolean temPedidosActivos(Integer id) {
        if (Core.isNull(id)) return false;
        String sql = "SELECT count(*) as total FROM tbl_pedido_suporte " +
                "WHERE departamento_id = :id " +
                "AND estado NOT IN ('CONCLUIDO', 'CANCELADO', 'REJEITADO')";
        var row = Core.query(null, sql).addInt("id", id).getSingleResult();
        return row != null && Core.toInt(row.get("total").toString()) > 0;
    }

    public Departamento save(Departamento departamento) {
        if (Core.isNull(departamento)) return null;
        return (Core.isNotNull(departamento.getId())) ? departamento.update() : departamento.insert();
    }

    public boolean delete(Integer id) {
        Departamento d = findById(id);
        return d != null && d.delete();
    }

    /*----#end-code----*/
}