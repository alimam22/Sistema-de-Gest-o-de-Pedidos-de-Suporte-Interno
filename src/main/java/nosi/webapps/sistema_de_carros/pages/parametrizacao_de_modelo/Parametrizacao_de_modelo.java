package nosi.webapps.sistema_de_carros.pages.parametrizacao_de_modelo;

import nosi.core.gui.components.IGRPTable;
import nosi.core.webapp.Model;
import nosi.core.webapp.RParam;
import nosi.core.webapp.databse.helpers.BaseQueryInterface;
import java.util.ArrayList;
import java.util.List;

import nosi.core.validator.constraints.*;

public class Parametrizacao_de_modelo extends Model{		

	@RParam(rParamName = "p_marca")
	private String marca;

	@RParam(rParamName = "p_modelo")
	private String modelo;

	@RParam(rParamName = "p_id_modelo")
	private String id_modelo;
	
	private List<Table_1> table_1 = new ArrayList<>();	
	public void setTable_1(List<Table_1> table_1){
		this.table_1 = table_1;
	}
	public List<Table_1> getTable_1(){
		return this.table_1;
	}

	
	public void setMarca(String marca){
		this.marca = marca;
	}
	public String getMarca(){
		return this.marca;
	}
	
	public void setModelo(String modelo){
		this.modelo = modelo;
	}
	public String getModelo(){
		return this.modelo;
	}
	
	public void setId_modelo(String id_modelo){
		this.id_modelo = id_modelo;
	}
	public String getId_modelo(){
		return this.id_modelo;
	}


	public static class Table_1 extends IGRPTable.Table{
		private String marca_tbl;
		private String modelo_tbl;
		private String id_modelo_tbl;
		public void setMarca_tbl(String marca_tbl){
			this.marca_tbl = marca_tbl;
		}
		public String getMarca_tbl(){
			return this.marca_tbl;
		}

		public void setModelo_tbl(String modelo_tbl){
			this.modelo_tbl = modelo_tbl;
		}
		public String getModelo_tbl(){
			return this.modelo_tbl;
		}

		public void setId_modelo_tbl(String id_modelo_tbl){
			this.id_modelo_tbl = id_modelo_tbl;
		}
		public String getId_modelo_tbl(){
			return this.id_modelo_tbl;
		}

	}

	public void loadTable_1(BaseQueryInterface query) {
		this.setTable_1(this.loadTable(query,Table_1.class));
	}

}