package nosi.webapps.gestao_de_pedidos_de_suporte_.enums;

import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

/**
 * ============================================================
 *  MÁQUINA DE ESTADOS — Pedido de Suporte
 * ============================================================
 *
 *  Fluxo principal:
 *    ABERTO ──► ATRIBUIDO ──► EM_ANALISE ──► EM_EXECUCAO ──► CONCLUIDO
 *      │            │             │ │            │ │
 *      │            │             │ │            │ └────► REJEITADO
 *      │            │             │ │            │
 *      │            │             │ └────────────┘
 *      │            │             │
 *      │            │             └────► REJEITADO
 *      │            │
 *      │            └────► CANCELADO (Gestor)
 *      │
 *      └────► CANCELADO (Gestor/Solicitante)
 *
 *  REJEITADO ──► REABERTO ──► ATRIBUIDO (volta ao fluxo)
 *                  ↑
 *  CONCLUIDO ──────┘ (Solicitante pode reabrir)
 *
 *  Perfis reconhecidos: GESTOR | TECNICO | SOLICITANTE
 * ============================================================
 */
public final class FluxoEstadoPedido {

    // -------------------------------------------------------------------------
    // MAPA DE TRANSIÇÕES:  estadoAtual → { perfil → Set<estadosPermitidos> }
    // -------------------------------------------------------------------------
    private static final Map<EstadoPedido, Map<String, Set<EstadoPedido>>> TRANSICOES;

    static {
        Map<EstadoPedido, Map<String, Set<EstadoPedido>>> mapa = new EnumMap<>(EstadoPedido.class);

        // ABERTO
        mapa.put(EstadoPedido.ABERTO, Map.of(
                "GESTOR",      EnumSet.of(EstadoPedido.ATRIBUIDO, EstadoPedido.CANCELADO),
                "SOLICITANTE", EnumSet.of(EstadoPedido.CANCELADO)
        ));

        // ATRIBUIDO
        mapa.put(EstadoPedido.ATRIBUIDO, Map.of(
                "GESTOR",  EnumSet.of(EstadoPedido.CANCELADO),
                "TECNICO", EnumSet.of(EstadoPedido.EM_ANALISE)
        ));

        // EM_ANALISE
        mapa.put(EstadoPedido.EM_ANALISE, Map.of(
                "GESTOR",  EnumSet.of(EstadoPedido.CANCELADO),
                "TECNICO", EnumSet.of(EstadoPedido.EM_EXECUCAO, EstadoPedido.REJEITADO)
        ));

        // EM_EXECUCAO
        mapa.put(EstadoPedido.EM_EXECUCAO, Map.of(
                "GESTOR",  EnumSet.of(EstadoPedido.CANCELADO),
                "TECNICO", EnumSet.of(EstadoPedido.CONCLUIDO, EstadoPedido.REJEITADO)
        ));

        // REJEITADO — gestor decide reabrir
        mapa.put(EstadoPedido.REJEITADO, Map.of(
                "GESTOR",  EnumSet.of(EstadoPedido.REABERTO)
        ));

        // REABERTO — volta ao fluxo
        mapa.put(EstadoPedido.REABERTO, Map.of(
                "GESTOR",  EnumSet.of(EstadoPedido.ATRIBUIDO, EstadoPedido.CANCELADO),
                "TECNICO", EnumSet.of(EstadoPedido.EM_ANALISE, EstadoPedido.EM_EXECUCAO)
        ));

        // CONCLUIDO — solicitante pode reabrir se não estiver satisfeito
        mapa.put(EstadoPedido.CONCLUIDO, Map.of(
                "SOLICITANTE", EnumSet.of(EstadoPedido.REABERTO)
        ));

        // CANCELADO — estado final, sem transições
        mapa.put(EstadoPedido.CANCELADO, Collections.emptyMap());

        TRANSICOES = Collections.unmodifiableMap(mapa);
    }

    // -------------------------------------------------------------------------
    // Estados que exigem observação/justificativa obrigatória
    // -------------------------------------------------------------------------
    private static final Set<EstadoPedido> EXIGE_OBSERVACAO = EnumSet.of(
            EstadoPedido.CONCLUIDO,
            EstadoPedido.REJEITADO,
            EstadoPedido.CANCELADO,
            EstadoPedido.REABERTO
    );

    // Construtor privado — classe utilitária
    private FluxoEstadoPedido() {}

    // =========================================================================
    // API PÚBLICA
    // =========================================================================

    /**
     * Verifica se a transição é permitida para o perfil dado.
     *
     * @param atual   estado atual do pedido
     * @param novo    estado para o qual se quer transitar
     * @param perfil  perfil do utilizador (GESTOR | TECNICO | SOLICITANTE)
     * @return true se a transição for permitida
     */
    public static boolean isPermitido(EstadoPedido atual, EstadoPedido novo, String perfil) {
        if (atual == null || novo == null || perfil == null) return false;
        Map<String, Set<EstadoPedido>> porPerfil = TRANSICOES.getOrDefault(atual, Collections.emptyMap());
        Set<EstadoPedido> permitidos = porPerfil.getOrDefault(perfil.toUpperCase(), Collections.emptySet());
        return permitidos.contains(novo);
    }

    /**
     * Valida a transição e lança exceção descritiva se não for permitida.
     *
     * @throws SecurityException       se o perfil não tem permissão
     * @throws IllegalStateException   se o estado atual não permite nenhuma transição
     */
    public static void validar(EstadoPedido atual, EstadoPedido novo, String perfil) {
        Map<String, Set<EstadoPedido>> porPerfil = TRANSICOES.getOrDefault(atual, null);

        if (porPerfil == null || porPerfil.isEmpty()) {
            throw new IllegalStateException(
                    "O pedido está em '" + atual.getDescricao() + "' — nenhuma transição é permitida a partir deste estado.");
        }

        Set<EstadoPedido> permitidos = porPerfil.getOrDefault(
                perfil != null ? perfil.toUpperCase() : "", Collections.emptySet());

        if (!permitidos.contains(novo)) {
            throw new SecurityException(
                    "Operação não permitida: o perfil '" + perfil + "' não pode mover de '"
                    + atual.getDescricao() + "' para '" + novo.getDescricao() + "'.");
        }
    }

    /**
     * Retorna os estados para os quais o perfil pode transitar a partir do estado atual.
     */
    public static Set<EstadoPedido> transicoesPossiveis(EstadoPedido atual, String perfil) {
        if (atual == null || perfil == null) return Collections.emptySet();
        Map<String, Set<EstadoPedido>> porPerfil = TRANSICOES.getOrDefault(atual, Collections.emptyMap());
        return Collections.unmodifiableSet(
                porPerfil.getOrDefault(perfil.toUpperCase(), Collections.emptySet()));
    }

    /**
     * Indica se o estado de destino exige que uma observação seja fornecida.
     */
    public static boolean exigeObservacao(EstadoPedido estado) {
        return EXIGE_OBSERVACAO.contains(estado);
    }
}
