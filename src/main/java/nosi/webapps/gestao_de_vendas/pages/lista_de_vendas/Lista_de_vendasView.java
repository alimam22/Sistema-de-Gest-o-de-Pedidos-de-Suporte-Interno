package nosi.webapps.gestao_de_vendas.pages.lista_de_vendas;

import nosi.core.webapp.Model;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;
import static nosi.core.i18n.Translator.gt;

/**
 * View da lista de vendas.
 * Gerencia a renderização da tabela e componentes da interface.
 */
public class Lista_de_vendasView extends View {

    public Field cliente;
    public Field data;
    public Field total;
    public Field status;
    public Field id_tbl_historico_venda;
    public IGRPTable table_1;

    public IGRPButton btn_editar;
    public IGRPButton btn_eliminar;
    public IGRPButton btn_nova_venda;

    public Lista_de_vendasView() {

        this.setPageTitle(gt("Lista de Vendas"));

        table_1 = new IGRPTable("table_1", gt("Histórico de Vendas"));

        cliente = new TextField(model, "cliente");
        cliente.setLabel(gt("Cliente"));
        cliente.propertie().add("name", "p_cliente").add("type", "text").add("maxlength", "150").add("showLabel", "true");

        data = new TextField(model, "data");
        data.setLabel(gt("Data"));
        data.propertie().add("name", "p_data").add("type", "text").add("maxlength", "20").add("showLabel", "true");

        total = new NumberField(model, "total");
        total.setLabel(gt("Total"));
        total.propertie().add("name", "p_total").add("type", "number").add("numberformat", "currency").add("maxlength", "20").add("showLabel", "true").add("total_footer", "true");

        status = new TextField(model, "status");
        status.setLabel(gt("Status"));
        status.propertie().add("name", "p_status").add("type", "text").add("maxlength", "20").add("showLabel", "true");

        id_tbl_historico_venda = new HiddenField(model, "id_tbl_historico_venda");
        id_tbl_historico_venda.setLabel(gt(""));
        id_tbl_historico_venda.setParam(true);
        id_tbl_historico_venda.propertie().add("name", "p_id_tbl_historico_venda").add("type", "hidden").add("tag", "id_tbl_historico_venda");

        // Botão Nova Venda
        btn_nova_venda = new IGRPButton(
                gt("Nova Venda"),
                "gestao_de_vendas",
                "Pagina_de_venda",
                "index",
                "link",
                "success|fa-plus-circle",
                "", ""
        );
        btn_nova_venda.propertie.add("id", "btn_nova_venda").add("type", "specific").add("rel", "index");

        // Botão Editar
        btn_editar = new IGRPButton(
                gt("Editar"),
                "gestao_de_vendas",
                "Lista_de_vendas",
                "editar",
                "submit_row",
                "warning|fa-edit",
                "", ""
        );
        btn_editar.propertie.add("id", "button_69dd_7ed6").add("type", "specific").add("class", "warning").add("rel", "editar").add("send-params", "p_id_tbl_historico_venda");

        // Botão Eliminar
        btn_eliminar = new IGRPButton(
                gt("Eliminar"),
                "gestao_de_vendas",
                "Lista_de_vendas",
                "eliminar",
                "confirm",
                "danger|fa-trash",
                "", ""
        );
        btn_eliminar.propertie.add("id", "button_4100_3b1b").add("type", "specific").add("class", "danger").add("rel", "eliminar").add("refresh_components", "table_1").add("labelConfirm", gt("Deseja realmente eliminar esta venda?"));
    }

    @Override
    public void render() {

        table_1.addField(cliente);
        table_1.addField(data);
        table_1.addField(total);
        table_1.addField(status);
        table_1.addField(id_tbl_historico_venda);

        table_1.addButton(btn_editar);
        table_1.addButton(btn_eliminar);

        this.addToPage(btn_nova_venda);
        this.addToPage(table_1);
    }

    @Override
    public void setModel(Model model) {

        cliente.setValue(model);
        data.setValue(model);
        total.setValue(model);
        status.setValue(model);
        id_tbl_historico_venda.setValue(model);

        table_1.loadModel(((Lista_de_vendas) model).getTable_1());
    }
}
