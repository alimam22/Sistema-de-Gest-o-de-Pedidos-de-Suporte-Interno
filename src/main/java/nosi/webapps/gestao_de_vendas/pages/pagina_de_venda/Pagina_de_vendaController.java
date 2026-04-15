package nosi.webapps.gestao_de_vendas.pages.pagina_de_venda;

import nosi.core.webapp.Controller;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import nosi.core.webapp.Core;
import nosi.core.webapp.Response;
import nosi.webapps.gestao_de_vendas.dao.Clientes;
import nosi.webapps.gestao_de_vendas.dao.ItensVenda;
import nosi.webapps.gestao_de_vendas.dao.Produtos;
import nosi.webapps.gestao_de_vendas.dao.Vendas;

/**
 * Controller da página de venda.
 * Gerencia as ações da página de nova venda.
 */
public class Pagina_de_vendaController extends Controller {

    // =========================================================================
    // INDEX — exibe o formulário de nova venda ou edição
    // =========================================================================
    public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException {
        var model = new Pagina_de_venda();
        model.load();
        var view = new Pagina_de_vendaView();

        /*----#start-code(index)----*/

        String id = Core.getParam("p_id");
        boolean isEdicao = id != null && !id.trim().isEmpty();

        if (isEdicao) {
            try {
                Integer idInt = Core.toInt(id);
                if (idInt != null && idInt > 0) {
                    Vendas venda = new Vendas().findOne(idInt);
                    if (venda != null) {
                        model.setId(idInt);
                        model.setCliente(String.valueOf(venda.getClienteId()));
                        model.setData(venda.getDataVenda().toLocalDate().toString());
                        model.setStatus(venda.getStatus());

                        // Carrega os itens da venda
                        List<ItensVenda> itens = new ItensVenda().find().andWhere("vendaId", "=", idInt).all();
                        List<Pagina_de_venda.Table_1> listaItens = new ArrayList<>();

                        for (ItensVenda item : itens) {
                            Produtos produto = new Produtos().findOne(item.getProdutoId());
                            Pagina_de_venda.Table_1 linha = new Pagina_de_venda.Table_1();
                            linha.setProdutoId(String.valueOf(item.getProdutoId()));
                            linha.setProdutoNome(produto != null ? produto.getNome() : "");
                            linha.setQuantidade(String.valueOf(item.getQuantidade()));
                            linha.setPrecoUnitario(Pagina_de_venda.Table_1.formatarValor(item.getPrecoUnitario()));
                            linha.setSubtotal(Pagina_de_venda.Table_1.formatarValor(item.getSubtotal()));
                            linha.setId_tbl_item(String.valueOf(item.getId()));
                            listaItens.add(linha);
                        }

                        model.setTable_1(listaItens);
                    } else {
                        Core.setMessageError("Venda não encontrada.");
                    }
                }
            } catch (Exception e) {
                Core.setMessageError("Erro ao carregar venda: " + e.getMessage());
            }
        } else {
            // Novo registro
            model.setData(LocalDateTime.now().toLocalDate().toString());
            model.setStatus("ATIVO");
        }

        /*----#end-code----*/

        view.setModel(model);
        return this.renderView(view);
    }

