package nosi.webapps.gestao_de_pedidos_de_suporte_.pages.detalheshistorico;

import nosi.core.gui.components.IGRPTable;
import nosi.core.webapp.Model;
import nosi.core.webapp.RParam;
import nosi.core.webapp.databse.helpers.BaseQueryInterface;
import java.util.ArrayList;
import java.util.List;

public class Detalheshistorico extends Model{		

	@RParam(rParamName = "p_n_do_pedido")
	private String n_do_pedido;

	@RParam(rParamName = "p_data_de_abertura")
	private String data_de_abertura;

	@RParam(rParamName = "p_estado")
	private String estado;

	@RParam(rParamName = "p_departamento")
	private String departamento;

	@RParam(rParamName = "p_categoria")
	private String categoria;

	@RParam(rParamName = "p_form_1_hidden_1")
	private String form_1_hidden_1;

	@RParam(rParamName = "p_nova_interacao__resolucao")
	private String nova_interacao__resolucao;

	@RParam(rParamName = "p_adicionar_comentario")
	private String adicionar_comentario;

	@RParam(rParamName = "p_comentario_editar_id")
	private String comentario_editar_id;
	
	private List<Table_1> table_1 = new ArrayList<>();	
	public void setTable_1(List<Table_1> table_1){
		this.table_1 = table_1;
	}
	public List<Table_1> getTable_1(){
		return this.table_1;
	}

	
	private List<Table_2> table_2 = new ArrayList<>();	
	public void setTable_2(List<Table_2> table_2){
		this.table_2 = table_2;
	}
	public List<Table_2> getTable_2(){
		return this.table_2;
	}

	
	public void setN_do_pedido(String n_do_pedido){
		this.n_do_pedido = n_do_pedido;
	}
	public String getN_do_pedido(){
		return this.n_do_pedido;
	}
	
	public void setData_de_abertura(String data_de_abertura){
		this.data_de_abertura = data_de_abertura;
	}
	public String getData_de_abertura(){
		return this.data_de_abertura;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
	}
	public String getEstado(){
		return this.estado;
	}
	
	public void setDepartamento(String departamento){
		this.departamento = departamento;
	}
	public String getDepartamento(){
		return this.departamento;
	}
	
	public void setCategoria(String categoria){
		this.categoria = categoria;
	}
	public String getCategoria(){
		return this.categoria;
	}
	
	public void setForm_1_hidden_1(String form_1_hidden_1){
		this.form_1_hidden_1 = form_1_hidden_1;
	}
	public String getForm_1_hidden_1(){
		return this.form_1_hidden_1;
	}
	
	public void setNova_interacao__resolucao(String nova_interacao__resolucao){
		this.nova_interacao__resolucao = nova_interacao__resolucao;
	}
	public String getNova_interacao__resolucao(){
		return this.nova_interacao__resolucao;
	}
	
	public void setAdicionar_comentario(String adicionar_comentario){
		this.adicionar_comentario = adicionar_comentario;
	}
	public String getAdicionar_comentario(){
		return this.adicionar_comentario;
	}
	
	public void setComentario_editar_id(String comentario_editar_id){
		this.comentario_editar_id = comentario_editar_id;
	}
	public String getComentario_editar_id(){
		return this.comentario_editar_id;
	}


	public static class Table_1 extends IGRPTable.Table{
		private String datahora;
		private String tecnico;
		private String estado_anterior;
		private String novo_estado;
		private String observacao;
		private String tbi_historico_id;
		public void setDatahora(String datahora){
			this.datahora = datahora;
		}
		public String getDatahora(){
			return this.datahora;
		}

		public void setTecnico(String tecnico){
			this.tecnico = tecnico;
		}
		public String getTecnico(){
			return this.tecnico;
		}

		public void setEstado_anterior(String estado_anterior){
			this.estado_anterior = estado_anterior;
		}
		public String getEstado_anterior(){
			return this.estado_anterior;
		}

		public void setNovo_estado(String novo_estado){
			this.novo_estado = novo_estado;
		}
		public String getNovo_estado(){
			return this.novo_estado;
		}

		public void setObservacao(String observacao){
			this.observacao = observacao;
		}
		public String getObservacao(){
			return this.observacao;
		}

		public void setTbi_historico_id(String tbi_historico_id){
			this.tbi_historico_id = tbi_historico_id;
		}
		public String getTbi_historico_id(){
			return this.tbi_historico_id;
		}

	}
	public static class Table_2 extends IGRPTable.Table{
		private String historico_de_comentario;
		private String tbl_comentario_id;
		public void setHistorico_de_comentario(String historico_de_comentario){
			this.historico_de_comentario = historico_de_comentario;
		}
		public String getHistorico_de_comentario(){
			return this.historico_de_comentario;
		}

		public void setTbl_comentario_id(String tbl_comentario_id){
			this.tbl_comentario_id = tbl_comentario_id;
		}
		public String getTbl_comentario_id(){
			return this.tbl_comentario_id;
		}

	}

	public void loadTable_1(BaseQueryInterface query) {
		this.setTable_1(this.loadTable(query,Table_1.class));
	}

	public void loadTable_2(BaseQueryInterface query) {
		this.setTable_2(this.loadTable(query,Table_2.class));
	}

}