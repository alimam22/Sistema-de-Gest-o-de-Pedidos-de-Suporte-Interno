package nosi.webapps.gestao_de_pedidos_de_suporte_.PageHelper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import nosi.core.webapp.Core;
import nosi.webapps.gestao_de_pedidos_de_suporte_.dao.PedidoSuporte;
import nosi.webapps.gestao_de_pedidos_de_suporte_.dao.PedidoHistorico;
import nosi.webapps.gestao_de_pedidos_de_suporte_.dao.PedidoComentario;
import nosi.webapps.gestao_de_pedidos_de_suporte_.pages.detalheshistorico.Detalheshistorico;
import nosi.webapps.gestao_de_pedidos_de_suporte_.pages.detalheshistorico.Detalheshistorico.Table_1;
import nosi.webapps.gestao_de_pedidos_de_suporte_.pages.detalheshistorico.Detalheshistorico.Table_2;
import nosi.webapps.gestao_de_pedidos_de_suporte_.pages.detalheshistorico.DetalheshistoricoView;
import nosi.webapps.gestao_de_pedidos_de_suporte_.ServicoPedidoSuporte.PedidoSuporteService;

public class PageHelperDetalheHistorico {

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private final PedidoSuporteService service = new PedidoSuporteService();

    public void carregarDetalhes(Detalheshistorico model, DetalheshistoricoView view) {
        String idPedidoStr = Core.getParam("p_pedido_id");
        if (Core.isNull(idPedidoStr)) idPedidoStr = Core.getParam("p_tbl_listapedido_id");
        if (Core.isNull(idPedidoStr)) idPedidoStr = model.getForm_1_hidden_1();

        if (Core.isNotNull(idPedidoStr)) {
            PedidoSuporte pedido = new PedidoSuporte().findOne(Core.toInt(idPedidoStr));

            if (pedido != null) {
                // Preencher dados do formulário
                model.setForm_1_hidden_1(pedido.getId().toString());
                model.setN_do_pedido(pedido.getNumeroPedido() != null ? pedido.getNumeroPedido().toString() : "");
                model.setEstado(pedido.getEstado());

                if (pedido.getDataCriacao() != null)
                    model.setData_de_abertura(pedido.getDataCriacao().format(dtf));

                if (pedido.getDepartamentoId() != null)
                    model.setDepartamento(pedido.getDepartamentoId().getNome());

                if (pedido.getCategoriaId() != null)
                    model.setCategoria(pedido.getCategoriaId().getNome());

                preencherTabelaHistorico(model, pedido.getId());
                preencherTabelaComentarios(model, pedido.getId());

                // Regra básica: Se o pedido estiver fechado, esconde formulários de interação
                aplicarRegrasEstadoTerminal(pedido, view);
            }
        }
    }

    private void aplicarRegrasEstadoTerminal(PedidoSuporte pedido, DetalheshistoricoView view) {
        String estado = pedido.getEstado();
        boolean pedidoFechado = "CONCLUIDO".equalsIgnoreCase(estado) || "RESOLVIDO".equalsIgnoreCase(estado) || "CANCELADO".equalsIgnoreCase(estado);

        if (pedidoFechado) {
            view.form_2.setVisible(false); // Finalização
            view.form_3.setVisible(false); // Comentários
            view.btn_assumir_pedido.setVisible(false);
            view.btn_finalizar_pedido.setVisible(false);
            view.btn_eliminar.setVisible(false);
        }
    }

    private void preencherTabelaHistorico(Detalheshistorico model, Integer idPedido) {
        List<PedidoHistorico> historicos = new PedidoHistorico().find()
                .andWhere("pedidoId", "=", idPedido)
                .orderByDesc("id")
                .all();

        List<Table_1> listaTable = new ArrayList<>();
        if (historicos != null) {
            for (PedidoHistorico h : historicos) {
                Table_1 row = new Table_1();
                row.setDatahora(h.getDataTransicao() != null ? h.getDataTransicao().format(dtf) : "--");

                var user = (h.getUtilizadorId() != null) ? Core.findUserById(h.getUtilizadorId()) : null;
                row.setTecnico(user != null ? user.getName() : "Sistema");

                row.setEstado_anterior(h.getEstadoAnterior());
                row.setNovo_estado(h.getEstadoNovo());
                row.setObservacao(h.getObservacao());
                row.setTbi_historico_id(h.getId().toString());

                listaTable.add(row);
            }
        }
        model.setTable_1(listaTable);
    }

