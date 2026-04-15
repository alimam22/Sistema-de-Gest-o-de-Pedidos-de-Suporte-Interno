package nosi.webapps.gestao_de_pedidos_de_suporte_.PageHelper;

import nosi.core.webapp.Core;
import nosi.webapps.gestao_de_pedidos_de_suporte_.enums.EstadoPedido;
import nosi.webapps.gestao_de_pedidos_de_suporte_.enums.FluxoEstadoPedido;
import nosi.webapps.gestao_de_pedidos_de_suporte_.ServicoPedidoSuporte.MeuPedidoService;
import nosi.webapps.gestao_de_pedidos_de_suporte_.dao.PedidoSuporte;
import nosi.webapps.gestao_de_pedidos_de_suporte_.pages.meus_pedidos_.Meus_pedidos_;
import nosi.webapps.gestao_de_pedidos_de_suporte_.pages.meus_pedidos_.Meus_pedidos_View;
import nosi.webapps.igrp.dao.User;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * PageHelper para a página "Meus Pedidos" (visão do Técnico).
 *
 * Toda mudança de estado é delegada ao FluxoEstadoPedido para garantir
 * que as regras do diagrama de transição sejam sempre respeitadas.
 *
 * Fluxo do Técnico:
 *   ATRIBUIDO  ──► EM_ANALISE  (aceitar)
 *   EM_ANALISE ──► EM_EXECUCAO (aceitar / avançar)
 *   EM_EXECUCAO──► CONCLUIDO   (resolver)
 *   REABERTO   ──► EM_ANALISE  (aceitar — volta ao fluxo)
 *   Qualquer ativo ──► REJEITADO (rejeitar, com observação)
 *
 * Fluxo do Gestor (via combo):
 *   Qualquer estado ──► qualquer estado permitido pelo FluxoEstadoPedido com perfil GESTOR
 */
public class PageHelperMeusPedidoController {

    private final MeuPedidoService service;

    public PageHelperMeusPedidoController() {
        this.service = new MeuPedidoService();
    }

    // =========================================================================
    // UTILITÁRIOS PRIVADOS
    // =========================================================================

    /** Lê o ID do pedido a partir dos parâmetros HTTP disponíveis. */
    private Integer obterIdPedido() {
        // Ordem de prioridade: parâmetro da tabela -> p_id da query string -> p_id do model
        String idParam = Core.getParam("p_tbl_listapedido_id");
        if (Core.isNull(idParam)) idParam = Core.getParam("p_id");
        if (Core.isNull(idParam)) return null;
        try {
            return Integer.parseInt(idParam.trim());
        } catch (Exception e) {
            return null;
        }
    }

    /** Resolve o nome amigável de um utilizador pelo ID. */
    private String resolverNomeUtilizador(Integer userId, String fallback) {
        if (userId == null) return fallback;
        try {
            User user = new User().findOne(userId);
            if (user != null) {
                return Core.isNotNull(user.getName()) ? user.getName() : user.getUser_name();
            }
        } catch (Exception ignored) {}
        return fallback;
    }

    // =========================================================================
    // CARREGAR DADOS DO PEDIDO
    // =========================================================================

    public void carregarDadosPedido(Meus_pedidos_ model) {
        Integer id = obterIdPedido();
        if (id == null) return;

        PedidoSuporte pedido = service.buscarPorId(id);
        if (pedido != null) {
            model.setNumero_de_pedido(pedido.getNumeroPedido());
            model.setAssunto(pedido.getTitulo());
            model.setDetalhe_de_pedido(pedido.getDescricao());
            model.setEstado_atual(pedido.getEstado());
            model.setParecerobervacao_tecnica(pedido.getObservacaoResolucao());
            model.setSolicitante(resolverNomeUtilizador(pedido.getSolicitanteId(), "N/A"));
        }
    }

    // =========================================================================
    // PERMISSÕES DE BOTÕES — baseadas no FluxoEstadoPedido
    // =========================================================================

