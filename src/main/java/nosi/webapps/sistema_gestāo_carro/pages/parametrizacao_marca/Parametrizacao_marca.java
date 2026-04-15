package nosi.webapps.sistema_gestāo_carro.pages.parametrizacao_marca;

import nosi.core.gui.components.IGRPTable;
import nosi.core.webapp.Model;
import nosi.core.webapp.RParam;
import nosi.core.webapp.databse.helpers.BaseQueryInterface;
import java.util.ArrayList;
import java.util.List;

import nosi.core.validator.constraints.*;

public class Parametrizacao_marca extends Model{		

	@NotNull()
	@RParam(rParamName = "p_marca")
	private String marca;

	@NotNull()
	@RParam(rParamName = "p_id_marca")
	private String id_marca;
	
	private List<Table_2> table_2 = new ArrayList<>();	
	public void setTable_2(List<Table_2> table_2){
		this.table_2 = table_2;
	}
	public List<Table_2> getTable_2(){
		return this.table_2;
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


	public static class Table_2 extends IGRPTable.Table{
		private String marca_tbl_1;
		private String id_marca_tbl;
		public void setMarca_tbl_1(String marca_tbl_1){
			this.marca_tbl_1 = marca_tbl_1;
		}
		public String getMarca_tbl_1(){
			return this.marca_tbl_1;
		}

		public void setId_marca_tbl(String id_marca_tbl){
			this.id_marca_tbl = id_marca_tbl;
		}
		public String getId_marca_tbl(){
			return this.id_marca_tbl;
		}

	}

	public void loadTable_2(BaseQueryInterface query) {
		this.setTable_2(this.loadTable(query,Table_2.class));
	}

}