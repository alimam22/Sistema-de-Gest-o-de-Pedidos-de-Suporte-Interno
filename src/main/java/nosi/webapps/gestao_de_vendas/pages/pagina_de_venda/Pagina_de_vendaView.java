package nosi.webapps.gestao_de_vendas.pages.pagina_de_venda;

import nosi.core.webapp.Model;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;
import static nosi.core.i18n.Translator.gt;

/**
 * View da página de venda.
 * Gerencia a renderização dos componentes da interface.
 */
public class Pagina_de_vendaView extends View {

    // --- Campos do Formulário Principal ---
    public Field id;
    public Field cliente;
    public Field data;
    public Field status;

    // --- Campos para Adicionar Produto ---
    public Field produtoId;
    public Field quantidade;

    // --- Tabela de Itens ---
    public Field produtoNome;
    public Field precoUnitario;
    public Field quantidade_tbl; // ← campo separado para a coluna da tabela
    public Field subtotal;
    public Field id_tbl_item;
    public IGRPTable table_1;

    // --- Totais ---
    public Field total_venda;

    // --- Componentes de Layout ---
    public IGRPForm form_1_venda;
    public IGRPForm form_2_produtos;
    public IGRPForm form_3_total;  // ← form dedicado ao total
    public IGRPToolsBar toolsbar_1;

    // --- Botões ---
    public IGRPButton btn_salvar;
    public IGRPButton btn_cancelar;
    public IGRPButton btn_adicionar_produto;
    public IGRPButton btn_remover_produto;

    public Pagina_de_vendaView() {

        this.setPageTitle(gt("Nova Venda"));

        form_1_venda    = new IGRPForm("form_1_venda",    gt("Dados da Venda"));
        form_2_produtos = new IGRPForm("form_2_produtos", gt("Adicionar Produto"));
        form_3_total    = new IGRPForm("form_3_total",    gt("Resumo"));
        toolsbar_1      = new IGRPToolsBar("toolsbar_1");

        // --- ID (Hidden) ---
        id = new HiddenField(null, "id");
        id.propertie().add("name", "p_id").add("type", "hidden");

        // --- Cliente ---
        cliente = new TextField(null, "cliente");
        cliente.setLabel(gt("Cliente"));
        cliente.propertie()
                .add("name", "p_cliente")
                .add("type", "text")
                .add("maxlength", "150")
                .add("required", "true")
                .add("placeholder", gt("Digite o nome do cliente"));

        // --- Data ---
        data = new DateField(null, "data");
        data.setLabel(gt("Data da Venda"));
        data.propertie()
                .add("name", "p_data")
                .add("type", "date")
                .add("required", "true");

        // --- Status ---
        status = new TextField(null, "status");
        status.setLabel(gt("Status"));
        status.propertie()
                .add("name", "p_status")
                .add("type", "text")
                .add("maxlength", "20")
                .add("readonly", "true");

        // --- Produto ID (Hidden) ---
        produtoId = new HiddenField(null, "produtoId");
        produtoId.propertie().add("name", "p_produto_id").add("type", "hidden");

        // --- Quantidade (formulário de adicionar produto) ---
        quantidade = new NumberField(null, "quantidade");
        quantidade.setLabel(gt("Quantidade"));
        quantidade.propertie()
                .add("name", "p_quantidade")
                .add("type", "number")
                .add("min", "1")
                .add("value", "1")
                .add("required", "true")
                .add("placeholder", gt("Digite a quantidade"));

        // --- Quantidade exclusiva para exibição na tabela ---
        quantidade_tbl = new NumberField(null, "quantidade_tbl");
        quantidade_tbl.setLabel(gt("Qtd."));
        quantidade_tbl.propertie()
                .add("name", "p_quantidade_tbl")
                .add("type", "number")
                .add("readonly", "true");

        // --- Tabela de Itens ---
        table_1 = new IGRPTable("table_1", gt("Itens da Venda"));

        produtoNome = new TextField(null, "produtoNome");
        produtoNome.setLabel(gt("Produto"));
        produtoNome.propertie()
                .add("name", "p_produto_nome")
                .add("type", "text")
                .add("maxlength", "200")
                .add("readonly", "true");

        precoUnitario = new TextField(null, "precoUnitario");
        precoUnitario.setLabel(gt("Preço Unitário"));
        precoUnitario.propertie()
                .add("name", "p_preco_unitario")
                .add("type", "text")
                .add("readonly", "true");

        subtotal = new TextField(null, "subtotal");
        subtotal.setLabel(gt("Subtotal"));
        subtotal.propertie()
                .add("name", "p_subtotal")
                .add("type", "text")
                .add("readonly", "true");

        id_tbl_item = new HiddenField(null, "id_tbl_item");
        id_tbl_item.setParam(true);
        id_tbl_item.propertie().add("name", "p_id_tbl_item").add("type", "hidden");

        // --- Total da Venda ---
        total_venda = new TextField(null, "total_venda");
        total_venda.setLabel(gt("Total da Venda"));
        total_venda.propertie()
                .add("name", "p_total_venda")
                .add("type", "text")
                .add("readonly", "true")
                .add("value", "R$ 0,00");

        // --- Botão Adicionar Produto ---
        btn_adicionar_produto = new IGRPButton(
                gt("Adicionar Produto"),
                "gestao_de_vendas",
                "Pagina_de_venda",
                "adicionar_produto",
                "submit_form",
                "info|fa-plus-circle",
                "", ""
        );
        btn_adicionar_produto.propertie
                .add("type", "specific")
                .add("rel", "adicionar_produto")
                .add("refresh_components", "table_1");

        // --- Botão Remover Produto ---
        btn_remover_produto = new IGRPButton(
                gt("Remover"),
                "gestao_de_vendas",
                "Pagina_de_venda",
                "remover_produto",
                "confirm",
                "danger|fa-trash",
                "", ""
        );
        btn_remover_produto.propertie
                .add("id", "btn_remover_produto")
                .add("type", "specific")
                .add("class", "danger")
                .add("rel", "remover_produto")
                .add("refresh_components", "table_1")
                .add("labelConfirm", gt("Deseja remover este item?"))
                .add("send-params", "p_id_tbl_item");

        // --- Botão Salvar ---
        btn_salvar = new IGRPButton(
                gt("Salvar Venda"),
                "gestao_de_vendas",
                "Pagina_de_venda",
                "salvar",
                "submit_form",
                "success|fa-save",
                "", ""
        );
        btn_salvar.propertie
                .add("type", "specific")
                .add("rel", "salvar");

        // --- Botão Cancelar ---
        btn_cancelar = new IGRPButton(
                gt("Cancelar"),
                "gestao_de_vendas",
                "Lista_de_vendas",
                "index",
                "link",
                "danger|fa-times",
                "", ""
        );
        btn_cancelar.propertie
                .add("id", "btn_cancelar")
                .add("type", "specific")
                .add("rel", "index");
    }

