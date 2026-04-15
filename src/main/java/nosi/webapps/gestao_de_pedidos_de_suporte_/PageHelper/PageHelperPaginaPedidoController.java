package nosi.webapps.gestao_de_pedidos_de_suporte_.PageHelper;

import nosi.core.webapp.Core;
import nosi.webapps.igrp.dao.Profile;
import nosi.webapps.igrp.dao.User;
import nosi.webapps.gestao_de_pedidos_de_suporte_.dao.CategoriaSuporte;
import nosi.webapps.gestao_de_pedidos_de_suporte_.dao.Departamento;
import nosi.webapps.gestao_de_pedidos_de_suporte_.dao.PedidoSuporte;
import nosi.webapps.gestao_de_pedidos_de_suporte_.pages.paginapedidos.Paginapedidos;
import nosi.webapps.gestao_de_pedidos_de_suporte_.pages.paginapedidos.PaginapedidosView;
import nosi.webapps.gestao_de_pedidos_de_suporte_.ServicoPedidoSuporte.PedidoSuporteService;
import nosi.webapps.gestao_de_pedidos_de_suporte_.RepositoryPedidoSuporte.PedidoSuporteRepository;

import java.util.List;

public class PageHelperPaginaPedidoController {

    // =========================================================================
    // DEPENDÊNCIAS
    // =========================================================================

    private final PedidoSuporteService    service;
    private final PedidoSuporteRepository repository;

    public PageHelperPaginaPedidoController() {
        this.service    = new PedidoSuporteService();
        this.repository = new PedidoSuporteRepository();
    }

    // =========================================================================
    // VERIFICAÇÕES DE PERFIL
    // =========================================================================

    private boolean isSolicitante() {
        String code = Core.getCurrentProfileCode();
        return code != null && code.toLowerCase().contains("solicitante");
    }

    private boolean isTecnico() {
        String code = Core.getCurrentProfileCode();
        return code != null && code.toLowerCase().contains("tecnico");
    }

    private boolean isGestor() {
        String code = Core.getCurrentProfileCode();
        return code != null && code.toLowerCase().contains("gestor");
    }

    // =========================================================================
    // ACTION — INDEX (preparar formulário)
    // =========================================================================

    public void actionIndex(PaginapedidosView view) {
        carregarDepartamentos(view);
        carregarCategorias(view);
        carregarPrioridades(view);
        carregarEstados(view);
        carregarTecnicosNaView(view);
        aplicarRestricoesPorPerfil(view);
    }

    // =========================================================================
    // ACTION — CARREGAR PEDIDO PARA EDIÇÃO
    // =========================================================================

    public void carregarPedidoParaEdicao(Paginapedidos model) {
        String idParam = Core.getParam("p_id");
        if (!Core.isNotNull(idParam)) return;

        try {
            PedidoSuporte pedido = repository.findById(Core.toInt(idParam));
            if (pedido == null) return;

            // Solicitante só pode ver os seus próprios pedidos
            if (isSolicitante() && !pedido.getSolicitanteId().equals(Core.getCurrentUser().getId())) {
                Core.setMessageError("Acesso negado.");
                return;
            }

            model.setP_id(pedido.getId());
            model.setAssunto(pedido.getTitulo());
            model.setDetalhe_de_pedido(pedido.getDescricao());
            model.setPropriedade(pedido.getPrioridade());
            model.setEstado(pedido.getEstado());
            model.setNumero_de_pedido(pedido.getNumeroPedido());

            if (pedido.getTecnicoId() != null)
                model.setTecnico_atribuido(pedido.getTecnicoId());

            if (pedido.getDepartamentoId() != null)
                model.setDepartamento(pedido.getDepartamentoId().getId().toString());

            if (pedido.getCategoriaId() != null)
                model.setCategoria(pedido.getCategoriaId().getId().toString());

        } catch (Exception e) {
            Core.setMessageError("Erro ao carregar pedido: " + e.getMessage());
        }
    }

