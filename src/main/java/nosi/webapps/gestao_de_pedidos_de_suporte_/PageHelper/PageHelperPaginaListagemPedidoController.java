package nosi.webapps.gestao_de_pedidos_de_suporte_.PageHelper;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import nosi.core.webapp.Core;
import nosi.core.webapp.Controller;
import nosi.webapps.igrp.dao.User;
import nosi.webapps.gestao_de_pedidos_de_suporte_.ServicoPedidoSuporte.ListagemPedidosService;
import nosi.webapps.gestao_de_pedidos_de_suporte_.dao.PedidoSuporte;
import nosi.webapps.gestao_de_pedidos_de_suporte_.pages.pagina_de_listagem_de_pedidos.Pagina_de_listagem_de_pedidos;
import nosi.webapps.gestao_de_pedidos_de_suporte_.pages.pagina_de_listagem_de_pedidos.Pagina_de_listagem_de_pedidos.Table_1;
import nosi.webapps.gestao_de_pedidos_de_suporte_.pages.pagina_de_listagem_de_pedidos.Pagina_de_listagem_de_pedidosView;

public class PageHelperPaginaListagemPedidoController {

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private final ListagemPedidosService service;

    public PageHelperPaginaListagemPedidoController() {
        this.service = new ListagemPedidosService();
    }

    // --- PERFIS ---
    private boolean isGestor() {
        String code = Core.getCurrentProfileCode();
        return code != null && code.toLowerCase().contains("gestor");
    }

    private boolean isTecnico() {
        String code = Core.getCurrentProfileCode();
        return code != null && code.toLowerCase().contains("tecnico");
    }

    private boolean isSolicitante() {
        String code = Core.getCurrentProfileCode();
        return code != null && code.toLowerCase().contains("solicitante");
    }

    // --- AUXILIARES ---
    public String obterIdParaEdicao() {
        String idParam = Core.getParam("p_tbl_listapedido_id");
        if(Core.isNull(idParam)) idParam = Core.getParam("p_id");
        return idParam;
    }

    public boolean prepararEdicao(Controller controller) {
        String id = this.obterIdParaEdicao();
        if (Core.isNotNull(id)) {
            controller.addQueryString("p_id", id);
            controller.addQueryString("p_tbl_listapedido_id", id);
            return true;
        }
        return false;
    }

    private String resolverNomeUtilizador(Integer userId, String fallback) {
        if (userId == null) return fallback;
        try {
            User user = new User().findOne(userId);
            if (user != null) {
                return Core.isNotNull(user.getName()) ? user.getName() : user.getUser_name();
            }
        } catch (Exception e) {}
        return fallback;
    }

    // --- CARREGAR TABELA ---
    public void carregarTabela(Pagina_de_listagem_de_pedidos model) {
        Integer solicitanteId = isSolicitante() ? Core.getCurrentUser().getId() : null;

        List<PedidoSuporte> pedidos = service.pesquisarPedidos(
                model.getN_do_pedido(),
                model.getEstado(),
                model.getDepartamento(),
                model.getCategoria(),
                solicitanteId
        );

        List<Table_1> listaTable = new ArrayList<>();
        if (pedidos != null) {
            for (PedidoSuporte p : pedidos) {
                Table_1 row = new Table_1();
                row.setTbl_listapedido_id(p.getId().toString());
                row.setN_do_pedido_tbl(p.getNumeroPedido());
                row.setAssunto(p.getTitulo());
                row.setPrioridade_tbl(p.getPrioridade());
                row.setEstado_tbl(p.getEstado());
                row.setData_criacao(p.getDataCriacao() != null ? p.getDataCriacao().format(DTF) : "");
                row.setSolicitante_tbl(resolverNomeUtilizador(p.getSolicitanteId(), "S/N"));
                row.setTecnico_tbl(resolverNomeUtilizador(p.getTecnicoId(), "-- Não Atribuído --"));
                listaTable.add(row);
            }
        }
        model.setTable_1(listaTable);
    }

    // --- PERMISSÕES DE BOTÕES ---
    public void aplicarPermissoesBotoes(Pagina_de_listagem_de_pedidosView view) {
        boolean eGestor = isGestor();
        boolean eTecnico = isTecnico();
        boolean eSolicitante = isSolicitante();

        view.btn_novo_pedido.setVisible(eSolicitante || eGestor);
        view.btn_ver.setVisible(true);
        view.btn_editar.setVisible(eGestor);
        view.btn_eliminar.setVisible(eGestor);
        view.btn_atribuir.setVisible(eGestor);
        view.btn_resolver.setVisible(eTecnico);

        // Mapeamento de Parâmetros
        view.btn_ver.addParameter("p_id", "tbl_listapedido_id");
        view.btn_editar.addParameter("p_id", "tbl_listapedido_id");
        view.btn_atribuir.addParameter("p_id", "tbl_listapedido_id");
        view.btn_eliminar.addParameter("p_id", "tbl_listapedido_id");
        view.btn_resolver.addParameter("p_id", "tbl_listapedido_id");
    }
}
