package nosi.webapps.gestao_de_vendas.pages.cadastro_de_fornecedor;

import nosi.core.webapp.Model;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;
import static nosi.core.i18n.Translator.gt;

public class Cadastro_de_fornecedorView extends View {

	// ── Campos do Formulário ──────────────────────────────────────────────────
	public Field nome;
	public Field telefone;
	public Field email;
	public Field endereco;
	public Field id_fornecedor; // ← hidden essencial para edição (INSERT vs UPDATE)

	// ── Componentes de Layout ─────────────────────────────────────────────────
	public IGRPForm     form_1;
	public IGRPToolsBar toolsbar_1;

	// ── Botões ────────────────────────────────────────────────────────────────
	public IGRPButton btn_salvar;
	public IGRPButton btn_cancelar;

	public Cadastro_de_fornecedorView() {

		this.setPageTitle("Cadastro de Fornecedor");

		form_1     = new IGRPForm("form_1", "Formulário de Fornecedor");
		toolsbar_1 = new IGRPToolsBar("toolsbar_1");

		// ── Nome ──────────────────────────────────────────────────────────────
		nome = new TextField(model, "nome");
		nome.setLabel(gt("Nome do Fornecedor"));
		nome.propertie()
				.add("name",        "p_nome")
				.add("type",        "text")
				.add("maxlength",   "250")
				.add("required",    "true")
				.add("placeholder", gt("Ex: Empresa ABC Lda"));

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

		// ── E-mail ────────────────────────────────────────────────────────────
		email = new TextField(model, "email");
		email.setLabel(gt("E-mail"));
		email.propertie()
				.add("name",        "p_email")
				.add("type",        "text")
				.add("maxlength",   "250")
				.add("required",    "true")
				.add("placeholder", gt("Ex: contacto@empresa.com"));

		// ── Endereço ──────────────────────────────────────────────────────────
		endereco = new TextField(model, "endereco");
		endereco.setLabel(gt("Endereço"));
		endereco.propertie()
				.add("name",        "p_endereco")
				.add("type",        "text")
				.add("maxlength",   "250")
				.add("required",    "true")
				.add("placeholder", gt("Ex: Rua Principal, 123"));

		// ── ID Hidden (essencial para edição — distingue INSERT de UPDATE) ────
		id_fornecedor = new HiddenField(model, "id_fornecedor");
		id_fornecedor.propertie()
				.add("name", "p_id")
				.add("type", "hidden");

		// ── Botão Salvar ──────────────────────────────────────────────────────
		btn_salvar = new IGRPButton(
				"Salvar",
				"gestao_de_vendas",
				"Cadastro_de_fornecedor",
				"salvar",
				"submit_form",
				"success|fa-save",
				"", ""
		);
		btn_salvar.propertie
				.add("type", "specific")
				.add("rel",  "salvar")
				.add("refresh_components", "");

		// ── Botão Cancelar ────────────────────────────────────────────────────
// ── Botão Cancelar ────────────────────────────────────────────────────
		btn_cancelar = new IGRPButton(
				"Cancelar",
				"gestao_de_vendas",
				"Pagina_fornecedor",
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
		form_1.addField(nome);
		form_1.addField(telefone);
		form_1.addField(email);
		form_1.addField(endereco);
		form_1.addField(id_fornecedor); // ← hidden dentro do form

		toolsbar_1.addButton(btn_salvar);
		toolsbar_1.addButton(btn_cancelar);

		this.addToPage(toolsbar_1);
		this.addToPage(form_1);
	}

	@Override
	public void setModel(Model model) {
		nome.setValue(model);
		telefone.setValue(model);
		email.setValue(model);
		endereco.setValue(model);
		id_fornecedor.setValue(model); // ← preenchido pelo actionIndex na edição
	}
}