    // =========================================================================
    // ACTION — SUBMETER (criar ou atualizar)
    // =========================================================================

    public void actionSubmeter(Paginapedidos model) {

        // ── GUARDA GLOBAL: técnico nunca pode criar nem submeter pedidos ──────
        if (isTecnico()) {
            Core.setMessageError("Técnicos não podem criar ou submeter pedidos.");
            return;
        }

        // ── Validação básica ──────────────────────────────────────────────────
        if (Core.isNull(model.getAssunto())) {
            Core.setMessageError("O assunto é obrigatório.");
            return;
        }

        try {
            boolean isEdicao = model.getP_id() != null && model.getP_id() > 0;

            if (isEdicao) {
                atualizarPedido(model);
            } else {
                criarPedido(model);
            }

        } catch (Exception e) {
            Core.setMessageError("Erro: " + e.getMessage());
        }
    }

    // =========================================================================
    // LÓGICA DE NEGÓCIO — CRIAR / ATUALIZAR
    // =========================================================================

    private void criarPedido(Paginapedidos model) {
        service.criarPedido(
                model.getAssunto(),
                model.getDetalhe_de_pedido(),
                model.getPropriedade(),
                new CategoriaSuporte().findOne(Core.toInt(model.getCategoria())),
                new Departamento().findOne(Core.toInt(model.getDepartamento())),
                Core.getCurrentUser().getId()
        );
        Core.setMessageSuccess("Pedido criado com sucesso.");
    }

    private void atualizarPedido(Paginapedidos model) {
        PedidoSuporte pedido = repository.findById(model.getP_id());
        if (pedido == null) {
            Core.setMessageError("Pedido não encontrado.");
            return;
        }

        // Solicitante só pode editar pedidos ainda em aberto
        if (isSolicitante() && !"ABERTO".equals(pedido.getEstado())) {
            Core.setMessageError("Não pode editar um pedido que já está em análise.");
            return;
        }

        // Solicitante e Gestor podem alterar dados base do pedido
        if (isGestor() || isSolicitante()) {
            pedido.setTitulo(model.getAssunto());
            pedido.setDescricao(model.getDetalhe_de_pedido());
            pedido.setPrioridade(model.getPropriedade());
        }

        // Gestor pode alterar estado e atribuir técnico
        if (isGestor()) {
            if (Core.isNotNull(model.getEstado())) {
                pedido.setEstado(model.getEstado().toUpperCase());
            }
            if (model.getTecnico_atribuido() != null) {
                pedido.setTecnicoId(model.getTecnico_atribuido());
                pedido.setDataAtribuicao(java.time.LocalDateTime.now());
                // Avança automaticamente para ATRIBUIDO quando um técnico é designado
                if ("ABERTO".equalsIgnoreCase(pedido.getEstado())) {
                    pedido.setEstado("ATRIBUIDO");
                }
            }
        }

        repository.update(pedido);
        Core.setMessageSuccess("Pedido atualizado com sucesso.");
    }

    // =========================================================================
    // CARREGAMENTO DE LISTAS (dropdowns)
    // =========================================================================

    private void carregarDepartamentos(PaginapedidosView view) {
        List<Departamento> departamentos = new Departamento().findAll();
        StringBuilder query = new StringBuilder("SELECT '' as ID, '-- Selecione --' as NAME");
        if (Core.isNotNull(departamentos)) {
            for (Departamento d : departamentos) {
                if ("1".equals(d.getActivo())) {
                    query.append(" UNION SELECT '").append(d.getId())
                            .append("' as ID, '").append(d.getNome()).append("' as NAME");
                }
            }
        }
        view.departamento.setQuery(Core.query(null, query.toString()));
    }

