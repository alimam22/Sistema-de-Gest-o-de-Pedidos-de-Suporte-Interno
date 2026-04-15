package nosi.webapps.gestao_de_pedidos_de_suporte_.ServicoPedidoSuporte;

import nosi.core.webapp.Core;
import nosi.webapps.gestao_de_pedidos_de_suporte_.dao.PedidoSuporte;
import nosi.webapps.gestao_de_pedidos_de_suporte_.dao.PedidoHistorico;
import nosi.webapps.gestao_de_pedidos_de_suporte_.dao.CategoriaSuporte;
import nosi.webapps.gestao_de_pedidos_de_suporte_.dao.Departamento;
import nosi.webapps.gestao_de_pedidos_de_suporte_.enums.EstadoPedido;
import nosi.webapps.gestao_de_pedidos_de_suporte_.enums.FluxoEstadoPedido;
import nosi.webapps.gestao_de_pedidos_de_suporte_.RepositoryPedidoSuporte.PedidoSuporteRepository;

import java.time.LocalDateTime;

/**
 * Serviço principal de negócio para PedidoSuporte.
 *
 * Toda mudança de estado passa obrigatoriamente por {@link FluxoEstadoPedido#validar},
 * garantindo que o diagrama de fluxo seja respeitado em runtime.
 *
 * Fluxo:
 *   ABERTO → ATRIBUIDO → EM_ANALISE → EM_EXECUCAO → CONCLUIDO
 *     │           │             │             │
 *     ▼           ▼             ▼             ▼
 *  CANCELADO  CANCELADO    REJEITADO     REJEITADO
 *                               └──► REABERTO → ATRIBUIDO
 *   CONCLUIDO → REABERTO (pelo SOLICITANTE)
 */
public class PedidoSuporteService {

    private final PedidoSuporteRepository repository;

    public PedidoSuporteService() {
        this.repository = new PedidoSuporteRepository();
    }

    // =========================================================================
    // CRIAÇÃO
    // =========================================================================

    /**
     * Cria um novo pedido com estado inicial ABERTO.
     */
    public PedidoSuporte criarPedido(String titulo, String descricao, String prioridade,
                                     CategoriaSuporte cat, Departamento dep, Integer solicitanteId) {
        PedidoSuporte p = new PedidoSuporte();
        p.setNumeroPedido(gerarNumeroPedido());
        p.setTitulo(titulo);
        p.setDescricao(descricao);
        p.setPrioridade(prioridade != null ? prioridade.toUpperCase() : "MEDIA");
        p.setEstado(EstadoPedido.ABERTO.name());
        p.setCategoriaId(cat);
        p.setDepartamentoId(dep);
        p.setSolicitanteId(solicitanteId);
        p.setDataCriacao(LocalDateTime.now());
        p.setDataUltimaActualizacao(LocalDateTime.now());
        return repository.save(p);
    }

    // =========================================================================
    // TRANSIÇÃO DE ESTADO (ponto central de toda mudança de estado)
    // =========================================================================

    /**
     * Realiza a transição de estado com validação completa do fluxo.
     *
     * @param pedidoId          ID do pedido a transitar
     * @param novoEstado        novo estado desejado (String ou EstadoPedido.name())
     * @param utilizadorId      ID do utilizador a executar a ação
     * @param perfilUtilizador  perfil do utilizador: GESTOR | TECNICO | SOLICITANTE
     * @param observacao        justificativa (obrigatória para certos estados)
     * @return true se a transição foi bem-sucedida
     * @throws IllegalArgumentException se o estado for inválido ou observação estiver em falta
     * @throws SecurityException        se o perfil não tiver permissão para esta transição
     * @throws IllegalStateException    se o estado atual não permitir transições
     */
    public boolean transitarEstado(Integer pedidoId, String novoEstado,
                                   Integer utilizadorId, String perfilUtilizador, String observacao) {

        PedidoSuporte pedido = repository.findById(pedidoId);
        if (pedido == null) throw new IllegalArgumentException("Pedido não encontrado: " + pedidoId);

        // 1. Converter Strings para enums (com validação de valor)
        EstadoPedido estadoAtual = EstadoPedido.fromString(pedido.getEstado());
        EstadoPedido estadoNovo  = EstadoPedido.fromString(novoEstado);

        // 2. Validar a transição pelo FluxoEstadoPedido (lança exceção se inválida)
        FluxoEstadoPedido.validar(estadoAtual, estadoNovo, perfilUtilizador);

        // 3. Validar observação obrigatória
        if (FluxoEstadoPedido.exigeObservacao(estadoNovo)
                && (observacao == null || observacao.isBlank())) {
            throw new IllegalArgumentException(
                    "É obrigatório fornecer uma justificativa ao mover para: " + estadoNovo.getDescricao());
        }

        // 4. Aplicar regras de negócio específicas por estado
        aplicarRegrasNegocio(pedido, estadoNovo, observacao);

        // 5. Persistir o novo estado
        pedido.setEstado(estadoNovo.name());
        pedido.setDataUltimaActualizacao(LocalDateTime.now());

        PedidoSuporte salvo = repository.save(pedido);
        if (salvo == null) throw new RuntimeException("Erro crítico ao persistir o estado do pedido.");

        // 6. Registar no histórico de auditoria
        registrarHistorico(salvo, estadoAtual.name(), estadoNovo.name(), utilizadorId, observacao);

        return true;
    }

