package nosi.webapps.gestao_de_pedidos_de_suporte_.enums;

/**
 * Enum que representa todos os estados possíveis de um PedidoSuporte.
 *
 * Fluxo principal:
 *   ABERTO → ATRIBUIDO → EM_ANALISE → EM_EXECUCAO → CONCLUIDO
 *
 * Saídas laterais:
 *   ABERTO      → CANCELADO
 *   ATRIBUIDO   → CANCELADO
 *   EM_ANALISE  → REJEITADO
 *   EM_EXECUCAO → REJEITADO
 *   REJEITADO   → REABERTO → ATRIBUIDO (volta ao fluxo)
 *   CONCLUIDO   → REABERTO → ATRIBUIDO (volta ao fluxo)
 */
public enum EstadoPedido {

    ABERTO("Aberto"),
    ATRIBUIDO("Atribuído"),
    EM_ANALISE("Em Análise"),
    EM_EXECUCAO("Em Execução"),
    CONCLUIDO("Concluído"),
    CANCELADO("Cancelado"),
    REJEITADO("Rejeitado"),
    REABERTO("Reaberto");

    private final String descricao;

    EstadoPedido(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    /**
     * Converte uma String (case-insensitive) para o enum correspondente.
     * @throws IllegalArgumentException se o valor não for reconhecido.
     */
    public static EstadoPedido fromString(String valor) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("Estado não pode ser nulo ou vazio.");
        }
        try {
            return EstadoPedido.valueOf(valor.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Estado inválido: '" + valor + "'");
        }
    }

    /**
     * Verifica se este estado é um estado terminal (não permite mais transições de progresso).
     */
    public boolean isTerminal() {
        return this == CONCLUIDO || this == CANCELADO;
    }

    /**
     * Verifica se o pedido está em processamento ativo.
     */
    public boolean isAtivo() {
        return this == ABERTO || this == ATRIBUIDO || this == EM_ANALISE || this == EM_EXECUCAO || this == REABERTO;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
