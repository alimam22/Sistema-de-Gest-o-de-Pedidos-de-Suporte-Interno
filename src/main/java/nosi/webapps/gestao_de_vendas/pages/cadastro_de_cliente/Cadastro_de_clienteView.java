package nosi.webapps.gestao_de_vendas.pages.cadastro_de_cliente;

import nosi.core.webapp.Model;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;
import static nosi.core.i18n.Translator.gt;

/**
 * View do formulário de Cadastro de Cliente.
 * Gerencia a renderização dos campos e componentes da interface.
 */
public class Cadastro_de_clienteView extends View {

    // ── Campos do Formulário ──────────────────────────────────────────────────
    public Field nome;
    public Field email;
    public Field telefone;
    public Field nif;
    public Field endereco;
    public Field cidade;
    public Field id_cliente;

    // ── Componentes de Layout ─────────────────────────────────────────────────
    public IGRPForm     form_1_cliente;
    public IGRPToolsBar toolsbar_1;

    // ── Botões ────────────────────────────────────────────────────────────────
    public IGRPButton btn_salva;
    public IGRPButton btn_cancelar;

    public Cadastro_de_clienteView() {

        this.setPageTitle(gt("Cadastro de Cliente"));

        form_1_cliente = new IGRPForm("form_1_cliente", gt("Dados do Cliente"));
        toolsbar_1     = new IGRPToolsBar("toolsbar_1");

        // ── Nome ──────────────────────────────────────────────────────────────
        nome = new TextField(model, "nome");
        nome.setLabel(gt("Nome Completo"));
        nome.propertie()
                .add("name",        "p_nome")
                .add("type",        "text")
                .add("maxlength",   "250")
                .add("required",    "true")
                .add("placeholder", gt("Ex: João Silva"));

        // ── E-mail ────────────────────────────────────────────────────────────
        email = new TextField(model, "email");
        email.setLabel(gt("E-mail"));
        email.propertie()
                .add("name",        "p_email")
                .add("type",        "text")
                .add("maxlength",   "250")
                .add("required",    "true")
                .add("placeholder", gt("Ex: joao@email.com"));

        // ── Telefone ──────────────────────────────────────────────────────────
        telefone = new NumberField(model, "telefone");
        telefone.setLabel(gt("Telefone"));
        telefone.propertie()
                .add("name",        "p_telefone")
                .add("type",        "number")
                .add("maxlength",   "20")
                .add("required",    "true")
                .add("placeholder", gt("Ex: 9XXXXXXXX"))
                .add("java-type",   "");

        // ── NIF ───────────────────────────────────────────────────────────────
        nif = new NumberField(model, "nif");
        nif.setLabel(gt("NIF"));
        nif.propertie()
                .add("name",        "p_nif")
                .add("type",        "number")
                .add("maxlength",   "20")
                .add("required",    "true")
                .add("placeholder", gt("Ex: 000000000"))
                .add("java-type",   "");

        // ── Endereço ──────────────────────────────────────────────────────────
        endereco = new TextField(model, "endereco");
        endereco.setLabel(gt("Endereço"));
        endereco.propertie()
                .add("name",        "p_endereco")
                .add("type",        "text")
                .add("maxlength",   "500")
                .add("required",    "true")
                .add("placeholder", gt("Ex: Rua Principal, 123"));

        // ── Cidade ────────────────────────────────────────────────────────────
        cidade = new TextField(model, "cidade");
        cidade.setLabel(gt("Cidade"));
        cidade.propertie()
                .add("name",        "p_cidade")
                .add("type",        "text")
                .add("maxlength",   "150")
                .add("required",    "true")
                .add("placeholder", gt("Ex: Praia"));

        // ── ID Hidden ─────────────────────────────────────────────────────────
        id_cliente = new HiddenField(model, "id_cliente");
        id_cliente.propertie()
                .add("name", "p_id")
                .add("type", "hidden")
                .add("tag",  "id_cliente");

        // ── Botão Salvar ──────────────────────────────────────────────────────
        btn_salva = new IGRPButton(
                "Salvar",
                "gestao_de_vendas",
                "Cadastro_de_cliente",
                "salva",
                "submit_form",
                "success|fa-save",
                "", ""
        );
        btn_salva.propertie
                .add("type", "specific")
                .add("rel",  "salva")
                .add("refresh_components", "");

        // ── Botão Cancelar ────────────────────────────────────────────────────
        btn_cancelar = new IGRPButton(
                "Cancelar",
                "gestao_de_vendas",
                "Pagina_cliente",
                "index",
                "link", // ← MUDANÇA: de 'submit_form' para 'link'
                "danger|fa-times",
                "", ""
        );
        btn_cancelar.propertie
                .add("id",            "btn_cancelar")
                .add("type",          "specific")
                .add("rel",           "index")
                .add("refresh_components", "");
    }

    @Override
    public void render() {
        form_1_cliente.addField(nome);
        form_1_cliente.addField(email);
        form_1_cliente.addField(telefone);
        form_1_cliente.addField(nif);
        form_1_cliente.addField(endereco);
        form_1_cliente.addField(cidade);
        form_1_cliente.addField(id_cliente);

        // Ambos os botões na mesma toolsbar — cancelar usa flg_validate=false
        toolsbar_1.addButton(btn_salva);
        toolsbar_1.addButton(btn_cancelar);

        this.addToPage(toolsbar_1);
        this.addToPage(form_1_cliente);
    }

    @Override
    public void setModel(Model model) {
        nome.setValue(model);
        email.setValue(model);
        telefone.setValue(model);
        nif.setValue(model);
        endereco.setValue(model);
        cidade.setValue(model);
        id_cliente.setValue(model);
    }
}