    /**
     * Mostra/oculta botões de acordo com as transições possíveis para o perfil TECNICO.
     * Usa FluxoEstadoPedido para garantir consistência com o diagrama de estados.
     *
     * CORREÇÃO 1: btn_cancelar sempre oculto — o Técnico nunca cancela pedidos.
     */
    public void aplicarPermissoesBotoes(Meus_pedidos_View view, Meus_pedidos_ model) {
        String estadoStr = model.getEstado_atual();
        if (Core.isNull(estadoStr)) {
            // Sem estado carregado: oculta tudo
            view.btn_aceitar.setVisible(false);
            view.btn_resolver.setVisible(false);
            view.btn_rejeitar.setVisible(false);
            view.btn_cancelar.setVisible(false); // Técnico nunca cancela
            return;
        }

        EstadoPedido estado;
        try {
            estado = EstadoPedido.fromString(estadoStr);
        } catch (IllegalArgumentException e) {
            view.btn_aceitar.setVisible(false);
            view.btn_resolver.setVisible(false);
            view.btn_rejeitar.setVisible(false);
            view.btn_cancelar.setVisible(false); // Técnico nunca cancela
            return;
        }

        // Transições possíveis para o TECNICO a partir do estado atual
        Set<EstadoPedido> possiveis = FluxoEstadoPedido.transicoesPossiveis(estado, "TECNICO");

        // "Aceitar" avança o pedido: ATRIBUIDO→EM_ANALISE, EM_ANALISE→EM_EXECUCAO, REABERTO→EM_ANALISE
        view.btn_aceitar.setVisible(
                possiveis.contains(EstadoPedido.EM_ANALISE) ||
                        possiveis.contains(EstadoPedido.EM_EXECUCAO)
        );

        // "Resolver" conclui o pedido: EM_EXECUCAO → CONCLUIDO
        view.btn_resolver.setVisible(possiveis.contains(EstadoPedido.CONCLUIDO));

        // "Rejeitar" rejeita o pedido: disponível se REJEITADO estiver nas transições
        view.btn_rejeitar.setVisible(possiveis.contains(EstadoPedido.REJEITADO));

        // CORREÇÃO 1: Técnico nunca pode cancelar — responsabilidade do GESTOR/SOLICITANTE
        view.btn_cancelar.setVisible(false);
    }

    // =========================================================================
    // AÇÕES DE MUDANÇA DE ESTADO
    // =========================================================================

    /**
     * ACEITAR — avança o pedido para o próximo estado de trabalho.
     *
     * Transições:
     *   ATRIBUIDO  → EM_ANALISE
     *   EM_ANALISE → EM_EXECUCAO
     *   REABERTO   → EM_ANALISE  (volta ao fluxo)
     */
    public boolean aceitar(Meus_pedidos_ model) {
        Integer id = obterIdPedido();
        if (id == null) {
            Core.setMessageError("ID do pedido não encontrado.");
            return false;
        }

        PedidoSuporte pedido = service.buscarPorId(id);
        if (pedido == null) {
            Core.setMessageError("Pedido não encontrado.");
            return false;
        }

        EstadoPedido estadoAtual;
        try {
            estadoAtual = EstadoPedido.fromString(pedido.getEstado());
        } catch (IllegalArgumentException e) {
            Core.setMessageError("Estado do pedido inválido: " + pedido.getEstado());
            return false;
        }

        // Determina o próximo estado lógico para o técnico "aceitar"
        EstadoPedido proximo = determinarProximoEstadoAceitar(estadoAtual);
        if (proximo == null) {
            Core.setMessageWarning("O pedido está em '" + estadoAtual.getDescricao()
                    + "' — não é possível avançar com 'Aceitar'.");
            return false;
        }

        // Valida pelo fluxo (lança exceção se não permitido)
        try {
            FluxoEstadoPedido.validar(estadoAtual, proximo, "TECNICO");
        } catch (SecurityException | IllegalStateException e) {
            Core.setMessageError(e.getMessage());
            return false;
        }

        pedido.setEstado(proximo.name());
        pedido.setDataUltimaActualizacao(LocalDateTime.now());
        return pedido.update() != null;
    }

    /**
     * RESOLVER — conclui o pedido (EM_EXECUCAO → CONCLUIDO).
     * Requer parecer técnico obrigatório.
     */
    public boolean resolver(Meus_pedidos_ model) {
        Integer id = obterIdPedido();
        if (id == null || Core.isNull(model.getParecerobervacao_tecnica())) {
            Core.setMessageError("O parecer técnico é obrigatório para concluir o pedido.");
            return false;
        }

        PedidoSuporte pedido = service.buscarPorId(id);
        if (pedido == null) return false;

        EstadoPedido estadoAtual;
        try {
            estadoAtual = EstadoPedido.fromString(pedido.getEstado());
        } catch (IllegalArgumentException e) {
            Core.setMessageError("Estado inválido: " + pedido.getEstado());
            return false;
        }

        try {
            FluxoEstadoPedido.validar(estadoAtual, EstadoPedido.CONCLUIDO, "TECNICO");
        } catch (SecurityException | IllegalStateException e) {
            Core.setMessageError(e.getMessage());
            return false;
        }

        pedido.setEstado(EstadoPedido.CONCLUIDO.name());
        pedido.setObservacaoResolucao(model.getParecerobervacao_tecnica());
        pedido.setDataResolucao(LocalDateTime.now());
        pedido.setDataUltimaActualizacao(LocalDateTime.now());
        return pedido.update() != null;
    }

