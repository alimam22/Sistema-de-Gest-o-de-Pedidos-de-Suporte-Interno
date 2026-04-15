package nosi.webapps.gestao_de_vendas.pages.lista_de_produtos;

import nosi.core.gui.components.IGRPTable;
import nosi.core.webapp.Model;
import nosi.core.webapp.databse.helpers.BaseQueryInterface;
import java.util.ArrayList;
import java.util.List;

public class Lista_de_produtos extends Model {

	// ── Tabela de Produtos ────────────────────────────────────────────────────
	private List<Table_1_produto> table_1_produto = new ArrayList<>();

	public void setTable_1_produto(List<Table_1_produto> table_1_produto) {
		this.table_1_produto = table_1_produto;
	}
	public List<Table_1_produto> getTable_1_produto() {
		return this.table_1_produto;
	}

	// ── Inner Class — linha da tabela ─────────────────────────────────────────
	public static class Table_1_produto extends IGRPTable.Table {
		private String  nome_produto;
		private Integer preco_produto;
		private Integer estoque_produto;
		private String  categoria_produto;
		private String  id_tbl_produto;

		public void setNome_produto(String nome_produto) { this.nome_produto = nome_produto; }
		public String getNome_produto() { return this.nome_produto; }

		public void setPreco_produto(Integer preco_produto) { this.preco_produto = preco_produto; }
		public Integer getPreco_produto() { return this.preco_produto; }

		public void setEstoque_produto(Integer estoque_produto) { this.estoque_produto = estoque_produto; }
		public Integer getEstoque_produto() { return this.estoque_produto; }

		public void setCategoria_produto(String categoria_produto) { this.categoria_produto = categoria_produto; }
		public String getCategoria_produto() { return this.categoria_produto; }

		public void setId_tbl_produto(String id_tbl_produto) { this.id_tbl_produto = id_tbl_produto; }
		public String getId_tbl_produto() { return this.id_tbl_produto; }
	}

	public void loadTable_1_produto(BaseQueryInterface query) {
		this.setTable_1_produto(this.loadTable(query, Table_1_produto.class));
	}
}