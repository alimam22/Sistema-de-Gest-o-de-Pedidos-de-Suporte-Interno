package nosi.webapps.gestao_de_vendas.pages.detalhe_da_venda;

import nosi.core.webapp.Controller;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import nosi.core.webapp.Core;
import nosi.core.webapp.Response;
import nosi.webapps.gestao_de_vendas.dao.ItensVenda;
import nosi.webapps.gestao_de_vendas.dao.Produtos;
import nosi.webapps.gestao_de_vendas.dao.Vendas;

/**
 * Controller do detalhe da venda.
 * Gerencia as ações da página de detalhes da venda.
 */
public class Detalhe_da_vendaController extends Controller {

    // =========================================================================
    // INDEX — exibe os detalhes da venda
    // =========================================================================
    public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException {
        var model = new Detalhe_da_venda();
        model.load();
        var view = new Detalhe_da_vendaView();

        /*----#start-code(index)----*/

        String idVenda = Core.getParam("p_id");

        List<Detalhe_da_venda.Table_1> listaItens = new ArrayList<>();

        try {
            if (idVenda != null && !idVenda.trim().isEmpty()) {
                Integer idInt = Core.toInt(idVenda);

                if (idInt != null && idInt > 0) {
                    // Verifica se a venda existe
                    Vendas venda = new Vendas().findOne(idInt);
                    if (venda != null) {
                        // Busca os itens da venda
                        List<ItensVenda> itensDb = new ItensVenda().find().andWhere("vendaId", "=", idInt).all();

                        if (itensDb != null) {
                            for (ItensVenda item : itensDb) {
                                Detalhe_da_venda.Table_1 linha = new Detalhe_da_venda.Table_1();

                                // Busca o nome do produto
                                Produtos produto = new Produtos().findOne(item.getProdutoId());
                                linha.setProdutos_comprados(produto != null ? produto.getNome() : "Produto Desconhecido");

                                // Formata a quantidade
                                linha.setQuantidade(String.valueOf(item.getQuantidade()));

                                // Formata o preço unitário
                                linha.setPreco(Detalhe_da_venda.Table_1.formatarValor(item.getPrecoUnitario()));

                                // ID do item
                                linha.setId_tbl_detalha(String.valueOf(item.getId()));

                                listaItens.add(linha);
                            }
                        }
                    } else {
                        Core.setMessageError("Venda não encontrada.");
                    }
                } else {
                    Core.setMessageError("ID inválido.");
                }
            } else {
                Core.setMessageError("Nenhuma venda selecionada.");
            }
        } catch (Exception e) {
            Core.setMessageError("Erro ao carregar detalhes da venda: " + e.getMessage());
        }

        model.setTable_1(listaItens);

        /*----#end-code----*/

        view.setModel(model);
        return this.renderView(view);
    }

    /* Start-Code-Block (custom-actions)  *//* End-Code-Block  */
    /*----#start-code(custom_actions)----*/
    /*----#end-code----*/
}