    /**
     * REJEITAR — rejeita o pedido com motivo obrigatório.
     * Disponível em: ATRIBUIDO, EM_ANALISE, EM_EXECUCAO.
     */
    public boolean rejeitar(Meus_pedidos_ model) {
        Integer id = obterIdPedido();
        if (id == null || Core.isNull(model.getParecerobervacao_tecnica())) {
            Core.setMessageError("Informe o motivo da rejeição no campo observação.");
            return false;
        }

        PedidoSuporte pedido = service.buscarPorId(id);
        if (pedido == null) return false;

        EstadoPedido estadoAtual;
        try {
            estadoAtual = EstadoPedido.fromString(pedido.getEstado());
        } catch (IllegalArgumentException e) {
            Core.setMessageError("Estado inválido: " + pedido.getEstado());
            return false;
        }

        try {
            FluxoEstadoPedido.validar(estadoAtual, EstadoPedido.REJEITADO, "TECNICO");
        } catch (SecurityException | IllegalStateException e) {
            Core.setMessageError(e.getMessage());
            return false;
        }

        pedido.setEstado(EstadoPedido.REJEITADO.name());
        pedido.setObservacaoResolucao(model.getParecerobervacao_tecnica());
        pedido.setDataResolucao(LocalDateTime.now());
        pedido.setDataUltimaActualizacao(LocalDateTime.now());
        return pedido.update() != null;
    }

    /**
     * CANCELAR — operação não permitida ao Técnico.
     *
     * CORREÇÃO 1: Método mantido por compatibilidade de assinatura com o Controller,
     * mas retorna sempre false com mensagem de erro adequada.
     * O cancelamento é responsabilidade do GESTOR ou SOLICITANTE.
     */
    public boolean cancelar(Meus_pedidos_ model) {
        Core.setMessageError("O Técnico não tem permissão para cancelar pedidos.");
        return false;
    }

    /**
     * ATUALIZAR ESTADO (via combo) — permite ao Gestor forçar qualquer transição válida.
     *
     * CORREÇÃO 2: novo método para processar a mudança de estado seleccionada no combo.
     * Valida pelo FluxoEstadoPedido com perfil GESTOR.
     */
    public boolean atualizarEstado(Meus_pedidos_ model) {
        Integer id = obterIdPedido();
        String novoEstadoStr = model.getEstado_combo();

        if (id == null || Core.isNull(novoEstadoStr)) {
            Core.setMessageError("Selecione um estado válido.");
            return false;
        }

        PedidoSuporte pedido = service.buscarPorId(id);
        if (pedido == null) {
            Core.setMessageError("Pedido não encontrado.");
            return false;
        }

        EstadoPedido estadoAtual;
        EstadoPedido novoEstado;
        try {
            estadoAtual = EstadoPedido.fromString(pedido.getEstado());
            novoEstado  = EstadoPedido.fromString(novoEstadoStr);
        } catch (IllegalArgumentException e) {
            Core.setMessageError("Estado inválido: " + novoEstadoStr);
            return false;
        }

        // Valida a transição com perfil GESTOR (autoridade máxima sobre o fluxo)
        try {
            FluxoEstadoPedido.validar(estadoAtual, novoEstado, "GESTOR");
        } catch (SecurityException | IllegalStateException e) {
            Core.setMessageError(e.getMessage());
            return false;
        }

        pedido.setEstado(novoEstado.name());
        pedido.setDataUltimaActualizacao(LocalDateTime.now());
        return pedido.update() != null;
    }

    // =========================================================================
    // HELPER PRIVADO
    // =========================================================================

    /**
     * Determina o próximo estado lógico quando o técnico clica em "Aceitar".
     *
     * ATRIBUIDO  → EM_ANALISE
     * EM_ANALISE → EM_EXECUCAO
     * REABERTO   → EM_ANALISE  (volta ao fluxo normal)
     */
    private EstadoPedido determinarProximoEstadoAceitar(EstadoPedido atual) {
        switch (atual) {
            case ATRIBUIDO:
            case REABERTO:
                return EstadoPedido.EM_ANALISE;
            case EM_ANALISE:
                return EstadoPedido.EM_EXECUCAO;
            default:
                return null; // Estado não permite "aceitar"
        }
    }
}