package nosi.webapps.gestao_de_vendas.pages.lista_de_vendas;

import nosi.core.webapp.Controller;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import nosi.core.webapp.Core;
import nosi.core.webapp.Response;
import nosi.webapps.gestao_de_vendas.dao.Clientes;
import nosi.webapps.gestao_de_vendas.dao.Vendas;

/**
 * Controller da lista de vendas.
 * Gerencia as ações da página de listagem de vendas.
 */
public class Lista_de_vendasController extends Controller {

    // =========================================================================
    // INDEX — lista todas as vendas
    // =========================================================================
    public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException {
        var model = new Lista_de_vendas();
        model.load();
        var view = new Lista_de_vendasView();

        /*----#start-code(index)----*/

        List<Lista_de_vendas.Table_1> listaTabela = new ArrayList<>();

        try {
            List<Vendas> vendasDb = new Vendas().find().orderByDesc("dataVenda").all();

            if (vendasDb != null) {
                for (Vendas v : vendasDb) {
                    Lista_de_vendas.Table_1 linha = new Lista_de_vendas.Table_1();
                    
                    // Busca o nome do cliente
                    Clientes cliente = new Clientes().findOne(v.getClienteId());
                    linha.setCliente(cliente != null ? cliente.getNome() : "Cliente Desconhecido");
                    
                    // Formata a data
                    linha.setData(Lista_de_vendas.Table_1.formatarData(v.getDataVenda()));
                    
                    // Formata o total
                    linha.setTotal(Lista_de_vendas.Table_1.formatarValor(v.getTotal()));
                    
                    // Define o status
                    linha.setStatus(v.getStatus());
                    
                    // ID da venda
                    linha.setId_tbl_historico_venda(String.valueOf(v.getId()));
                    
                    listaTabela.add(linha);
                }
            }
        } catch (Exception e) {
            Core.setMessageError("Erro ao carregar vendas: " + e.getMessage());
        }

        model.setTable_1(listaTabela);

        /*----#end-code----*/

        view.setModel(model);
        return this.renderView(view);
    }

    // =========================================================================
    // EDITAR — redireciona para a página de nova venda em modo edição
    // =========================================================================
    public Response actionEditar() throws IOException, IllegalArgumentException, IllegalAccessException {
        /*----#start-code(editar)----*/

        String id = Core.getParam("p_id_tbl_historico_venda");

        if (id != null && !id.trim().isEmpty()) {
            try {
                Integer idInt = Core.toInt(id);
                if (idInt != null && idInt > 0) {
                    // Verifica se a venda existe
                    Vendas venda = new Vendas().findOne(idInt);
                    if (venda != null) {
                        this.addQueryString("p_id", id);
                        return this.redirect("gestao_de_vendas", "Pagina_de_venda", "index", this.queryString());
                    } else {
                        Core.setMessageError("Venda não encontrada.");
                    }
                } else {
                    Core.setMessageError("ID inválido.");
                }
            } catch (Exception e) {
                Core.setMessageError("Erro técnico: " + e.getMessage());
            }
        } else {
            Core.setMessageError("Selecione uma venda para editar.");
        }

        /*----#end-code----*/
        return this.redirect("gestao_de_vendas", "Lista_de_vendas", "index");
    }

    // =========================================================================
    // ELIMINAR — remove a venda selecionada
    // =========================================================================
    public Response actionEliminar() throws IOException, IllegalArgumentException, IllegalAccessException {
        /*----#start-code(eliminar)----*/

        String id = Core.getParam("p_id_tbl_historico_venda");

        if (id == null || id.trim().isEmpty()) {
            Core.setMessageError("Nenhuma venda selecionada para eliminar.");
            return this.redirect("gestao_de_vendas", "Lista_de_vendas", "index");
        }

        try {
            Integer idInt = Core.toInt(id);

            if (idInt == null || idInt <= 0) {
                Core.setMessageError("ID inválido para eliminação.");
                return this.redirect("gestao_de_vendas", "Lista_de_vendas", "index");
            }

            Vendas venda = new Vendas().findOne(idInt);

            if (venda == null) {
                Core.setMessageError("Venda não encontrada ou já foi eliminada.");
                return this.redirect("gestao_de_vendas", "Lista_de_vendas", "index");
            }

            // Verifica se a venda está ativa antes de permitir a exclusão
            if (venda.isCancelada()) {
                Core.setMessageError("Não é possível eliminar uma venda já cancelada.");
                return this.redirect("gestao_de_vendas", "Lista_de_vendas", "index");
            }

            // Cancela a venda (soft delete)
            venda.cancelar();
            if (venda.update() != null && !venda.hasError()) {
                Core.setMessageSuccess("Venda cancelada com sucesso!");
            } else {
                Core.setMessageError("Não foi possível cancelar a venda. Verifique se há dependências.");
            }

        } catch (Exception e) {
            Core.setMessageError("Erro técnico ao eliminar: " + e.getMessage());
        }

        /*----#end-code----*/
        return this.redirect("gestao_de_vendas", "Lista_de_vendas", "index");
    }

    /* Start-Code-Block (custom-actions)  *//* End-Code-Block  */
    /*----#start-code(custom_actions)----*/
    /*----#end-code----*/
}