    // =========================================================================
    // ADICIONAR_PRODUTO — adiciona um produto à venda
    // =========================================================================
    public Response actionAdicionar_produto() throws IOException, IllegalArgumentException, IllegalAccessException {
        var model = new Pagina_de_venda();
        model.load();
        var view = new Pagina_de_vendaView();

        /*----#start-code(adicionar_produto)----*/

        String produtoIdParam = Core.getParam("p_produto_id");
        String quantidadeParam = Core.getParam("p_quantidade");

        if (produtoIdParam != null && !produtoIdParam.trim().isEmpty() && 
            quantidadeParam != null && !quantidadeParam.trim().isEmpty()) {
            
            try {
                Integer produtoId = Core.toInt(produtoIdParam);
                Integer quantidade = Core.toInt(quantidadeParam);

                if (produtoId != null && quantidade != null && quantidade > 0) {
                    Produtos produto = new Produtos().findOne(produtoId);
                    if (produto != null && produto.getAtivo() != null && produto.getAtivo()) {
                        
                        BigDecimal precoUnitario = BigDecimal.valueOf(produto.getPrecoVenda());
                        BigDecimal subtotal = precoUnitario.multiply(BigDecimal.valueOf(quantidade));

                        // Adiciona à lista
                        List<Pagina_de_venda.Table_1> listaItens = model.getTable_1();
                        if (listaItens == null) {
                            listaItens = new ArrayList<>();
                        }

                        Pagina_de_venda.Table_1 linha = new Pagina_de_venda.Table_1();
                        linha.setProdutoId(produtoIdParam);
                        linha.setProdutoNome(produto.getNome());
                        linha.setQuantidade(String.valueOf(quantidade));
                        linha.setPrecoUnitario(Pagina_de_venda.Table_1.formatarValor(precoUnitario));
                        linha.setSubtotal(Pagina_de_venda.Table_1.formatarValor(subtotal));
                        linha.setId_tbl_item("temp_" + System.currentTimeMillis());

                        listaItens.add(linha);
                        model.setTable_1(listaItens);

                        // Calcula o total
                        BigDecimal total = BigDecimal.ZERO;
                        for (Pagina_de_venda.Table_1 item : listaItens) {
                            if (item.getSubtotal() != null && !item.getSubtotal().isEmpty()) {
                                try {
                                    String valor = item.getSubtotal().replace("R$", "").replace(",", ".").trim();
                                    total = total.add(new BigDecimal(valor));
                                } catch (Exception e) {
                                    // Ignora erros de parsing
                                }
                            }
                        }
                        model.setTotal_venda(Pagina_de_venda.Table_1.formatarValor(total));

                    } else {
                        Core.setMessageError("Produto não encontrado ou inativo.");
                    }
                } else {
                    Core.setMessageError("Quantidade inválida.");
                }
            } catch (Exception e) {
                Core.setMessageError("Erro ao adicionar produto: " + e.getMessage());
            }
        } else {
            Core.setMessageError("Selecione um produto e informe a quantidade.");
        }

        /*----#end-code----*/

        view.setModel(model);
        return this.renderView(view);
    }

    // =========================================================================
    // REMOVER_PRODUTO — remove um produto da venda
    // =========================================================================
    public Response actionRemover_produto() throws IOException, IllegalArgumentException, IllegalAccessException {
        var model = new Pagina_de_venda();
        model.load();
        var view = new Pagina_de_vendaView();

        /*----#start-code(remover_produto)----*/

        String idItem = Core.getParam("p_id_tbl_item");

        if (idItem != null && !idItem.trim().isEmpty()) {
            List<Pagina_de_venda.Table_1> listaItens = model.getTable_1();
            if (listaItens != null) {
                listaItens.removeIf(item -> idItem.equals(item.getId_tbl_item()));
                model.setTable_1(listaItens);

                // Recalcula o total
                BigDecimal total = BigDecimal.ZERO;
                for (Pagina_de_venda.Table_1 item : listaItens) {
                    if (item.getSubtotal() != null && !item.getSubtotal().isEmpty()) {
                        try {
                            String valor = item.getSubtotal().replace("R$", "").replace(",", ".").trim();
                            total = total.add(new BigDecimal(valor));
                        } catch (Exception e) {
                            // Ignora erros de parsing
                        }
                    }
                }
                model.setTotal_venda(Pagina_de_venda.Table_1.formatarValor(total));
            }
        }

        /*----#end-code----*/

        view.setModel(model);
        return this.renderView(view);
    }