    @Override
    public void render() {

        // Form 1 — Dados da venda
        form_1_venda.addField(id);
        form_1_venda.addField(cliente);
        form_1_venda.addField(data);
        form_1_venda.addField(status);

        // Form 2 — Adicionar produto
        form_2_produtos.addField(produtoId);
        form_2_produtos.addField(quantidade);

        // Tabela — usa quantidade_tbl (objeto distinto de `quantidade`)
        table_1.addField(produtoNome);
        table_1.addField(quantidade_tbl); // ← corrigido: campo exclusivo da tabela
        table_1.addField(precoUnitario);
        table_1.addField(subtotal);
        table_1.addField(id_tbl_item);
        table_1.addButton(btn_remover_produto);

        // Form 3 — Total da venda (dentro de um form, não solto na página)
        form_3_total.addField(total_venda);

        // Ordem de renderização na página
        this.addToPage(form_1_venda);
        this.addToPage(form_2_produtos);
        this.addToPage(btn_adicionar_produto);
        this.addToPage(table_1);
        this.addToPage(form_3_total);  // ← corrigido

        // Toolbar sempre por último
        toolsbar_1.addButton(btn_salvar);
        toolsbar_1.addButton(btn_cancelar);
        this.addToPage(toolsbar_1); // ← movido para o fim
    }

    @Override
    public void setModel(Model model) {
        Pagina_de_venda m = (Pagina_de_venda) model; // ← cast único extraído

        id.setValue(m);
        cliente.setValue(m);
        data.setValue(m);
        status.setValue(m);
        total_venda.setValue(m);

        produtoId.setValue(m);
        quantidade.setValue(m);
        produtoNome.setValue(m);
        quantidade_tbl.setValue(m); // ← adicionado
        precoUnitario.setValue(m);
        subtotal.setValue(m);
        id_tbl_item.setValue(m);

        table_1.loadModel(m.getTable_1());
    }
}