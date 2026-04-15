package nosi.webapps.gestao_de_vendas.pages.lista_de_produtos;

import nosi.core.webapp.Model;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;
import static nosi.core.i18n.Translator.gt;

public class Lista_de_produtosView extends View {

	// ── Colunas da Tabela ─────────────────────────────────────────────────────
	public Field nome_produto;
	public Field preco_produto;
	public Field estoque_produto;
	public Field categoria_produto;
	public Field id_tbl_produto;

	// ── Componentes de Layout ─────────────────────────────────────────────────
	public IGRPTable    table_1_produto;
	public IGRPToolsBar toolsbar_1;

	// ── Botões ────────────────────────────────────────────────────────────────
	public IGRPButton btn_novo_produto;
	public IGRPButton btn_editar;
	public IGRPButton btn_eliminar;

	public Lista_de_produtosView() {

		this.setPageTitle("Lista de Produtos");

		table_1_produto = new IGRPTable("table_1_produto", "Produtos Registados");
		toolsbar_1      = new IGRPToolsBar("toolsbar_1");

		// ── Nome ──────────────────────────────────────────────────────────────
		nome_produto = new TextField(model, "nome_produto");
		nome_produto.setLabel(gt("Nome"));
		nome_produto.propertie()
				.add("name",      "p_nome_produto")
				.add("type",      "text")
				.add("maxlength", "250")
				.add("showLabel", "true");

		// ── Preço ─────────────────────────────────────────────────────────────
		preco_produto = new NumberField(model, "preco_produto");
		preco_produto.setLabel(gt("Preço"));
		preco_produto.propertie()
				.add("name",         "p_preco_produto")
				.add("type",         "number")
				.add("maxlength",    "20")
				.add("showLabel",    "true")
				.add("java-type",    "")
				.add("total_footer", "true"); // ← mostra o total no rodapé da coluna

		// ── Estoque ───────────────────────────────────────────────────────────
		estoque_produto = new NumberField(model, "estoque_produto");
		estoque_produto.setLabel(gt("Estoque"));
		estoque_produto.propertie()
				.add("name",         "p_estoque_produto")
				.add("type",         "number")
				.add("maxlength",    "20")
				.add("showLabel",    "true")
				.add("java-type",    "")
				.add("total_footer", "true"); // ← mostra o total no rodapé da coluna

		// ── Categoria ─────────────────────────────────────────────────────────
		categoria_produto = new TextField(model, "categoria_produto");
		categoria_produto.setLabel(gt("Categoria"));
		categoria_produto.propertie()
				.add("name",      "p_categoria_produto")
				.add("type",      "text")
				.add("maxlength", "250")
				.add("showLabel", "true");

		// ── Chave da Tabela (hidden) ──────────────────────────────────────────
		id_tbl_produto = new HiddenField(model, "id_tbl_produto");
		id_tbl_produto.setLabel(gt(""));
		id_tbl_produto.setParam(true);
		id_tbl_produto.propertie()
				.add("name", "p_id_tbl_produto")
				.add("type", "hidden")
				.add("tag",  "id_tbl_produto");

		// ── Botão Novo Produto ────────────────────────────────────────────────
		btn_novo_produto = new IGRPButton(
				"Novo Produto",
				"gestao_de_vendas",
				"Lista_de_produtos",
				"novo_produto",
				"submit_form",
				"success|fa-plus-square",
				"", ""
		);
		btn_novo_produto.propertie
				.add("type", "specific")
				.add("rel",  "novo_produto")
				.add("refresh_components", "");

		// ── Botão Editar (por linha) ───────────────────────────────────────────
		btn_editar = new IGRPButton(
				"Editar",
				"gestao_de_vendas",
				"Lista_de_produtos",
				"editar",
				"submit_row",
				"warning|fa-edit",
				"", ""
		);
		btn_editar.propertie
				.add("id",          "button_editar")
				.add("type",        "specific")
				.add("class",       "warning")
				.add("rel",         "editar")
				.add("send-params", "p_id_tbl_produto");

		// ── Botão Eliminar (por linha, com confirmação) ────────────────────────
		btn_eliminar = new IGRPButton(
				"Eliminar",
				"gestao_de_vendas",
				"Lista_de_produtos",
				"eliminar",
				"confirm_row",
				"danger|fa-trash",
				"", ""
		);
		btn_eliminar.propertie
				.add("id",           "button_eliminar")
				.add("type",         "specific")
				.add("class",        "danger")
				.add("rel",          "eliminar")
				.add("refresh_components", "table_1_produto")
				.add("labelConfirm", "Tem certeza que deseja eliminar este produto?")
				.add("send-params",  "p_id_tbl_produto");
	}

	@Override
	public void render() {
		table_1_produto.addField(nome_produto);
		table_1_produto.addField(categoria_produto);
		table_1_produto.addField(preco_produto);
		table_1_produto.addField(estoque_produto);
		table_1_produto.addField(id_tbl_produto);

		toolsbar_1.addButton(btn_novo_produto);
		table_1_produto.addButton(btn_editar);
		table_1_produto.addButton(btn_eliminar);

		this.addToPage(toolsbar_1);
		this.addToPage(table_1_produto);
	}

	@Override
	public void setModel(Model model) {
		nome_produto.setValue(model);
		categoria_produto.setValue(model);
		preco_produto.setValue(model);
		estoque_produto.setValue(model);
		id_tbl_produto.setValue(model);

		table_1_produto.loadModel(((Lista_de_produtos) model).getTable_1_produto());
	}
}