package nosi.webapps.sistema_de_carros.pages.parametrizacao_marcas;

import nosi.core.gui.components.IGRPTable;
import nosi.core.webapp.Model;
import nosi.core.webapp.RParam;
import nosi.core.webapp.databse.helpers.BaseQueryInterface;
import java.util.ArrayList;
import java.util.List;

import nosi.core.validator.constraints.*;

public class Parametrizacao_marcas extends Model{		

	@NotNull()
	@RParam(rParamName = "p_marca")
	private String marca;

	@RParam(rParamName = "p_id_marca")
	private String id_marca;
	
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
	
	public void setId_marca(String id_marca){
		this.id_marca = id_marca;
	}
	public String getId_marca(){
		return this.id_marca;
	}


	public static class Table_1 extends IGRPTable.Table{
		private String marca_tbl;
		private String id_marca_tbl;
		public void setMarca_tbl(String marca_tbl){
			this.marca_tbl = marca_tbl;
		}
		public String getMarca_tbl(){
			return this.marca_tbl;
		}

		public void setId_marca_tbl(String id_marca_tbl){
			this.id_marca_tbl = id_marca_tbl;
		}
		public String getId_marca_tbl(){
			return this.id_marca_tbl;
		}

	}

	public void loadTable_1(BaseQueryInterface query) {
		this.setTable_1(this.loadTable(query,Table_1.class));
	}

}