    // =========================================================================
    // ATRIBUIÇÃO DE TÉCNICO (GESTOR)
    // =========================================================================

    /**
     * Atribui um técnico ao pedido e avança o estado para ATRIBUIDO.
     * Só pode ser executado por um GESTOR.
     */
    public boolean atribuirTecnico(Integer pedidoId, Integer tecnicoId,
                                   Integer gestorId, String perfilUtilizador) {
        if (!"GESTOR".equalsIgnoreCase(perfilUtilizador))
            throw new SecurityException("Apenas o perfil GESTOR pode atribuir técnicos.");

        PedidoSuporte pedido = repository.findById(pedidoId);
        if (pedido == null) throw new IllegalArgumentException("Pedido não encontrado: " + pedidoId);

        EstadoPedido estadoAtual = EstadoPedido.fromString(pedido.getEstado());

        // Valida que o GESTOR pode mover para ATRIBUIDO a partir do estado atual
        FluxoEstadoPedido.validar(estadoAtual, EstadoPedido.ATRIBUIDO, "GESTOR");

        String estadoAnterior = pedido.getEstado();
        pedido.setTecnicoId(tecnicoId);
        pedido.setEstado(EstadoPedido.ATRIBUIDO.name());
        pedido.setDataAtribuicao(LocalDateTime.now());
        pedido.setDataUltimaActualizacao(LocalDateTime.now());

        if (repository.save(pedido) != null) {
            registrarHistorico(pedido, estadoAnterior, EstadoPedido.ATRIBUIDO.name(),
                    gestorId, "Técnico atribuído pelo gestor.");
            return true;
        }
        return false;
    }

    // =========================================================================
    // REABERTURA (SOLICITANTE ou GESTOR)
    // =========================================================================

    /**
     * Reabre um pedido CONCLUIDO ou REJEITADO, voltando ao fluxo.
     * Após REABERTO, o GESTOR deverá atribuir novamente (→ ATRIBUIDO).
     */
    public boolean reabrirPedido(Integer pedidoId, Integer utilizadorId,
                                 String perfilUtilizador, String motivo) {
        return transitarEstado(pedidoId, EstadoPedido.REABERTO.name(),
                utilizadorId, perfilUtilizador, motivo);
    }

    // =========================================================================
    // MÉTODOS PRIVADOS
    // =========================================================================

    /**
     * Aplica efeitos colaterais no pedido conforme o estado de destino.
     */
    private void aplicarRegrasNegocio(PedidoSuporte pedido, EstadoPedido novoEstado, String obs) {
        switch (novoEstado) {
            case CONCLUIDO:
            case REJEITADO:
                pedido.setDataResolucao(LocalDateTime.now());
                pedido.setObservacaoResolucao(obs);
                break;

            case REABERTO:
                // Limpa dados de resolução anterior para novo ciclo
                pedido.setDataResolucao(null);
                pedido.setObservacaoResolucao(null);
                pedido.setTecnicoId(null); // Gestor deverá atribuir novamente
                break;

            case CANCELADO:
                pedido.setObservacaoResolucao(obs);
                break;

            default:
                // Sem efeitos colaterais para os demais estados
                break;
        }
    }

    /**
     * Grava uma linha no histórico de auditoria do pedido.
     */
    private void registrarHistorico(PedidoSuporte p, String estadoAnterior,
                                    String estadoNovo, Integer userId, String obs) {
        try {
            PedidoHistorico h = new PedidoHistorico();
            h.setPedidoId(p);
            h.setEstadoAnterior(estadoAnterior);
            h.setEstadoNovo(estadoNovo);
            h.setUtilizadorId(userId);
            h.setObservacao(obs);
            h.setDataTransicao(LocalDateTime.now());
            h.insert();
        } catch (Exception e) {
            System.err.println("[PedidoSuporteService] Erro ao gravar histórico: " + e.getMessage());
        }
    }

    /**
     * Gera o número sequencial do pedido no formato SUP-YYYY-NNNN.
     */
    private String gerarNumeroPedido() {
        int ano = LocalDateTime.now().getYear();
        Long seq = repository.proximoNumeroSequencia(ano);
        return String.format("SUP-%d-%04d", ano, seq);
    }
}
