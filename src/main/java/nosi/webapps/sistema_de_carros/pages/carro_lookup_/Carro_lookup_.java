package nosi.webapps.sistema_de_carros.pages.carro_lookup_;

import nosi.core.gui.components.IGRPTable;
import nosi.core.webapp.Model;
import nosi.core.webapp.RParam;
import nosi.core.webapp.databse.helpers.BaseQueryInterface;
import java.util.ArrayList;
import java.util.List;

public class Carro_lookup_ extends Model{		
	
	private List<Table_1> table_1 = new ArrayList<>();	
	public void setTable_1(List<Table_1> table_1){
		this.table_1 = table_1;
	}
	public List<Table_1> getTable_1(){
		return this.table_1;
	}



	public static class Table_1 extends IGRPTable.Table{
		private String marca;
		private String modelo;
		private String data_registro;
		private String id_carro;
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

		public void setData_registro(String data_registro){
			this.data_registro = data_registro;
		}
		public String getData_registro(){
			return this.data_registro;
		}

		public void setId_carro(String id_carro){
			this.id_carro = id_carro;
		}
		public String getId_carro(){
			return this.id_carro;
		}

	}

	public void loadTable_1(BaseQueryInterface query) {
		this.setTable_1(this.loadTable(query,Table_1.class));
	}

}