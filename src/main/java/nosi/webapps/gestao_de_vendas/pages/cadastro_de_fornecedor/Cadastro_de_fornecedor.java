package nosi.webapps.gestao_de_vendas.pages.cadastro_de_fornecedor;

import nosi.core.webapp.Model;
import nosi.core.webapp.RParam;

import nosi.core.validator.constraints.*;

public class Cadastro_de_fornecedor extends Model{		

	@NotNull()
	@RParam(rParamName = "p_nome")
	private String nome;

	@RParam(rParamName = "p_telefone")
	private Integer telefone;

	@NotNull()
	@RParam(rParamName = "p_email")
	private String email;

	@NotNull()
	@RParam(rParamName = "p_endereco")
	private String endereco;
	
	public void setNome(String nome){
		this.nome = nome;
	}
	public String getNome(){
		return this.nome;
	}
	
	public void setTelefone(Integer telefone){
		this.telefone = telefone;
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



}