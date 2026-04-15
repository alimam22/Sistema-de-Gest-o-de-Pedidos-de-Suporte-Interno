package nosi.webapps.gestao_de_vendas.pages.cadastro_de_usuario;

import nosi.core.gui.components.IGRPTable;
import nosi.core.webapp.Model;
import nosi.core.webapp.RParam;
import nosi.core.webapp.databse.helpers.BaseQueryInterface;
import java.util.ArrayList;
import java.util.List;

import nosi.core.validator.constraints.*;

public class Cadastro_de_usuario extends Model{		

	@NotNull()
	@RParam(rParamName = "p_nome_de_usuario")
	private String nome_de_usuario;

	@RParam(rParamName = "p_ativo")
	private int ativo;

	@RParam(rParamName = "p_perfil_de_usuario")
	private String perfil_de_usuario;

	@NotNull()
	@RParam(rParamName = "p_email_de_usuario")
	private String email_de_usuario;

	@NotNull()
	@RParam(rParamName = "p_senha_de_usuario")
	private String senha_de_usuario;
	
	private List<Table_1> table_1 = new ArrayList<>();	
	public void setTable_1(List<Table_1> table_1){
		this.table_1 = table_1;
	}
	public List<Table_1> getTable_1(){
		return this.table_1;
	}

	
	public void setNome_de_usuario(String nome_de_usuario){
		this.nome_de_usuario = nome_de_usuario;
	}
	public String getNome_de_usuario(){
		return this.nome_de_usuario;
	}
	
	public void setAtivo(int ativo){
		this.ativo = ativo;
	}
	public int getAtivo(){
		return this.ativo;
	}
	
	public void setPerfil_de_usuario(String perfil_de_usuario){
		this.perfil_de_usuario = perfil_de_usuario;
	}
	public String getPerfil_de_usuario(){
		return this.perfil_de_usuario;
	}
	
	public void setEmail_de_usuario(String email_de_usuario){
		this.email_de_usuario = email_de_usuario;
	}
	public String getEmail_de_usuario(){
		return this.email_de_usuario;
	}
	
	public void setSenha_de_usuario(String senha_de_usuario){
		this.senha_de_usuario = senha_de_usuario;
	}
	public String getSenha_de_usuario(){
		return this.senha_de_usuario;
	}


	public static class Table_1 extends IGRPTable.Table{
		private String nome;
		private String email;
		private String senha;
		private String perfil;
		private int ativo_usuario;
		private int ativo_usuario_check;
		private String id_tbl_usuario;
		public void setNome(String nome){
			this.nome = nome;
		}
		public String getNome(){
			return this.nome;
		}

		public void setEmail(String email){
			this.email = email;
		}
		public String getEmail(){
			return this.email;
		}

		public void setSenha(String senha){
			this.senha = senha;
		}
		public String getSenha(){
			return this.senha;
		}

		public void setPerfil(String perfil){
			this.perfil = perfil;
		}
		public String getPerfil(){
			return this.perfil;
		}

		public void setAtivo_usuario(int ativo_usuario){
			this.ativo_usuario = ativo_usuario;
		}
		public int getAtivo_usuario(){
			return this.ativo_usuario;
		}
		public void setAtivo_usuario_check(int ativo_usuario_check){
			this.ativo_usuario_check = ativo_usuario_check;
		}
		public int getAtivo_usuario_check(){
			return this.ativo_usuario_check;
		}

		public void setId_tbl_usuario(String id_tbl_usuario){
			this.id_tbl_usuario = id_tbl_usuario;
		}
		public String getId_tbl_usuario(){
			return this.id_tbl_usuario;
		}

	}

	public void loadTable_1(BaseQueryInterface query) {
		this.setTable_1(this.loadTable(query,Table_1.class));
	}

}