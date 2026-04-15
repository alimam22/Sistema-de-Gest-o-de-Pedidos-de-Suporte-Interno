package nosi.webapps.sistema_gestao_aluno.pages.lista_de_alunos;

import nosi.core.gui.components.IGRPTable;
import nosi.core.webapp.Model;
import nosi.core.webapp.RParam;
import nosi.core.webapp.databse.helpers.BaseQueryInterface;
import java.util.ArrayList;
import java.util.List;

public class Lista_de_alunos extends Model{		
	
	private List<Table_1> table_1 = new ArrayList<>();	
	public void setTable_1(List<Table_1> table_1){
		this.table_1 = table_1;
	}
	public List<Table_1> getTable_1(){
		return this.table_1;
	}



	public static class Table_1 extends IGRPTable.Table{
		private String nome;
		private String apelido;
		private Integer idade;
		private String curso;
		private String email;
		public void setNome(String nome){
			this.nome = nome;
		}
		public String getNome(){
			return this.nome;
		}

		public void setApelido(String apelido){
			this.apelido = apelido;
		}
		public String getApelido(){
			return this.apelido;
		}

		public void setIdade(Integer idade){
			this.idade = idade;
		}
		public Integer getIdade(){
			return this.idade;
		}

		public void setCurso(String curso){
			this.curso = curso;
		}
		public String getCurso(){
			return this.curso;
		}

		public void setEmail(String email){
			this.email = email;
		}
		public String getEmail(){
			return this.email;
		}

	}

	public void loadTable_1(BaseQueryInterface query) {
		this.setTable_1(this.loadTable(query,Table_1.class));
	}

}