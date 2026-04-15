package nosi.webapps.sistema_de_carros.pages.lista_de_carros_;

import nosi.core.gui.components.IGRPLink;
import nosi.core.webapp.Report;
import nosi.core.gui.components.IGRPTable;
import nosi.core.webapp.Model;
import nosi.core.webapp.RParam;
import nosi.core.webapp.databse.helpers.BaseQueryInterface;
import java.util.ArrayList;
import java.util.List;
//classe herda Model do IGRP
public class Lista_de_carros_ extends Model{		
    //@RParam mapeia campos da interface (View) para o Model.
	@RParam(rParamName = "p_sectionheader_1_text")
	private String sectionheader_1_text;

	@RParam(rParamName = "p_preco")
	private String preco;

	@RParam(rParamName = "p_intervalo_de_data")
	private String intervalo_de_data;
	//IGRPTable.Table permite trabalhar com listas de dados
	private List<Table_1> table_1 = new ArrayList<>();	
	public void setTable_1(List<Table_1> table_1){
		this.table_1 = table_1;
	}
	public List<Table_1> getTable_1(){
		return this.table_1;
	}

	
	public void setSectionheader_1_text(String sectionheader_1_text){
		this.sectionheader_1_text = sectionheader_1_text;
	}
	public String getSectionheader_1_text(){
		return this.sectionheader_1_text;
	}
	
	public void setPreco(String preco){
		this.preco = preco;
	}
	public String getPreco(){
		return this.preco;
	}
	
	public void setIntervalo_de_data(String intervalo_de_data){
		this.intervalo_de_data = intervalo_de_data;
	}
	public String getIntervalo_de_data(){
		return this.intervalo_de_data;
	}


	public static class Table_1 extends IGRPTable.Table{
		private String foto="../images/IGRP/IGRP2.3/assets/img/jon_doe.jpg";
		private String foto_uuid;
		private String marca_tbl;
		private Integer preco_tbl;
		private String modelo_tbl;
		private IGRPLink manual_tbl;
		private String manual_tbl_desc= "Manual_tbl";
		private String data_resgistro_tbl;
		private String id_carro_tbl;
		public void setFoto(String foto){
			this.foto = foto;
		}
		public String getFoto(){
			return this.foto;
		}
		public void setFoto_uuid(String foto_uuid){
			this.foto_uuid = foto_uuid;
		}
		public String getFoto_uuid(){
			return this.foto_uuid;
		}

		public void setMarca_tbl(String marca_tbl){
			this.marca_tbl = marca_tbl;
		}
		public String getMarca_tbl(){
			return this.marca_tbl;
		}

		public void setPreco_tbl(Integer preco_tbl){
			this.preco_tbl = preco_tbl;
		}
		public Integer getPreco_tbl(){
			return this.preco_tbl;
		}

		public void setModelo_tbl(String modelo_tbl){
			this.modelo_tbl = modelo_tbl;
		}
		public String getModelo_tbl(){
			return this.modelo_tbl;
		}

		public IGRPLink setManual_tbl(String app,String page,String action){
			this.manual_tbl = new IGRPLink(app,page,action);
			return this.manual_tbl;
		}
		public IGRPLink getManual_tbl(){
			return this.manual_tbl;
		}
		public void setManual_tbl_desc(String manual_tbl_desc){
			this.manual_tbl_desc = manual_tbl_desc;
		}
		public String getManual_tbl_desc(){
			return this.manual_tbl_desc;
		}
	public IGRPLink setManual_tbl(String link){
		this.manual_tbl = new IGRPLink(link);
		return this.manual_tbl;
	}
	public IGRPLink setManual_tbl(Report link){
		this.manual_tbl = new IGRPLink(link);
		return this.manual_tbl;
	}

		public void setData_resgistro_tbl(String data_resgistro_tbl){
			this.data_resgistro_tbl = data_resgistro_tbl;
		}
		public String getData_resgistro_tbl(){
			return this.data_resgistro_tbl;
		}

		public void setId_carro_tbl(String id_carro_tbl){
			this.id_carro_tbl = id_carro_tbl;
		}
		public String getId_carro_tbl(){
			return this.id_carro_tbl;
		}

	}

	public void loadTable_1(BaseQueryInterface query) {
		this.setTable_1(this.loadTable(query,Table_1.class));
	}

}