package nosi.webapps.gestao_de_vendas.pages.pagina_cliente;

import nosi.core.webapp.Model;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;
import static nosi.core.i18n.Translator.gt;

/**
 * View da lista de clientes.
 * Gerencia a renderização da tabela e componentes da interface.
 */
public class Pagina_clienteView extends View {

    // ── Colunas da Tabela ─────────────────────────────────────────────────────
    public Field nome_cliente;
    public Field email_cliente;
    public Field telefone_cliente;
    public Field endereco_cliente;
    public Field nif_cliente;
    public Field cidade_cliente;
    public Field id_tbl_cliente;

    // ── Componentes de Layout ─────────────────────────────────────────────────
    public IGRPTable    table_1;
    public IGRPToolsBar toolsbar_1;

    // ── Botões ────────────────────────────────────────────────────────────────
    public IGRPButton btn_novo_cliente;
    public IGRPButton btn_edit;
    public IGRPButton btn_eliminar;

    public Pagina_clienteView() {

        this.setPageTitle(gt("Lista de Clientes"));

        // ── Inicialização dos componentes ─────────────────────────────────────
        table_1    = new IGRPTable("table_1", gt("Clientes Registados"));
        toolsbar_1 = new IGRPToolsBar("toolsbar_1");

        // ── Nome ──────────────────────────────────────────────────────────────
        nome_cliente = new TextField(model, "nome_cliente");
        nome_cliente.setLabel(gt("Nome"));
        nome_cliente.propertie()
                .add("name",      "p_nome_cliente")
                .add("type",      "text")
                .add("maxlength", "250")
                .add("showLabel", "true");

        // ── E-mail ────────────────────────────────────────────────────────────
        email_cliente = new TextField(model, "email_cliente");
        email_cliente.setLabel(gt("E-mail"));
        email_cliente.propertie()
                .add("name",      "p_email_cliente")
                .add("type",      "text")
                .add("maxlength", "250")
                .add("showLabel", "true");

        // ── Telefone ──────────────────────────────────────────────────────────
        telefone_cliente = new NumberField(model, "telefone_cliente");
        telefone_cliente.setLabel(gt("Telefone"));
        telefone_cliente.propertie()
                .add("name",          "p_telefone_cliente")
                .add("type",          "number")
                .add("maxlength",     "20")
                .add("showLabel",     "true")
                .add("java-type",     "")
                .add("total_footer",  "false");

        // ── NIF ───────────────────────────────────────────────────────────────
        nif_cliente = new NumberField(model, "nif_cliente");
        nif_cliente.setLabel(gt("NIF"));
        nif_cliente.propertie()
                .add("name",          "p_nif_cliente")
                .add("type",          "number")
                .add("maxlength",     "20")
                .add("showLabel",     "true")
                .add("java-type",     "")
                .add("total_footer",  "false");

        // ── Endereço ──────────────────────────────────────────────────────────
        endereco_cliente = new TextField(model, "endereco_cliente");
        endereco_cliente.setLabel(gt("Endereço"));
        endereco_cliente.propertie()
                .add("name",      "p_endereco_cliente")
                .add("type",      "text")
                .add("maxlength", "500")
                .add("showLabel", "true");

        // ── Cidade ────────────────────────────────────────────────────────────
        cidade_cliente = new TextField(model, "cidade_cliente");
        cidade_cliente.setLabel(gt("Cidade"));
        cidade_cliente.propertie()
                .add("name",      "p_cidade_cliente")
                .add("type",      "text")
                .add("maxlength", "150")
                .add("showLabel", "true");

        // ── Chave da Tabela (hidden) ───────────────────────────────────────────
        // setParam(true) indica ao IGRP que este é o identificador de cada linha,
        // garantindo que o ID correto é enviado ao clicar em Editar ou Eliminar.
        id_tbl_cliente = new HiddenField(model, "id_tbl_cliente");
        id_tbl_cliente.setLabel(gt(""));
        id_tbl_cliente.setParam(true);
        id_tbl_cliente.propertie()
                .add("name",      "p_id_tbl_cliente")
                .add("type",      "hidden")
                .add("tag",       "id_tbl_cliente");

        // ── Botão Novo Cliente ────────────────────────────────────────────────
        btn_novo_cliente = new IGRPButton(
                gt("Novo Cliente"),
                "gestao_de_vendas",
                "Pagina_cliente",
                "novo_cliente",
                "submit_form",
                "success|fa-plus-square",
                "", ""
        );
        btn_novo_cliente.propertie
                .add("type", "specific")
                .add("rel",  "novo_cliente")
                .add("refresh_components", "");

        // ── Botão Editar (por linha) ───────────────────────────────────────────
        btn_edit = new IGRPButton(
                gt("Editar"),
                "gestao_de_vendas",
                "Pagina_cliente",
                "edit",
                "submit_row",
                "warning|fa-edit",
                "", ""
        );
        btn_edit.propertie
                .add("id",          "button_edit")
                .add("type",        "specific")
                .add("class",       "warning")
                .add("rel",         "edit")
                .add("send-params", "p_id_tbl_cliente");

        // ── Botão Eliminar (por linha, com confirmação) ────────────────────────
        btn_eliminar = new IGRPButton(
                gt("Eliminar"),
                "gestao_de_vendas",
                "Pagina_cliente",
                "eliminar",
                "confirm",
                "danger|fa-trash",
                "", ""
        );
        btn_eliminar.propertie
                .add("id",            "button_eliminar")
                .add("type",          "specific")
                .add("class",         "danger")
                .add("rel",           "eliminar")
                .add("refresh_components", "table_1")
                .add("labelConfirm",  gt("Tem certeza que deseja eliminar este cliente?"))
                .add("send-params",   "p_id_tbl_cliente");
    }

    @Override
    public void render() {
        // ── Colunas da tabela (ordem lógica de leitura) ───────────────────────
        table_1.addField(nome_cliente);
        table_1.addField(email_cliente);
        table_1.addField(telefone_cliente);
        table_1.addField(nif_cliente);
        table_1.addField(endereco_cliente);
        table_1.addField(cidade_cliente);
        table_1.addField(id_tbl_cliente);

        // ── Botões da toolsbar e da tabela ────────────────────────────────────
        toolsbar_1.addButton(btn_novo_cliente);
        table_1.addButton(btn_edit);
        table_1.addButton(btn_eliminar);

        // ── Ordem dos componentes na página ───────────────────────────────────
        this.addToPage(toolsbar_1);
        this.addToPage(table_1);
    }

    @Override
    public void setModel(Model model) {
        nome_cliente.setValue(model);
        email_cliente.setValue(model);
        telefone_cliente.setValue(model);
        nif_cliente.setValue(model);
        endereco_cliente.setValue(model);
        cidade_cliente.setValue(model);
        id_tbl_cliente.setValue(model);

        table_1.loadModel(((Pagina_cliente) model).getTable_1());
    }
}
