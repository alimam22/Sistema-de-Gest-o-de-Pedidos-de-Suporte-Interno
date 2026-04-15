package nosi.webapps.gestao_de_pedidos_de_suporte_.ServicoPedidoSuporte;

import java.util.Map;
import nosi.webapps.gestao_de_pedidos_de_suporte_.RepositoryPedidoSuporte.DashboardRepository;

public class DashboardService {

    private final DashboardRepository repository = new DashboardRepository();

    /**
     * Retorna dados dos cards com filtros aplicados
     */
    public Map<String, Long> getDadosCards(String dataInicio, String dataFim, Integer departamentoId) {
        Map<String, Long> dados = repository.getEstatisticasFiltradas(dataInicio, dataFim, departamentoId);
        
        // Calcula total filtrado
        long totalFiltrado = dados.values().stream().mapToLong(Long::longValue).sum();
        dados.put("TOTAL", totalFiltrado > 0 ? totalFiltrado : repository.countTotal());
        
        // Agrupa estados de atendimento para o card "Em Atendimento"
        dados.put("EM_ATENDIMENTO", repository.countEmAtendimento(dataInicio, dataFim, departamentoId));
        
        return dados;
    }
    
    /**
     * Retorna dados sem filtros (para compatibilidade)
     */
    public Map<String, Long> getDadosCards() {
        return getDadosCards(null, null, null);
    }
}