    private void carregarCategorias(PaginapedidosView view) {
        List<CategoriaSuporte> categorias = new CategoriaSuporte().findAll();
        StringBuilder query = new StringBuilder("SELECT '' as ID, '-- Selecione --' as NAME");
        if (Core.isNotNull(categorias)) {
            for (CategoriaSuporte c : categorias) {
                if (c.getActivo() != null && "1".equals(c.getActivo().toString())) {
                    query.append(" UNION SELECT '").append(c.getId())
                            .append("' as ID, '").append(c.getNome()).append("' as NAME");
                }
            }
        }
        view.categoria.setQuery(Core.query(null, query.toString()));
    }

    private void carregarPrioridades(PaginapedidosView view) {
        view.propriedade.setQuery(Core.query(null,
                        "SELECT 'BAIXA'   as ID, 'Baixa'   as NAME UNION " +
                                "SELECT 'MEDIA'   as ID, 'Média'   as NAME UNION " +
                                "SELECT 'ALTA'    as ID, 'Alta'    as NAME UNION " +
                                "SELECT 'URGENTE' as ID, 'Urgente' as NAME"),
                "--- Selecione ---");
    }

    private void carregarEstados(PaginapedidosView view) {
        view.estado.setQuery(Core.query(null,
                        "SELECT 'ABERTO'      as ID, 'Aberto'      as NAME UNION " +
                                "SELECT 'ATRIBUIDO'   as ID, 'Atribuído'   as NAME UNION " +
                                "SELECT 'EM_ANALISE'  as ID, 'Em Análise'  as NAME UNION " +
                                "SELECT 'EM_EXECUCAO' as ID, 'Em Execução' as NAME UNION " +
                                "SELECT 'CONCLUIDO'   as ID, 'Concluído'   as NAME UNION " +
                                "SELECT 'CANCELADO'   as ID, 'Cancelado'   as NAME UNION " +
                                "SELECT 'REJEITADO'   as ID, 'Rejeitado'   as NAME UNION " +
                                "SELECT 'REABERTO'    as ID, 'Reaberto'    as NAME"),
                "--- Selecione ---");
    }

    private void carregarTecnicosNaView(PaginapedidosView view) {
        try {
            view.atribuir_tecnico.setQuery(Core.query(null,
                    "SELECT '' as ID, '-- Selecione técnico --' as NAME " +
                            "UNION " +
                            "SELECT CAST(u.id AS VARCHAR) as ID, u.user_name as NAME " +
                            "FROM tbl_user u " +
                            "INNER JOIN tbl_profile p    ON p.user_fk     = u.id " +
                            "INNER JOIN tbl_profile_type pt ON pt.id      = p.prof_type_fk " +
                            "WHERE LOWER(pt.code)  LIKE '%tecnico%' " +
                            "   OR LOWER(pt.descr) LIKE '%tecnico%' " +
                            "ORDER BY NAME"));
        } catch (Exception e) {
            Core.setMessageWarning("Erro ao carregar técnicos: " + e.getMessage());
            view.atribuir_tecnico.setQuery(Core.query(null,
                    "SELECT '' as ID, '-- Técnicos indisponíveis --' as NAME"));
        }
    }

    // =========================================================================
    // VISIBILIDADE E RESTRIÇÕES POR PERFIL
    // =========================================================================

    private void aplicarRestricoesPorPerfil(PaginapedidosView view) {
        if (isSolicitante()) {
            view.estado.setVisible(false);
            view.atribuir_tecnico.setVisible(false);
        }

        if (isTecnico()) {
            // Técnico só pode consultar — sem edição dos campos base
            view.assunto.propertie().add("readonly", "true");
            view.detalhe_de_pedido.propertie().add("readonly", "true");
            view.departamento.propertie().add("readonly", "true");
            view.categoria.propertie().add("readonly", "true");
            view.atribuir_tecnico.setVisible(false);
        }

        if (!isGestor()) {
            view.atribuir_tecnico.setVisible(false);
        }
    }
}