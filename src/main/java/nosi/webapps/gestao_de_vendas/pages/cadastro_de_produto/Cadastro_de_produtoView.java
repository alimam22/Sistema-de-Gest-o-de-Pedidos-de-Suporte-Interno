package nosi.webapps.gestao_de_vendas.pages.cadastro_de_produto;

import nosi.core.webapp.Model;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;
import static nosi.core.i18n.Translator.gt;

public class Cadastro_de_produtoView extends View {

	// ── Campos do Formulário ──────────────────────────────────────────────────
	public Field nome;
	public Field estoque;
	public Field preco_venda;
	public Field preco_compra;
	public Field categoria;
	public Field id_produto;

	// ── Componentes de Layout ─────────────────────────────────────────────────
	public IGRPForm     form_1;
	public IGRPToolsBar toolsbar_1;

	// ── Botões ────────────────────────────────────────────────────────────────
	public IGRPButton btn_salva;
	public IGRPButton btn_cancelar;

	public Cadastro_de_produtoView() {

		this.setPageTitle("Cadastro de Produto");

		form_1     = new IGRPForm("form_1", "Formulário de Produto");
		toolsbar_1 = new IGRPToolsBar("toolsbar_1");

		// ── Nome ──────────────────────────────────────────────────────────────
		nome = new TextField(model, "nome");
		nome.setLabel(gt("Nome do Produto"));
		nome.propertie()
				.add("name",        "p_nome")
				.add("type",        "text")
				.add("maxlength",   "250")
				.add("required",    "true")
				.add("placeholder", gt("Ex: Camiseta Azul"));

		// ── Estoque ───────────────────────────────────────────────────────────
		estoque = new NumberField(model, "estoque");
		estoque.setLabel(gt("Estoque"));
		estoque.propertie()
				.add("name",        "p_estoque")
				.add("type",        "number")
				.add("maxlength",   "20")
				.add("required",    "true")
				.add("placeholder", gt("Ex: 100"))
				.add("java-type",   "");

		// ── Preço de Venda ────────────────────────────────────────────────────
		preco_venda = new NumberField(model, "preco_venda");
		preco_venda.setLabel(gt("Preço de Venda"));
		preco_venda.propertie()
				.add("name",        "p_preco_venda")
				.add("type",        "number")
				.add("maxlength",   "20")
				.add("required",    "true")
				.add("placeholder", gt("Ex: 1500"))
				.add("java-type",   "");

		// ── Preço de Compra ───────────────────────────────────────────────────
		preco_compra = new NumberField(model, "preco_compra");
		preco_compra.setLabel(gt("Preço de Compra"));
		preco_compra.propertie()
				.add("name",        "p_preco_compra")
				.add("type",        "number")
				.add("maxlength",   "20")
				.add("required",    "true")
				.add("placeholder", gt("Ex: 800"))
				.add("java-type",   "");

		// ── Categoria ─────────────────────────────────────────────────────────
		categoria = new TextField(model, "categoria");
		categoria.setLabel(gt("Categoria"));
		categoria.propertie()
				.add("name",        "p_categoria")
				.add("type",        "text")
				.add("maxlength",   "250")
				.add("required",    "true")
				.add("placeholder", gt("Ex: Vestuário"));

		// ── ID Hidden (essencial para edição — distingue INSERT de UPDATE) ────
		id_produto = new HiddenField(model, "id_produto");
		id_produto.propertie()
				.add("name", "p_id")
				.add("type", "hidden");

		// ── Botão Salvar ──────────────────────────────────────────────────────
		btn_salva = new IGRPButton(
				"Salvar",
				"gestao_de_vendas",
				"Cadastro_de_produto",
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
// ── Botão Cancelar ────────────────────────────────────────────────────
		btn_cancelar = new IGRPButton(
				"Cancelar",
				"gestao_de_vendas",
				"Pagina_produto",
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
		form_1.addField(estoque);
		form_1.addField(preco_venda);
		form_1.addField(preco_compra);
		form_1.addField(categoria);
		form_1.addField(id_produto); // ← hidden dentro do form

		toolsbar_1.addButton(btn_salva);
		toolsbar_1.addButton(btn_cancelar); // ← adicionado

		this.addToPage(toolsbar_1);
		this.addToPage(form_1);
	}

	@Override
	public void setModel(Model model) {
		nome.setValue(model);
		estoque.setValue(model);
		preco_venda.setValue(model);
		preco_compra.setValue(model);
		categoria.setValue(model);
		id_produto.setValue(model); // ← preenchido pelo actionIndex na edição
	}
}