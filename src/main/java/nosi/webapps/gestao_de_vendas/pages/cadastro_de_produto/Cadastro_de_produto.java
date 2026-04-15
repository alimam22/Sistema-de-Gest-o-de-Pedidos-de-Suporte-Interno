package nosi.webapps.gestao_de_vendas.pages.cadastro_de_produto;

import nosi.core.webapp.Model;
import nosi.core.webapp.RParam;

import nosi.core.validator.constraints.*;

public class Cadastro_de_produto extends Model{		

	@NotNull()
	@RParam(rParamName = "p_nome")
	private String nome;

	@RParam(rParamName = "p_estoque")
	private Integer estoque;

	@RParam(rParamName = "p_preco_venda")
	private Integer preco_venda;

	@RParam(rParamName = "p_preco_compra")
	private Integer preco_compra;

	@NotNull()
	@RParam(rParamName = "p_categoria")
	private String categoria;
	
	public void setNome(String nome){
		this.nome = nome;
	}
	public String getNome(){
		return this.nome;
	}
	
	public void setEstoque(Integer estoque){
		this.estoque = estoque;
	}
	public Integer getEstoque(){
		return this.estoque;
	}
	
	public void setPreco_venda(Integer preco_venda){
		this.preco_venda = preco_venda;
	}
	public Integer getPreco_venda(){
		return this.preco_venda;
	}
	
	public void setPreco_compra(Integer preco_compra){
		this.preco_compra = preco_compra;
	}
	public Integer getPreco_compra(){
		return this.preco_compra;
	}
	
	public void setCategoria(String categoria){
		this.categoria = categoria;
	}
	public String getCategoria(){
		return this.categoria;
	}



}