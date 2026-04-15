package nosi.webapps.sistema_de_carros.pages.teste;

import nosi.core.gui.components.IGRPTable;
import nosi.core.webapp.Model;
import nosi.core.webapp.RParam;
import nosi.core.webapp.databse.helpers.BaseQueryInterface;
import java.util.ArrayList;
import java.util.List;

public class Teste extends Model{		

	@RParam(rParamName = "p_bola")
	private String bola;
	
	private List<Table_1> table_1 = new ArrayList<>();	
	public void setTable_1(List<Table_1> table_1){
		this.table_1 = table_1;
	}
	public List<Table_1> getTable_1(){
		return this.table_1;
	}

	
	public void setBola(String bola){
		this.bola = bola;
	}
	public String getBola(){
		return this.bola;
	}


	public static class Table_1 extends IGRPTable.Table{
		private String mota;
		private String carro;
		public void setMota(String mota){
			this.mota = mota;
		}
		public String getMota(){
			return this.mota;
		}

		public void setCarro(String carro){
			this.carro = carro;
		}
		public String getCarro(){
			return this.carro;
		}

	}

	public void loadTable_1(BaseQueryInterface query) {
		this.setTable_1(this.loadTable(query,Table_1.class));
	}

}