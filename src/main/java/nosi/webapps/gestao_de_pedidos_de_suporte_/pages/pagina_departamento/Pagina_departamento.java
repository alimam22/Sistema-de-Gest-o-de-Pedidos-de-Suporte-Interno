package nosi.webapps.gestao_de_pedidos_de_suporte_.pages.pagina_departamento;

import nosi.core.gui.components.IGRPTable;
import nosi.core.webapp.Model;
import nosi.core.webapp.RParam;
import java.util.ArrayList;
import java.util.List;
import nosi.core.validator.constraints.*;

public class Pagina_departamento extends Model {

	// NOVO: Campo para armazenar o ID do formulário (Edição)
	@RParam(rParamName = "p_id")
	private Integer p_id;

	@NotNull()
	@RParam(rParamName = "p_nome")
	private String nome;

	@NotNull()
	@RParam(rParamName = "p_siglas")
	private String siglas;

	@RParam(rParamName = "p_activos")
	private String activos;

	@NotNull()
	@RParam(rParamName = "p_data")
	private String data;

	private List<Table_1> table_1 = new ArrayList<>();

	// --- NOVOS: Getter e Setter para p_id ---
	public void setP_id(Integer p_id) {
		this.p_id = p_id;
	}

	public Integer getP_id() {
		return this.p_id;
	}

	// --- MÉTODOS EXISTENTES ---

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

	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}

	public String getSiglas() {
		return this.siglas;
	}

	public void setActivos(String activos) {
		this.activos = activos;
	}

	public String getActivos() {
		return this.activos;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getData() {
		return this.data;
	}

	public static class Table_1 extends IGRPTable.Table {
		private String nome_departamento;
		private String siglas_departamento;
		private String activo_departamento;
		private String activo_departamento_check;
		private String data_departamento;
		private String tbl_departamento_id;

		public void setNome_departamento(String nome_departamento) {
			this.nome_departamento = nome_departamento;
		}

		public String getNome_departamento() {
			return this.nome_departamento;
		}

		public void setSiglas_departamento(String siglas_departamento) {
			this.siglas_departamento = siglas_departamento;
		}

		public String getSiglas_departamento() {
			return this.siglas_departamento;
		}

		public void setActivo_departamento(String activo_departamento) {
			this.activo_departamento = activo_departamento;
		}

		public String getActivo_departamento() {
			return this.activo_departamento;
		}

		public void setActivo_departamento_check(String activo_departamento_check) {
			this.activo_departamento_check = activo_departamento_check;
		}

		public String getActivo_departamento_check() {
			return this.activo_departamento_check;
		}

		public void setData_departamento(String data_departamento) {
			this.data_departamento = data_departamento;
		}

		public String getData_departamento() {
			return this.data_departamento;
		}

		public void setTbl_departamento_id(String tbl_departamento_id) {
			this.tbl_departamento_id = tbl_departamento_id;
		}

		public String getTbl_departamento_id() {
			return this.tbl_departamento_id;
		}
	}
}