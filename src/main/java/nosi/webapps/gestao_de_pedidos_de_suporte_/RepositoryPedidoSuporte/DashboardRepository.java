package nosi.webapps.gestao_de_pedidos_de_suporte_.RepositoryPedidoSuporte;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import nosi.core.webapp.Core;
import nosi.webapps.gestao_de_pedidos_de_suporte_.dao.PedidoSuporte;

public class DashboardRepository {

    /**
     * Retorna o total de pedidos filtrados por estado
     */
    public long countByEstado(String estado) {
        return new PedidoSuporte().find()
                .andWhere("estado", "=", estado)
                .getCount();
    }

    /**
     * Retorna o total geral de pedidos
     */
    public long countTotal() {
        return new PedidoSuporte().find().getCount();
    }

    /**
     * Busca estatísticas com filtros de data e departamento
     * Estados alinhados com o fluxo de trabalho real do sistema
     */
    public Map<String, Long> getEstatisticasFiltradas(String dataInicio, String dataFim, Integer departamentoId) {
        Map<String, Long> stats = new HashMap<>();

        // Estados do fluxo de trabalho real
        String[] estados = {"ABERTO", "ATRIBUIDO", "EM_ANALISE", "EM_EXECUCAO", "CONCLUIDO", "CANCELADO", "REJEITADO"};

        for (String estado : estados) {
            var query = new PedidoSuporte().find().andWhere("estado", "=", estado);

            if (Core.isNotNull(departamentoId)) {
                query.andWhere("departamentoId", "=", departamentoId);
            }

            // Filtros de data
            if (Core.isNotNull(dataInicio)) {
                query.andWhere("dataCriacao", ">=", dataInicio);
            }
            if (Core.isNotNull(dataFim)) {
                query.andWhere("dataCriacao", "<=", dataFim);
            }

            stats.put(estado, query.getCount());
        }

        return stats;
    }

    /**
     * Retorna contagem de pedidos em atendimento (soma de ATRIBUIDO + EM_ANALISE + EM_EXECUCAO)
     */
    public long countEmAtendimento(String dataInicio, String dataFim, Integer departamentoId) {
        String[] estadosAtendimento = {"ATRIBUIDO", "EM_ANALISE", "EM_EXECUCAO"};
        long total = 0;
        
        for (String estado : estadosAtendimento) {
            var query = new PedidoSuporte().find().andWhere("estado", "=", estado);
            
            if (Core.isNotNull(departamentoId)) {
                query.andWhere("departamentoId", "=", departamentoId);
            }
            if (Core.isNotNull(dataInicio)) {
                query.andWhere("dataCriacao", ">=", dataInicio);
            }
            if (Core.isNotNull(dataFim)) {
                query.andWhere("dataCriacao", "<=", dataFim);
            }
            
            total += query.getCount();
        }
        
        return total;
    }

    /**
     * Ranking de técnicos com mais pedidos resolvidos
     * Retorna uma lista de objetos genéricos para facilitar o mapeamento
     */
    public List<?> getRankingTecnicos() {
        // No IGRP/Hibernate, podemos usar HQL ou NativeQuery para agregações complexas
        return new PedidoSuporte().find()
                .andWhere("estado", "=", "CONCLUIDO")
                .andWhere("tecnicoId", "is not null")
                .all();
        // Nota: Em uma versão final, você usaria GroupBy para contar pedidos por técnico.
    }
}