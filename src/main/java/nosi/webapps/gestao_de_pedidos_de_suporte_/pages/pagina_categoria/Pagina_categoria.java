package nosi.webapps.gestao_de_pedidos_de_suporte_.pages.pagina_categoria;

import nosi.core.gui.components.IGRPTable;
import nosi.core.webapp.Model;
import nosi.core.webapp.RParam;
import java.util.ArrayList;
import java.util.List;
import nosi.core.validator.constraints.*;

public class Pagina_categoria extends Model {

	// --- CAMPO ADICIONADO PARA CORRIGIR O ERRO ---
	@RParam(rParamName = "p_id")
	private Integer p_id;

	@NotNull()
	@RParam(rParamName = "p_nome")
	private String nome;

	@RParam(rParamName = "p_descricao")
	private String descricao;

	@RParam(rParamName = "p_activo")
	private String activo;

	@RParam(rParamName = "p_data")
	private String data;

	private List<Table_1> table_1 = new ArrayList<>();

	// --- GETTER E SETTER PARA p_id (Obrigatório para o PageHelper funcionar) ---
	public void setP_id(Integer p_id) {
		this.p_id = p_id;
	}

	public Integer getP_id() {
		return this.p_id;
	}

	// --- MÉTODOS RESTANTES ---

	public void setTable_1(List<Table_1> table_1) {
		this.table_1 = table_1;
	}

	public List<Table_1> getTable_1() {
		return this.table_1;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public String getActivo() {
		return this.activo;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getData() {
		return this.data;
	}

	public static class Table_1 extends IGRPTable.Table {
		private String nome_categoria;
		private String descricao_categoria;
		private String activo_categoria;
		private String data_categoria;
		private String tbl_categoria_id;

		public void setNome_categoria(String nome_categoria) {
			this.nome_categoria = nome_categoria;
		}

		public String getNome_categoria() {
			return this.nome_categoria;
		}

		public void setDescricao_categoria(String descricao_categoria) {
			this.descricao_categoria = descricao_categoria;
		}

		public String getDescricao_categoria() {
			return this.descricao_categoria;
		}

		public void setActivo_categoria(String activo_categoria) {
			this.activo_categoria = activo_categoria;
		}

		public String getActivo_categoria() {
			return this.activo_categoria;
		}

		public void setData_categoria(String data_categoria) {
			this.data_categoria = data_categoria;
		}

		public String getData_categoria() {
			return this.data_categoria;
		}

		public void setTbl_categoria_id(String tbl_categoria_id) {
			this.tbl_categoria_id = tbl_categoria_id;
		}

		public String getTbl_categoria_id() {
			return this.tbl_categoria_id;
		}
	}
}