package nosi.webapps.gestao_de_vendas.pages.pagina_cliente;

import nosi.core.webapp.Controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import nosi.core.webapp.Core;
import nosi.core.webapp.Response;
import nosi.webapps.gestao_de_vendas.dao.Clientes;

/**
 * Controller da lista de clientes.
 * Gerencia as ações da página de listagem de clientes.
 */
@SuppressWarnings("unused") // Instanciado via reflexão pelo IGRP
public class Pagina_clienteController extends Controller {

    // ─────────────────────────────────────────────────────────────────────────
    // Utilitário: resolve ID vindo da tabela (string simples OU array)
    // ─────────────────────────────────────────────────────────────────────────
    private String resolverIdTabela(String param) {
        String id = Core.getParam(param);
        if (Core.isNull(id)) {
            String[] ids = Core.getParamArray(param);
            if (ids != null && ids.length > 0) {
                id = ids[0];
            }
        }
        return (id != null && !id.trim().isEmpty()) ? id.trim() : null;
    }

    // =========================================================================
    // INDEX — lista todos os clientes
    // =========================================================================
    public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException {
        var model = new Pagina_cliente();
        model.load();
        var view = new Pagina_clienteView();
        view.id_tbl_cliente.setParam(true); // ← marca a chave da tabela

        /*----#start-code(index)----*/

        List<Pagina_cliente.Table_1> listaTabela = new ArrayList<>();

        try {
            List<Clientes> clientesDb = new Clientes().find().orderByDesc("id").all();

            if (clientesDb != null) {
                for (Clientes c : clientesDb) {
                    Pagina_cliente.Table_1 linha = new Pagina_cliente.Table_1();
                    linha.setNome_cliente(c.getNome());
                    linha.setEmail_cliente(c.getEmail());
                    linha.setTelefone_cliente(c.getTelefone());
                    linha.setEndereco_cliente(c.getEndereco());
                    linha.setNif_cliente(c.getNif());
                    linha.setCidade_cliente(c.getCidade());
                    linha.setId_tbl_cliente(String.valueOf(c.getId()));
                    listaTabela.add(linha);
                }
            }
        } catch (Exception e) {
            Core.setMessageError("Erro ao carregar clientes: " + e.getMessage());
        }

        model.setTable_1(listaTabela);

        /*----#end-code----*/

        view.setModel(model);
        return this.renderView(view);
    }

    // =========================================================================
    // NOVO CLIENTE — redireciona para a página de cadastro em modo criação
    // =========================================================================
    @SuppressWarnings("unused") // Chamado via reflexão pelo IGRP (rel="novo_cliente")
    public Response actionNovo_cliente() throws IOException, IllegalArgumentException, IllegalAccessException {
        return this.redirect("gestao_de_vendas", "Cadastro_de_cliente", "index");
    }

    // =========================================================================
    // EDITAR — redireciona para cadastro com o ID do cliente selecionado
    // =========================================================================
    @SuppressWarnings("unused") // Chamado via reflexão pelo IGRP (rel="edit")
    public Response actionEdit() throws IOException, IllegalArgumentException, IllegalAccessException {
        /*----#start-code(edit)----*/

        String id = resolverIdTabela("p_id_tbl_cliente");

        if (id != null) {
            try {
                Integer idInt = Core.toInt(id);
                if (idInt == null || idInt <= 0) {
                    Core.setMessageError("ID inválido para edição.");
                    return this.redirect("gestao_de_vendas", "Pagina_cliente", "index");
                }

                // Verifica se o cliente realmente existe antes de redirecionar
                Clientes cliente = new Clientes().findOne(idInt);
                if (cliente == null) {
                    Core.setMessageError("Cliente não encontrado.");
                    return this.redirect("gestao_de_vendas", "Pagina_cliente", "index");
                }

                this.addQueryString("p_id", id);
                this.addQueryString("isEdit", "true");
                return this.redirect("gestao_de_vendas", "Cadastro_de_cliente", "index", this.queryString());

            } catch (Exception e) {
                Core.setMessageError("Erro técnico ao editar: " + e.getMessage());
            }
        } else {
            Core.setMessageError("Selecione um cliente válido para editar.");
        }

        /*----#end-code----*/
        return this.redirect("gestao_de_vendas", "Pagina_cliente", "index");
    }

    // =========================================================================
    // ELIMINAR — remove o cliente selecionado na tabela
    // =========================================================================
    @SuppressWarnings("unused") // Chamado via reflexão pelo IGRP (rel="eliminar")
    public Response actionEliminar() throws IOException, IllegalArgumentException, IllegalAccessException {
        /*----#start-code(eliminar)----*/

        String id = resolverIdTabela("p_id_tbl_cliente");

        if (id == null) {
            Core.setMessageError("Nenhum cliente selecionado para eliminar.");
            return this.redirect("gestao_de_vendas", "Pagina_cliente", "index");
        }

        try {
            Integer idInt = Core.toInt(id);

            if (idInt == null || idInt <= 0) {
                Core.setMessageError("ID inválido para eliminação.");
                return this.redirect("gestao_de_vendas", "Pagina_cliente", "index");
            }

            Clientes cliente = new Clientes().findOne(idInt);

            if (cliente == null) {
                Core.setMessageError("Cliente não encontrado ou já foi eliminado.");
                return this.redirect("gestao_de_vendas", "Pagina_cliente", "index");
            }

            if (cliente.delete()) {
                Core.setMessageSuccess("Cliente eliminado com sucesso!");
            } else {
                Core.setMessageError("Não foi possível eliminar o cliente. Verifique se há dependências.");
            }

        } catch (Exception e) {
            Core.setMessageError("Erro técnico ao eliminar: " + e.getMessage());
        }

        /*----#end-code----*/
        return this.redirect("gestao_de_vendas", "Pagina_cliente", "index");
    }

    /* Start-Code-Block (custom-actions)  *//* End-Code-Block  */
    /*----#start-code(custom_actions)----*/
    /*----#end-code----*/
}