    private void preencherTabelaComentarios(Detalheshistorico model, Integer idPedido) {
        List<PedidoComentario> comentarios = new PedidoComentario().find()
                .andWhere("pedidoId", "=", idPedido)
                .orderByDesc("dataCriacao")
                .all();

        List<Table_2> listaTable = new ArrayList<>();
        if (comentarios != null) {
            for (PedidoComentario c : comentarios) {
                Table_2 row = new Table_2();
                var user = (c.getUtilizadorId() != null) ? Core.findUserById(c.getUtilizadorId()) : null;
                String nome = (user != null) ? user.getName() : "Desconhecido";
                String data = (c.getDataCriacao() != null) ? c.getDataCriacao().format(dtf) : "--";

                // Layout simples compatível com IGRP
                StringBuilder sb = new StringBuilder();
                sb.append("<p>");
                sb.append("<b>").append(nome).append("</b>");
                sb.append(" | ");
                sb.append("<span style='color:#666;font-size:11px;'>").append(data).append("</span>");
                sb.append("<br/>");
                sb.append(c.getTexto());
                sb.append("</p>");
                
                row.setHistorico_de_comentario(sb.toString());
                row.setTbl_comentario_id(c.getId().toString());

                listaTable.add(row);
            }
        }
        model.setTable_2(listaTable);
    }

    public void finalizarPedido(Detalheshistorico model) throws Exception {
        String idPedido = model.getForm_1_hidden_1();
        String nota = model.getNova_interacao__resolucao();

        if (Core.isNull(nota)) {
            throw new Exception("A descrição da resolução é obrigatória.");
        }

        service.transitarEstado(
                Core.toInt(idPedido),
                "CONCLUIDO",
                Core.getCurrentUser().getId(),
                "UTILIZADOR", // Nome genérico sem lógica de Perfil
                "RESOLUÇÃO: " + nota
        );
    }

    public void eliminarComentario(String idComentario) {
        if (Core.isNotNull(idComentario)) {
            PedidoComentario c = new PedidoComentario().findOne(Core.toInt(idComentario));
            if (c != null && c.delete()) {
                Core.setMessageSuccess("Comentário removido.");
            }
        }
    }

    /**
     * Adiciona um novo comentário ao pedido.
     * Qualquer utilizador envolvido no pedido pode adicionar comentários.
     */
    public void adicionarComentario(Integer idPedido, String texto) {
        if (Core.isNull(idPedido) || Core.isNull(texto)) {
            throw new IllegalArgumentException("ID do pedido e texto do comentário são obrigatórios.");
        }

        PedidoSuporte pedido = new PedidoSuporte().findOne(idPedido);
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não encontrado.");
        }

        PedidoComentario comentario = new PedidoComentario();
        comentario.setPedidoId(pedido);
        comentario.setUtilizadorId(Core.getCurrentUser().getId());
        comentario.setTexto(texto);
        comentario.setDataCriacao(LocalDateTime.now());

        if (comentario.insert() != null) {
            Core.setMessageSuccess("Comentário adicionado com sucesso!");
        } else {
            throw new RuntimeException("Erro ao guardar o comentário.");
        }
    }

    /**
     * Edita um comentário existente.
     * Regras: 
     * - O utilizador pode editar os seus próprios comentários
     * - O Gestor pode editar qualquer comentário
     */
    public void editarComentario(Integer idComentario, String novoTexto) {
        if (Core.isNull(idComentario) || Core.isNull(novoTexto)) {
            throw new IllegalArgumentException("ID do comentário e novo texto são obrigatórios.");
        }

        PedidoComentario comentario = new PedidoComentario().findOne(idComentario);
        if (comentario == null) {
            throw new IllegalArgumentException("Comentário não encontrado.");
        }

        Integer currentUserId = Core.getCurrentUser().getId();
        boolean isGestor = isPerfilGestor();

        // Verificar permissão: próprio comentário ou Gestor
        if (!comentario.getUtilizadorId().equals(currentUserId) && !isGestor) {
            throw new SecurityException("Só pode editar os seus próprios comentários.");
        }

        comentario.setTexto(novoTexto);
        comentario.setDataCriacao(LocalDateTime.now()); // Atualiza data para mostrar que foi editado

        if (comentario.update() != null) {
            Core.setMessageSuccess("Comentário atualizado com sucesso!");
        } else {
            throw new RuntimeException("Erro ao atualizar o comentário.");
        }
    }

    /**
     * Busca um comentário pelo ID.
     */
    public PedidoComentario buscarComentario(Integer idComentario) {
        if (Core.isNull(idComentario)) return null;
        return new PedidoComentario().findOne(idComentario);
    }

    /**
     * Verifica se o utilizador atual é Gestor.
     */
    private boolean isPerfilGestor() {
        String perfilCodigo = Core.getCurrentProfileCode();
        return perfilCodigo != null && perfilCodigo.toLowerCase().contains("gestor");
    }
}
