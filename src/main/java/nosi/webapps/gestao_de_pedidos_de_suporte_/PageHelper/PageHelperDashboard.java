package nosi.webapps.gestao_de_pedidos_de_suporte_.PageHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nosi.core.webapp.Core;
import nosi.webapps.gestao_de_pedidos_de_suporte_.ServicoPedidoSuporte.DashboardService;
import nosi.webapps.gestao_de_pedidos_de_suporte_.pages.dashboard.Dashboard;
import nosi.webapps.gestao_de_pedidos_de_suporte_.pages.dashboard.Dashboard.Chart_estado;

/**
 * Helper responsável por orquestrar o carregamento de todos os componentes
 * visuais do Dashboard: stat boxes, gráfico de estado e tabela de técnicos.
 */
public class PageHelperDashboard {

    // =========================================================================
    // CONSTANTES — CHAVES DE ESTADO
    // =========================================================================

    private static final String ESTADO_TOTAL          = "TOTAL";
    private static final String ESTADO_ABERTO         = "ABERTO";
    private static final String ESTADO_EM_ATENDIMENTO = "EM_ATENDIMENTO";
    private static final String ESTADO_CONCLUIDO      = "CONCLUIDO";

    // =========================================================================
    // DEPENDÊNCIAS
    // =========================================================================

    private final DashboardService service;

    public PageHelperDashboard() {
        this.service = new DashboardService();
    }

    // =========================================================================
    // PONTO DE ENTRADA PRINCIPAL
    // =========================================================================

    /**
     * Carrega todos os componentes do Dashboard com base nos filtros do modelo.
     *
     * @param model instância do Dashboard com os filtros preenchidos pelo utilizador
     */
    public void carregarDashboard(Dashboard model) {
        FiltrosDashboard filtros = extrairFiltros(model);
        Map<String, Long> stats  = service.getDadosCards(
            filtros.dataInicio, 
            filtros.dataFim, 
            filtros.departamentoId
        );

        configurarStatBoxes(model, stats);
        configurarGraficoPizza(model, stats);
    }

    // =========================================================================
    // EXTRAÇÃO DE FILTROS
    // =========================================================================

    /**
     * Lê os valores de filtro submetidos pelo utilizador no formulário form_2.
     */
    private FiltrosDashboard extrairFiltros(Dashboard model) {
        String  dataInicio = model.getData_inicio();
        String  dataFim    = model.getData_fim();
        Integer deptoId    = Core.isNotNull(model.getDepartamento())
                ? Core.toInt(model.getDepartamento())
                : null;

        return new FiltrosDashboard(dataInicio, dataFim, deptoId);
    }

    // =========================================================================
    // STAT BOXES (CARDS)
    // =========================================================================

    /**
     * Preenche os quatro cards de estatísticas com os totais por estado.
     */
    private void configurarStatBoxes(Dashboard model, Map<String, Long> stats) {
        configurarCardTotal(model, stats);
        configurarCardAbertos(model, stats);
        configurarCardEmAtendimento(model, stats);
        configurarCardConcluidos(model, stats);
    }

    private void configurarCardTotal(Dashboard model, Map<String, Long> stats) {
        model.setStats_total_title("Total de Pedidos");
        model.setStats_total_val(getStatValor(stats, ESTADO_TOTAL));
        model.setStats_total_bg("cp-cyan");
        model.setStats_total_icn("fa-tasks");
    }

    private void configurarCardAbertos(Dashboard model, Map<String, Long> stats) {
        model.setStats_abertos_title("Pedidos Abertos");
        model.setStats_abertos_val(getStatValor(stats, ESTADO_ABERTO));
        model.setStats_abertos_bg("cp-orange");
        model.setStats_abertos_icn("fa-clock-o");
    }

    private void configurarCardEmAtendimento(Dashboard model, Map<String, Long> stats) {
        model.setStats_execucao_title("Em Andamento");
        model.setStats_execucao_val(getStatValor(stats, ESTADO_EM_ATENDIMENTO));
        model.setStats_execucao_bg("cp-yellow");
        model.setStats_execucao_icn("fa-spinner");
    }

    private void configurarCardConcluidos(Dashboard model, Map<String, Long> stats) {
        model.setStats_concluidos_title("Concluídos");
        model.setStats_concluidos_val(getStatValor(stats, ESTADO_CONCLUIDO));
        model.setStats_concluidos_bg("cp-green");
        model.setStats_concluidos_icn("fa-check-circle");
    }

    // =========================================================================
    // GRÁFICO DE ESTADO (PIZZA / LINE)
    // =========================================================================

    /**
     * Constrói a lista de pontos do gráfico de distribuição por estado.
     * Agrupa estados relacionados e exclui totais e valores zero.
     */
    private void configurarGraficoPizza(Dashboard model, Map<String, Long> stats) {
        List<Chart_estado> pontos = new ArrayList<>();

        // Mapeamento de estados para o gráfico (agrupando estados de atendimento)
        Map<String, Long> dadosGrafico = new HashMap<>();
        
        stats.forEach((estado, valor) -> {
            if (valor == null || valor <= 0) return;
            if (ESTADO_TOTAL.equals(estado)) return;
            if (ESTADO_EM_ATENDIMENTO.equals(estado)) return; // Já é agregado
            
            dadosGrafico.put(estado, valor);
        });
        
        // Se não houver dados, adiciona pelo menos um ponto para o gráfico não ficar vazio
        if (dadosGrafico.isEmpty()) {
            dadosGrafico.put("Sem Dados", 0L);
        }

        dadosGrafico.forEach((estado, valor) -> {
            pontos.add(new Chart_estado(estado, valor.toString(), valor));
        });

        model.setChart_estado(pontos);
    }

    // =========================================================================
    // HELPERS
    // =========================================================================

    /**
     * Retorna o valor de um estado como String, ou "0" se a chave não existir.
     */
    private String getStatValor(Map<String, Long> stats, String chave) {
        return stats.getOrDefault(chave, 0L).toString();
    }

    // =========================================================================
    // CLASSE AUXILIAR — FILTROS
    // =========================================================================

    /**
     * Agrupa os parâmetros de filtragem do Dashboard num único objeto,
     * facilitando a passagem para o serviço quando necessário.
     */
    private static class FiltrosDashboard {

        final String  dataInicio;
        final String  dataFim;
        final Integer departamentoId;

        FiltrosDashboard(String dataInicio, String dataFim, Integer departamentoId) {
            this.dataInicio     = dataInicio;
            this.dataFim        = dataFim;
            this.departamentoId = departamentoId;
        }

        boolean temFiltroData() {
            return Core.isNotNull(dataInicio) && Core.isNotNull(dataFim);
        }

        boolean temFiltroDepartamento() {
            return departamentoId != null;
        }
    }
}