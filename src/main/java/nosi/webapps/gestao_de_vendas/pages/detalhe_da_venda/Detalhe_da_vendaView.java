package nosi.webapps.gestao_de_vendas.pages.detalhe_da_venda;

import nosi.core.webapp.Model;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;
import static nosi.core.i18n.Translator.gt;

/**
 * View do detalhe da venda.
 * Gerencia a renderização da tabela e componentes da interface.
 */
public class Detalhe_da_vendaView extends View {

    public Field produtos_comprados;
    public Field quantidade;
    public Field preco;
    public Field id_tbl_detalha;
    public IGRPTable table_1;

    public IGRPButton btn_voltar;

    public Detalhe_da_vendaView() {

        this.setPageTitle(gt("Detalhe da Venda"));

        table_1 = new IGRPTable("table_1", gt("Itens da Venda"));

        produtos_comprados = new TextField(model, "produtos_comprados");
        produtos_comprados.setLabel(gt("Produto"));
        produtos_comprados.propertie().add("name", "p_produtos_comprados").add("type", "text").add("maxlength", "200").add("showLabel", "true");

        quantidade = new NumberField(model, "quantidade");
        quantidade.setLabel(gt("Quantidade"));
        quantidade.propertie().add("name", "p_quantidade").add("type", "number").add("maxlength", "10").add("showLabel", "true");

        preco = new NumberField(model, "preco");
        preco.setLabel(gt("Preço Unitário"));
        preco.propertie().add("name", "p_preco").add("type", "number").add("numberformat", "currency").add("maxlength", "20").add("showLabel", "true");

        id_tbl_detalha = new HiddenField(model, "id_tbl_detalha");
        id_tbl_detalha.setLabel(gt(""));
        id_tbl_detalha.propertie().add("name", "p_id_tbl_detalha").add("type", "hidden").add("tag", "id_tbl_detalha");

        // Botão Voltar
        btn_voltar = new IGRPButton(
                gt("Voltar"),
                "gestao_de_vendas",
                "Lista_de_vendas",
                "index",
                "link",
                "primary|fa-arrow-left",
                "", ""
        );
        btn_voltar.propertie.add("id", "btn_voltar").add("type", "specific").add("rel", "index");
    }

    @Override
    public void render() {

        table_1.addField(produtos_comprados);
        table_1.addField(quantidade);
        table_1.addField(preco);
        table_1.addField(id_tbl_detalha);

        this.addToPage(btn_voltar);
        this.addToPage(table_1);
    }

    @Override
    public void setModel(Model model) {

        produtos_comprados.setValue(model);
        quantidade.setValue(model);
        preco.setValue(model);
        id_tbl_detalha.setValue(model);

        table_1.loadModel(((Detalhe_da_venda) model).getTable_1());
    }
}
