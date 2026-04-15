package nosi.webapps.gestao_de_vendas.pages.pagina_fornecedor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import nosi.core.gui.components.IGRPTable;
import nosi.core.webapp.Model;
import nosi.core.webapp.databse.helpers.BaseQueryInterface;
import java.util.ArrayList;
import java.util.List;

public class Pagina_fornecedor extends Model{		
	
	private List<Table_1> table_1 = new ArrayList<>();	
	public void setTable_1(List<Table_1> table_1){
		this.table_1 = table_1;
	}
	public List<Table_1> getTable_1(){
		return this.table_1;
	}



	public static class Table_1 extends IGRPTable.Table{
		private String nome;
		private Integer telefone;
		private String email;
		private String endereco;
		private String id_tbl_fornecedor;
		public void setNome(String nome){
			this.nome = nome;
		}
		public String getNome(){
			return this.nome;
		}

		public void setTelefone(@NotBlank @Size(max = 50) String telefone){
			this.telefone = Integer.valueOf(telefone);
		}
		public Integer getTelefone(){
			return this.telefone;
		}

		public void setEmail(String email){
			this.email = email;
		}
		public String getEmail(){
			return this.email;
		}

		public void setEndereco(String endereco){
			this.endereco = endereco;
		}
		public String getEndereco(){
			return this.endereco;
		}

		public void setId_tbl_fornecedor(String id_tbl_fornecedor){
			this.id_tbl_fornecedor = id_tbl_fornecedor;
		}
		public String getId_tbl_fornecedor(){
			return this.id_tbl_fornecedor;
		}

	}

	public void loadTable_1(BaseQueryInterface query) {
		this.setTable_1(this.loadTable(query,Table_1.class));
	}

}