    // =========================================================================
    // SALVAR — salva a venda
    // =========================================================================
    public Response actionSalvar() throws IOException, IllegalArgumentException, IllegalAccessException {
        var model = new Pagina_de_venda();
        model.load();

        /*----#start-code(salvar)----*/

        String idVenda = Core.getParam("p_id");
        String clienteIdParam = Core.getParam("p_cliente");
        String dataParam = Core.getParam("p_data");

        if (clienteIdParam == null || clienteIdParam.trim().isEmpty()) {
            Core.setMessageError("Selecione um cliente.");
            return this.forward("gestao_de_vendas", "Pagina_de_venda", "index", this.queryString());
        }

        if (dataParam == null || dataParam.trim().isEmpty()) {
            Core.setMessageError("A data da venda é obrigatória.");
            return this.forward("gestao_de_vendas", "Pagina_de_venda", "index", this.queryString());
        }

        try {
            Integer clienteId = Core.toInt(clienteIdParam);
            LocalDateTime dataVenda = LocalDateTime.parse(dataParam + "T00:00:00");

            // Valida se o cliente existe
            Clientes cliente = new Clientes().findOne(clienteId);
            if (cliente == null) {
                Core.setMessageError("Cliente não encontrado.");
                return this.forward("gestao_de_vendas", "Pagina_de_venda", "index", this.queryString());
            }

            // Valida se há itens na venda
            List<Pagina_de_venda.Table_1> itens = model.getTable_1();
            if (itens == null || itens.isEmpty()) {
                Core.setMessageError("Adicione pelo menos um produto à venda.");
                return this.forward("gestao_de_vendas", "Pagina_de_venda", "index", this.queryString());
            }

            // Calcula o total
            BigDecimal total = BigDecimal.ZERO;
            for (Pagina_de_venda.Table_1 item : itens) {
                if (item.getSubtotal() != null && !item.getSubtotal().isEmpty()) {
                    try {
                        String valor = item.getSubtotal().replace("R$", "").replace(",", ".").trim();
                        total = total.add(new BigDecimal(valor));
                    } catch (Exception e) {
                        Core.setMessageError("Erro ao calcular total: " + e.getMessage());
                        return this.forward("gestao_de_vendas", "Pagina_de_venda", "index", this.queryString());
                    }
                }
            }

            // Salva a venda
            Vendas venda = new Vendas();
            venda.setClienteId(clienteId);
            venda.setUsuarioId(1); // TODO: pegar do usuário logado
            venda.setDataVenda(dataVenda);
            venda.setTotal(total);
            venda.setStatus("ATIVO");

            venda = venda.insert();
            if (venda == null || venda.hasError()) {
                Core.setMessageError("Erro ao salvar venda: " + (venda != null ? venda.getError() : "Erro desconhecido"));
                return this.forward("gestao_de_vendas", "Pagina_de_venda", "index", this.queryString());
            }

            // Salva os itens da venda
            for (Pagina_de_venda.Table_1 item : itens) {
                if (item.getProdutoId() != null && !item.getProdutoId().isEmpty() &&
                    item.getQuantidade() != null && !item.getQuantidade().isEmpty()) {

                    ItensVenda itensVenda = new ItensVenda();
                    itensVenda.setVendaId(venda.getId());
                    itensVenda.setProdutoId(Core.toInt(item.getProdutoId()));
                    itensVenda.setQuantidade(Core.toInt(item.getQuantidade()));
                    
                    // Recupera o preço unitário
                    if (item.getPrecoUnitario() != null && !item.getPrecoUnitario().isEmpty()) {
                        try {
                            String valor = item.getPrecoUnitario().replace("R$", "").replace(",", ".").trim();
                            itensVenda.setPrecoUnitario(new BigDecimal(valor));
                        } catch (Exception e) {
                            Core.setMessageError("Erro ao salvar itens: preço inválido");
                            return this.forward("gestao_de_vendas", "Pagina_de_venda", "index", this.queryString());
                        }
                    }

                    itensVenda.calcularSubtotal();
                    itensVenda = itensVenda.insert();
                    if (itensVenda == null || itensVenda.hasError()) {
                        Core.setMessageError("Erro ao salvar itens da venda: " + (itensVenda != null ? itensVenda.getError() : "Erro desconhecido"));
                        return this.forward("gestao_de_vendas", "Pagina_de_venda", "index", this.queryString());
                    }
                }
            }

            Core.setMessageSuccess("Venda realizada com sucesso!");
            return this.redirect("gestao_de_vendas", "Lista_de_vendas", "index");

        } catch (Exception e) {
            Core.setMessageError("Erro ao salvar venda: " + e.getMessage());
            return this.forward("gestao_de_vendas", "Pagina_de_venda", "index", this.queryString());
        }

        /*----#end-code----*/
    }

    /* Start-Code-Block (custom-actions)  *//* End-Code-Block  */
    /*----#start-code(custom_actions)----*/
    /*----#end-code----*/
}
