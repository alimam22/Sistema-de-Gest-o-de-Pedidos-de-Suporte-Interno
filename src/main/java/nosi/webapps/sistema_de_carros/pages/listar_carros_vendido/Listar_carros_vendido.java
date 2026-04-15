package nosi.webapps.sistema_de_carros.pages.listar_carros_vendido;

import nosi.core.gui.components.IGRPTable;
import nosi.core.webapp.Model;
import nosi.core.webapp.RParam;
import nosi.core.webapp.databse.helpers.BaseQueryInterface;
import java.util.ArrayList;
import java.util.List;

public class Listar_carros_vendido extends Model{		

	@RParam(rParamName = "p_paragraph_1_text")
	private String paragraph_1_text;
	
	private List<Table_1> table_1 = new ArrayList<>();	
	public void setTable_1(List<Table_1> table_1){
		this.table_1 = table_1;
	}
	public List<Table_1> getTable_1(){
		return this.table_1;
	}

	
	public void setParagraph_1_text(String paragraph_1_text){
		this.paragraph_1_text = paragraph_1_text;
	}
	public String getParagraph_1_text(){
		return this.paragraph_1_text;
	}


	public static class Table_1 extends IGRPTable.Table{
		private String marca;
		private String modelo;
		private String comprador;
		private String id_venda;
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

		public void setComprador(String comprador){
			this.comprador = comprador;
		}
		public String getComprador(){
			return this.comprador;
		}

		public void setId_venda(String id_venda){
			this.id_venda = id_venda;
		}
		public String getId_venda(){
			return this.id_venda;
		}

	}

	public void loadTable_1(BaseQueryInterface query) {
		this.setTable_1(this.loadTable(query,Table_1.class));
	}

}